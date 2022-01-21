package com.example.chargeapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chargeapp.R
import com.example.chargeapp.model.Charge
import com.example.chargeapp.viewmodel.ChargeViewModel
import kotlinx.android.synthetic.main.fragment_update_charge.*
import kotlinx.android.synthetic.main.fragment_update_charge.view.*


class UpdateChargeFragment : Fragment() {

    private val updateArgs by navArgs<UpdateChargeFragmentArgs>()
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_update_charge, container, false)

        view.updateTitleEditText.setText(updateArgs.editCharge.getTitle())
        view.updateDescriptionEditText.setText(updateArgs.editCharge.getDescription())
        view.updateCostEditText.setText(
            (updateArgs.editCharge.getCost() ?: getString(R.string.blank_string)).toString())
        view.updateUpdateButton.setOnClickListener { savedClicked() }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.updateMenuDelete -> deletedClicked()
            R.id.updateMenuSave -> savedClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletedClicked() {
        AlertDialog.Builder(requireContext())
            .apply {
                setPositiveButton(getString(R.string.yes)){ _, _ ->
                    chargeViewModel.deleteCharge(updateArgs.editCharge)
                    Toast.makeText(requireContext(), getString(R.string.successfully_removed_charge), Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                setNegativeButton(getString(R.string.no)) { _, _ -> }
                setTitle(getString(R.string.delete_charge_alert_title))
                setMessage(getString(R.string.delete_charge_alert_message))
                create()
                show()
            }
    }

    private fun savedClicked() {
        val title = updateTitleEditText.text.toString()
        val description = updateDescriptionEditText.text.toString()
        val cost = updateCostEditText.text.toString()

        if (validateInputs(title, description, cost)){
            val charge = Charge(updateArgs.editCharge.id, title, description,
                cost.toFloatOrNull(), updateArgs.editCharge.getUserId())

            chargeViewModel.updateCharge(charge)

            Toast.makeText(context, getString(R.string.successfully_save), Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(context, getString(R.string.invalid_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInputs(title: String, description: String, cost: String): Boolean{
        if (cost.toFloatOrNull() == null && cost.isNotEmpty()){ return false }
        return (title.isNotEmpty() && description.isNotEmpty())
    }
}

// davit chinchaladze