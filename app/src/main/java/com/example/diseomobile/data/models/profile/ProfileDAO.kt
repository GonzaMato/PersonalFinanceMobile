package com.example.diseomobile.data.models.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDAO {
    @Insert
    suspend fun insertProfile(profile: Profile)

    @Query("UPDATE Profile SET balance = :newBalance WHERE id = :profileId")
    suspend fun updateBalance(profileId: Int, newBalance: Double)

    @Query("SELECT * FROM Profile WHERE id = :profileId")
    suspend fun getProfileById(profileId: Int): Profile?

    @Query("SELECT balance FROM Profile WHERE id = :profileId")
    fun getProfileBalanceLiveData(profileId: Int): LiveData<Double>

    @Query("SELECT balance FROM Profile WHERE id = :profileId")
    suspend fun getProfileBalance(profileId: Int): Double

    @Query("SELECT name FROM Profile WHERE id = :profileId")
    fun getProfileName(profileId: Int): LiveData<String>
}