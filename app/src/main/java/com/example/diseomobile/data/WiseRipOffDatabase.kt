package com.example.diseomobile.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diseomobile.data.converters.Converters
import com.example.diseomobile.data.models.Transaction
import com.example.diseomobile.data.models.TransactionDao

@Database(entities = [Transaction::class] , version = 1)
@TypeConverters(Converters::class)
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
                    "wise_rip_off_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}