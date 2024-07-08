package com.example.expense_manager_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expense_manager_app.data.dao.ExpenseDao
import com.example.expense_manager_app.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                    }

                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDao()
                        dao.insertExpenses(ExpenseEntity(1,"Salary", 5000.48, System.currentTimeMillis(),"Salary" , "Income"))
                        dao.insertExpenses(ExpenseEntity(2,"Paypal", 2000.50, System.currentTimeMillis(),"Paypal" , "Income"))
                        dao.insertExpenses(ExpenseEntity(3,"Spotify", 100.43, System.currentTimeMillis(),"Spotify" , "Expense"))
                        dao.insertExpenses(ExpenseEntity(4,"Starbucks", 400.56, System.currentTimeMillis(),"Starbucks" , "Expense"))
                    }

                }
                }).build()
            }
    }
}


