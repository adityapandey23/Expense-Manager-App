package com.example.expense_manager_app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expense_manager_app.data.ExpenseDataBase
import com.example.expense_manager_app.data.dao.ExpenseDao
import com.example.expense_manager_app.data.model.ExpenseEntity

class AddExpenseViewModel(val dao: ExpenseDao):ViewModel() {
    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean{
        return try {
            dao.insertExpenses(expenseEntity)
            true
        }
        catch (ex: Throwable){
            false
        }
    }
}

class AddExpenseViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass : Class<T>): T {
        if(modelClass.isAssignableFrom(AddExpenseViewModel::class.java)) {
            val dao= ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}