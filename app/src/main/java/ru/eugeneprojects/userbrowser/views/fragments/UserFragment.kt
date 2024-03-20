package ru.eugeneprojects.userbrowser.views.fragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.eugeneprojects.userbrowser.R
import ru.eugeneprojects.userbrowser.databinding.FragmentUserBinding
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

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

        binding?.textViewUserName?.text = user.name.title + " " + user.name.first + " " + user.name.last
        binding?.textViewUserAge?.text = user.dob.age.toString()
        binding?.textViewUserDOB?.text = user.dob.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        binding?.textViewUserRegistrationDate?.text = user.registered.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        binding?.textViewUserEmail?.text = user.email
        binding?.textViewUserUserName?.text = user.login.username
        binding?.textViewUserUserPassword?.text = user.login.password
        binding?.textViewUserTelephoneNumber?.text = user.phone
        binding?.textViewUserCellNumber?.text = user.cell
        binding?.textViewUserNationality?.text = user.nat
        binding?.textViewUserGender?.text = user.gender
        binding?.textViewUserID?.text = user.id.name + " " + (user.id.value ?: "")
        binding?.textViewUserAddress?.text =
            "${user.location.street.number} ${user.location.street.name} ${user.location.city} ${user.location.state} ${user.location.country}"
        binding?.textViewUserTimeZone?.text =
            "${user.location.timezone.offset} ${user.location.timezone.description}"

        binding?.textViewUserAddress?.setOnClickListener {
            val location = Uri.parse("geo:" + user.location.coordinates.latitude + "," + user.location.coordinates.longitude + "?z=12")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            val mapInt =  Intent.createChooser(mapIntent, "Выберите как открыть")

            try {
                startActivity(mapInt)
            } catch (e: ActivityNotFoundException) {

            }
        }

        binding?.textViewUserEmail?.setOnClickListener {
            composeEmail(user.email)
        }

        binding?.textViewUserTelephoneNumber?.setOnClickListener {
            dialPhoneNumber(user.phone)
        }

        binding?.textViewUserCellNumber?.setOnClickListener {
            dialPhoneNumber(user.cell)
        }

    }

    private fun composeEmail(address: String) {
        try {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
            }
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {

        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }
}