package com.example.todolist.data.model

data class Task(val taskName: String, val taskDate: String, val taskCategory: String) {
    override fun toString(): String {
        return "Name: $taskName ----- Date: $taskDate ----- Category: $taskCategory"
    }
}