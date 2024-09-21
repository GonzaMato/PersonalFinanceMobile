package com.example.diseomobile.data.models.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var balance: Double,
)