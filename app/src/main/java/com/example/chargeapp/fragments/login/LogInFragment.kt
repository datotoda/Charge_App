package com.example.chargeapp.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chargeapp.R
import com.example.chargeapp.model.User
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_log_in.view.*


class LogInFragment : Fragment() {

    private lateinit var users: List<User>
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        chargeViewModel.readAllUser.observe(viewLifecycleOwner, Observer { usersList ->
            if (usersList.isEmpty()){
                val defaultUser = User(0, getString(R.string.default_name), getString(R.string.default_last_name), getString(R.string.default_email), getString(R.string.default_password))
                chargeViewModel.addUser(defaultUser)
                users = listOf(defaultUser)
            } else {
                users = usersList
            }

        })

        with(view.loginLoginButton){
            setOnClickListener { logInClicked(view) }


//            ეს მხოლოდ იმიტომ რომ მარტივად და სწრაფად გადავიდეთ სიაზე
            setOnLongClickListener {
                view.loginEmailEditText.setText(getString(R.string.default_email))
                view.loginPasswordEditText.setText(getString(R.string.default_password))
                true
            }
        }
        view.loginRegistrationButton.setOnClickListener { registrationClicked() }

        return view
    }

    private fun logInClicked(view: View){
        val userFilteredList = users.filter { user ->
            view.loginEmailEditText.text.toString() == user.getEmail() &&
                    view.loginPasswordEditText.text.toString() == user.getPassword()
        }
        if (userFilteredList.isNotEmpty()) {
            view.loginEmailEditText.setText(getString(R.string.blank_string))
            view.loginPasswordEditText.setText(getString(R.string.blank_string))

            val action = LogInFragmentDirections.actionLogInFragmentToChargesListFragment(userFilteredList.first())
            findNavController().navigate(action)
        } else {
            view.loginPasswordEditText.setText(getString(R.string.blank_string))
            Toast.makeText(context, getString(R.string.invalid_login_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun registrationClicked(){
        findNavController().navigate(R.id.action_logInFragment_to_registrationFragment)
    }
}

// davit chinchaladze