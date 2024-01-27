package com.example.newfacultyevaluation.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Form(
    @PrimaryKey
    val formID: Int,
    val overallPoints: Int,
    val feedback: String?
)
