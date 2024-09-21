package com.example.diseomobile.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.diseomobile.data.converters.Converters
import com.example.diseomobile.data.models.profile.Profile
import com.example.diseomobile.data.models.profile.ProfileDAO
import com.example.diseomobile.data.models.transaction.Transaction
import com.example.diseomobile.data.models.transaction.TransactionDao

@Database(entities = [Transaction::class , Profile::class] , version = 1)
@TypeConverters(Converters::class)
abstract class WiseRipOffDatabase : RoomDatabase(){
    abstract fun transactionDao(): TransactionDao
    abstract fun profileDao(): ProfileDAO

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