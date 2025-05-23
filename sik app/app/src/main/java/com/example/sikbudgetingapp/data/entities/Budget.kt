package com.example.sikbudgetingapp.data.entities

data class Budget(
    val id: String = "",
    val title: String = "",
    val amount: Double = 0.0,
    val category: String = "",
    val date: Long = System.currentTimeMillis(),
    val userId: String = ""
)
