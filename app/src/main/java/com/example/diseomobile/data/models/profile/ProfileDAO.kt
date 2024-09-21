package com.example.diseomobile.data.models.profile

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
    suspend fun getProfileBalance(profileId: Int): Double?
}