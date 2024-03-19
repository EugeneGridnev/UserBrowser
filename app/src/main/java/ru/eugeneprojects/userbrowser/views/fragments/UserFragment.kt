package ru.eugeneprojects.userbrowser.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.databinding.FragmentUserBinding

class UserFragment : Fragment(){

    private var binding: FragmentUserBinding? = null
    private val args: UserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUserUI()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    private fun setUpUserUI() {

        val user = args.user

        binding?.imageViewUserImage?.let {
            Glide.with(this)
                .load(user.picture.large)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(it)
        }

        binding?.textViewUserName?.text = user.name.first + " " + user.name.last
        binding?.textViewUserAge?.text = user.dob.age.toString()
        binding?.textViewUserEmail?.text = user.email
        binding?.textViewUserTelephoneNumber?.text = user.phone
        binding?.textViewUserNationality?.text = user.nat
        binding?.textViewUserGender?.text = user.gender
        binding?.textViewUserID?.text = user.id.name + " " + (user.id.value ?: "")
        binding?.textViewUserCoordinates?.text =
            user.location.coordinates.latitude + " " + user.location.coordinates.longitude

    }
}