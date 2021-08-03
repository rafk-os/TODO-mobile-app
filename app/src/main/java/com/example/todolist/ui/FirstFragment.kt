package com.example.todolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.todolist.R
import com.example.todolist.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding?= null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter= ArrayAdapter(requireContext(), R.layout.dropdown_item,categories)
        binding.TaskCategoryMenu.setAdapter(arrayAdapter)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentFirstBinding.inflate(inflater,container,false)

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}