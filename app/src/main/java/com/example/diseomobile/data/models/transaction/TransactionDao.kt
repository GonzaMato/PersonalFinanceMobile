package com.example.diseomobile.data.models.transaction

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.Date

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM `Transaction` WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsInRange(startDate: Date, endDate: Date): List<Transaction>

    @Query("SELECT * FROM `Transaction` WHERE profileId = :profileId ORDER BY date DESC LIMIT :limit OFFSET :offset")
    suspend fun getTransactionsWithPagination(profileId: Int, limit: Int, offset: Int): List<Transaction>

    @Query("SELECT * FROM `Transaction` WHERE profileId = :profileId ORDER BY date DESC")
    fun getTransactions(profileId: Int): LiveData<List<Transaction>>
}