package com.example.todolist.data


import android.content.Context
import com.example.todolist.data.model.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException


class DataAccessImplementation : DataAccess {

    private val gson = Gson()
    private val type = object : TypeToken<MutableList<Task>>() {}.type
    private val filename = "tasks"


    // zapis danych w postaci stringu do pliku aplikacji
    override fun saveData(context: Context, list: MutableList<Task>) {

        val file = File(context.filesDir, filename)

        val jsonOflIST = gson.toJson(list)

        return try {

            file.writeText(jsonOflIST)

        } catch (e: IOException) {
            e.printStackTrace()
        }


    }

    // odczyt danych w postaci stringu do pliku aplikacji
    override fun readData(context: Context): MutableList<Task> {

        val file = File(context.filesDir, filename)


        if (!file.exists()) file.createNewFile()

        val jsonString = file.readText()
        val outputList = gson.fromJson<MutableList<Task>>(jsonString, type)
        return outputList ?: ArrayList()

    }


}