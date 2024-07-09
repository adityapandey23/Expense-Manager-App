package com.example.expense_manager_app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expense_manager_app.R
import com.example.expense_manager_app.data.ExpenseDataBase
import com.example.expense_manager_app.data.dao.ExpenseDao
import com.example.expense_manager_app.data.model.ExpenseEntity
import kotlin.math.round

class HomeViewModel(dao: ExpenseDao) : ViewModel() {
    val expense = dao.getAllExpenses()
    fun getBalance(list: List<ExpenseEntity>): String {
        var total =0.0
        list.forEach {
            if(it.type == "Income") {
                total+=it.amount
            }
            else
            {
                total-=it.amount

            }
        }
        total = round(total)
        return "$ $total"

    }

    fun getTotalExpense(list: List<ExpenseEntity>) :String{
        var total =0.0
        list.forEach {
            if(it.type == "Expense") {
                total += it.amount
            }
        }
        total = round(total)
        return "$ $total"

    }

    fun getTotalIncome(list: List<ExpenseEntity>): String{
        var total =0.0
        list.forEach {
            if(it.type == "Income") {
                total+=it.amount
            }
        }
        total = round(total)
        return "$ $total"
    }
    fun getItemIcon(item: ExpenseEntity): Int {
        if (item.category == "Paypal")
        {
            return R.drawable.paypal
        }
        else if (item.category == "Spotify")
        {
            return R.drawable.spotify
        }
        else if (item.category == "Starbucks")
        {
            return R.drawable.starbucks
        }
        return R.drawable.upwork
    }
}

class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun<T : ViewModel> create(modelClass : Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao= ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}