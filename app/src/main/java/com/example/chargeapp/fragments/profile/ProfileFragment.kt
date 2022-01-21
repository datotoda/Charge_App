package com.example.chargeapp.fragments.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chargeapp.R
import com.example.chargeapp.model.User
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_update_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var currentUser: User
    private val profileArgs by navArgs<ProfileFragmentArgs>()
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        chargeViewModel.readAllUser.observe(viewLifecycleOwner, Observer { usersList ->
            currentUser = usersList.filter { u -> u.id == profileArgs.user.id }.first()

            view.profileNameEditText.setText(currentUser.getName())
            view.profileLastNameEditText.setText(currentUser.getLastName())
            view.profileEmailEditText.setText(currentUser.getEmail())
            view.profilePasswordEditText.setText(currentUser.getPassword())
        })

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.profileMenuEdit -> menuEditClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuEditClicked() {
        val action = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(currentUser)
        findNavController().navigate(action)
    }
}

// davit chinchaladze