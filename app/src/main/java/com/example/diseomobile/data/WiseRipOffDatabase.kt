package com.example.diseomobile.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diseomobile.data.models.Transaction
import com.example.diseomobile.data.models.TransactionDao

@Database(entities = [Transaction::class] , version = 1)
abstract class WiseRipOffDatabase : RoomDatabase(){
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: WiseRipOffDatabase? = null

        fun getDataBase(context : Context) : WiseRipOffDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WiseRipOffDatabase::class.java,
                    "unscramble_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}