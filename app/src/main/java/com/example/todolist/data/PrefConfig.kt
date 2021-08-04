package com.example.todolist.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.todolist.data.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PrefConfig {

    private val key = "list_key"

    fun writeListInPref(context: Context, list: ArrayList<Task>) {

        val gson = Gson()
        val jsonOflIST = gson.toJson(list)

        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key,jsonOflIST)
        editor.apply()


    }

    fun readListFromPref(context: Context): ArrayList<Task>{
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val jsonString = sharedPreferences.getString(key,"")
        val gson = Gson()

        val type = object : TypeToken<ArrayList<Task>>() {}.type
        val outputList=gson.fromJson<ArrayList<Task>>(jsonString,type)

        return outputList?:ArrayList<Task>()



    }
}