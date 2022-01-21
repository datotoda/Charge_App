package com.example.chargeapp.fragments.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chargeapp.R
import com.example.chargeapp.model.User
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_registration.view.*


class RegistrationFragment : Fragment() {

    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        view.registrationRegistrationButton.setOnClickListener { registrationClicked(view) }

        return view
    }

    private fun registrationClicked(view: View){
        val name = view.registrationNameEditText.text.toString()
        val lastName = view.registrationLastNameEditText.text.toString()
        val email = view.registrationEmailEditText.text.toString()
        val password = view.registrationPasswordEditText.text.toString()

        if (validateInputs(listOf(name, lastName, email, password))){
            view.registrationNameEditText.setText(getString(R.string.blank_string))
            view.registrationLastNameEditText.setText(getString(R.string.blank_string))
            view.registrationEmailEditText.setText(getString(R.string.blank_string))
            view.registrationPasswordEditText.setText(getString(R.string.blank_string))

            val newUser = User(0, name, lastName, email, password)

            chargeViewModel.addUser(newUser)

            Toast.makeText(context, getString(R.string.registration_successfully), Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            view.registrationPasswordEditText.setText(getString(R.string.blank_string))
            Toast.makeText(context, getString(R.string.invalid_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(fields: List<String>): Boolean{
        fields.forEach { field -> if (field.isEmpty()) { return false } }
        return true
    }
}

// davit chinchaladze