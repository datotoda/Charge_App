package com.example.chargeapp.fragments.add


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
import kotlinx.android.synthetic.main.fragment_add_charge.*
import kotlinx.android.synthetic.main.fragment_add_charge.view.*


class AddChargeFragment : Fragment() {

    private val addArgs by navArgs<AddChargeFragmentArgs>()
    private val chargeViewModel: ChargeViewModel by lazy {
        ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_add_charge, container, false)

        view.addAddButton.setOnClickListener { savedClicked() }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenuSave -> savedClicked()
        }
        return super.onOptionsItemSelected(item)
    }
    
    private fun savedClicked() {
        val title = addTitleEditText.text.toString()
        val description = addDescriptionEditText.text.toString()
        val cost = addCostEditText.text.toString()

        if (validateInputs(title, description, cost)){
            val newCharge = Charge(0, title, description, cost.toFloatOrNull(), addArgs.userId)

            chargeViewModel.addCharge(newCharge)

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