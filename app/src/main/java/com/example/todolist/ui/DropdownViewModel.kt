package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DropdownViewModel : ViewModel() {

    private var selectedItem = MutableLiveData<String>()

    fun setData(item: String) {

        selectedItem.value = item

    }

    fun getData(): LiveData<String> {

        return selectedItem

    }
}