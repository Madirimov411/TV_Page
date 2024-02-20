package com.uzb_khiva.numberpuzzlegame

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SettingsDialogFragment(
    var size: Int
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
            .setTitle("Define the Size of the Puzzle")
            .setSingleChoiceItems(R.array.size_option, size - 2) { dialog, which ->
                size = which + 2
            }
            .setPositiveButton("Change") { dialog, id ->
                (getActivity() as MainActivity).changeSize(size)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
        val settingDialog = builder.create()

        settingDialog.show()

        return super.onCreateDialog(savedInstanceState)
    }
}