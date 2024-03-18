package ru.eugeneprojects.userbrowser.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.eugeneprojects.userbrowser.databinding.FragmentUserBinding

class UserFragment : Fragment(){

    private var binding: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}