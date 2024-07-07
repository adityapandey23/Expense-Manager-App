package com.example.expense_manager_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expense_manager_app.data.dao.ExpenseDao
import com.example.expense_manager_app.data.model.ExpenseEntity


@Database(entities = [ExpenseEntity:: class], version=1)
abstract class ExpenseDataBase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao



    companion object{
        const val  DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}