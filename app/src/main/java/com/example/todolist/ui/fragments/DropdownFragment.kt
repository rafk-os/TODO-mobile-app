package com.example.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.databinding.FragmentDropdownBinding
import com.example.todolist.ui.DropdownViewModel


class DropdownFragment : Fragment() {


    private var _binding: FragmentDropdownBinding? = null
    private var viewModel = DropdownViewModel()
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
        binding.TaskCategoryMenu.setAdapter(arrayAdapter)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDropdownBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(DropdownViewModel::class.java)


        binding.TaskCategoryMenu.setOnItemClickListener { _, _, pos, _ ->
            val activeText: String = binding.TaskCategoryMenu.adapter.getItem(pos).toString()
            viewModel.setData(activeText)

        }
    }

}