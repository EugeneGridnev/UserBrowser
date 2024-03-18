package ru.eugeneprojects.userbrowser.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.eugeneprojects.userbrowser.adapters.UsersPagingAdapter
import ru.eugeneprojects.userbrowser.databinding.FragmentUsersListBinding
import ru.eugeneprojects.userbrowser.repository.network.UsersRepositoryIMPL
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
        val viewModelProviderFactory = UsersViewModelProviderFactory(usersRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory)[UsersSharedViewModel::class.java]

        setUpRecyclerView()
        observeProducts(usersPagingAdapter)
    }

    private fun observeProducts(adapter: UsersPagingAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collectLatest(adapter::submitData)
            }
        }
    }

    private fun setUpRecyclerView() {
        usersPagingAdapter = UsersPagingAdapter()
        binding?.recyclerViewUsers?.apply {
            adapter = usersPagingAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}