package ru.eugeneprojects.userbrowser.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.adapters.UsersLoadStateAdapter
import ru.eugeneprojects.userbrowser.adapters.UsersPagingAdapter
import ru.eugeneprojects.userbrowser.data.network.connection.ConnectivityRepositoryIMPL
import ru.eugeneprojects.userbrowser.databinding.FragmentUsersListBinding
import ru.eugeneprojects.userbrowser.data.network.repository.UsersRepositoryIMPL
import ru.eugeneprojects.userbrowser.views.UsersSharedViewModel
import ru.eugeneprojects.userbrowser.views.UsersViewModelProviderFactory

class UsersListFragment : Fragment(){

    private var binding: FragmentUsersListBinding? = null
    private lateinit var usersPagingAdapter: UsersPagingAdapter
    private lateinit var viewModel: UsersSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersRepository = UsersRepositoryIMPL()
        val connectivityRepository = ConnectivityRepositoryIMPL(requireContext())
        val viewModelProviderFactory = UsersViewModelProviderFactory(usersRepository, connectivityRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[UsersSharedViewModel::class.java]

        setUpUserList()
        observeUsers(usersPagingAdapter)

        viewModel.isOnline.observe(viewLifecycleOwner) { isOnline ->
            if (isOnline) {
                usersPagingAdapter.retry()
            } else {
                Toast.makeText(activity, resources.getString(R.string.network_error_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun observeUsers(adapter: UsersPagingAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collectLatest(adapter::submitData)
            }
        }
    }

    private fun setUpUserList() {
        usersPagingAdapter = UsersPagingAdapter()

        binding?.recyclerViewUsers?.layoutManager = LinearLayoutManager(activity)
        binding?.recyclerViewUsers?.adapter = usersPagingAdapter.withLoadStateFooter(UsersLoadStateAdapter())

        setOnUserClick()

        usersPagingAdapter.addLoadStateListener { combinedLoadStates ->
            val refreshState = combinedLoadStates.refresh
            binding?.recyclerViewUsers?.isVisible = refreshState != LoadState.Loading
            binding?.progressBar?.isVisible = refreshState == LoadState.Loading

            if (refreshState is LoadState.Error) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.toast_load_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setOnUserClick() {

        usersPagingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("user", it)
            }
            findNavController().navigate(
                R.id.action_usersListFragment_to_userFragment,
                bundle
            )
        }
    }
}