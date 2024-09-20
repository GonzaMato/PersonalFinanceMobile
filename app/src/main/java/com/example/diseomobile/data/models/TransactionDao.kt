package com.example.diseomobile.data.models

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)


}