package com.example.chargeapp.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chargeapp.R
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_charges_list.view.*


class ChargesListFragment : Fragment() {

    private var isChargeListEmpty = false
    private val listArgs by navArgs<ChargesListFragmentArgs>()
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_charges_list, container, false)

        view.listFloatingActionButton.setOnClickListener { addButtonClicked() }
        with(view.listRecyclerView) {
            val chargesAdapter = ChargesListAdapter()
            adapter = chargesAdapter
            layoutManager = LinearLayoutManager(context)
            chargeViewModel.readAllData.observe(viewLifecycleOwner, Observer { chargesList ->
                val filteredChargeList = chargesList.filter { charge ->
                    charge.getUserId() == listArgs.user.id
                }
                isChargeListEmpty = filteredChargeList.isEmpty()
                chargesAdapter.setData(filteredChargeList)
            })
            setHasFixedSize(true)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.listMenuDelete -> menuDeletedClicked()
            R.id.listMenuProfile -> menuProfileClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addButtonClicked() {
        val action = ChargesListFragmentDirections.actionChargesListFragmentToAddChargeFragment(listArgs.user.id)
        findNavController().navigate(action)
    }

    private fun menuProfileClicked() {
        val action = ChargesListFragmentDirections.actionChargesListFragmentToProfileFragment(listArgs.user)
        findNavController().navigate(action)
    }

    private fun menuDeletedClicked() {
        if (isChargeListEmpty){
            Toast.makeText(context, getString(R.string.list_is_empty), Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(requireContext())
                .apply {
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        chargeViewModel.deleteAllCharges(listArgs.user.id)
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.successfully_removed_everything),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    setNegativeButton(getString(R.string.no)) { _, _ -> }
                    setTitle(getString(R.string.delete_all_charges_alert_title))
                    setMessage(getString(R.string.delete_all_charges_alert_message))
                    create()
                    show()
                }
        }
    }
}

// davit chinchaladze