package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class ErrorDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.modal_error, container, false)
        val acceptButton: Button = rootView.findViewById(R.id.acceptButton)
        val declineButton: Button = rootView.findViewById(R.id.declineButton)

        acceptButton.setOnClickListener {
            dismiss()
        }

        declineButton.setOnClickListener {
            activity?.finish()
        }



        return rootView
    }
}