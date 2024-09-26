package com.example.diseomobile.data.models.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.diseomobile.data.models.profile.Profile
import java.util.Date

@Entity(
    foreignKeys = [ForeignKey(
        entity = Profile::class,
        parentColumns = ["id"],
        childColumns = ["profileId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val amount: Double,
    val date: Date,
    val income: Boolean,
    val profileId: Int
)