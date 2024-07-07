package com.example.expense_manager_app.data.model
import androidx.room.Entity
@Entity(tableName = "expense_table")
data class ExpenseEntity(
    val id: Int ,
    val title: String,
    val amount: Double,
    val data: Long,
    val category:String,
    val type: String
)
