package com.example.chargeapp.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chargeapp.R
import com.example.chargeapp.model.User
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_update_profile.view.*


class UpdateProfileFragment : Fragment() {

    private val updateArgs by navArgs<UpdateProfileFragmentArgs>()
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update_profile, container, false)

        view.updateProfileNameEditText.setText(updateArgs.user.getName())
        view.updateProfileLastNameEditText.setText(updateArgs.user.getLastName())
        view.updateProfileEmailEditText.setText(updateArgs.user.getEmail())

        view.updateProfileSaveButton.setOnClickListener { savedClicked() }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
        menu.removeItem(R.id.updateMenuDelete)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.updateMenuSave -> savedClicked()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun savedClicked() {
        val view = requireView()
        val name = view.updateProfileNameEditText.text.toString()
        val lastName = view.updateProfileLastNameEditText.text.toString()
        val email = view.updateProfileEmailEditText.text.toString()
        val password = view.updateProfilePasswordEditText.text.toString()

        if (validateInputs(name, lastName, email, password)){
            val user = User(updateArgs.user.id, name, lastName, email, password)

            chargeViewModel.updateUser(user)

            Toast.makeText(context, getString(R.string.successfully_save), Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            view.updateProfilePasswordEditText.setText(R.string.blank_string)
            Toast.makeText(context, getString(R.string.invalid_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(name: String, lastName: String, email: String, password: String): Boolean{
        return (name.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty())
    }
}

// davit chinchaladze