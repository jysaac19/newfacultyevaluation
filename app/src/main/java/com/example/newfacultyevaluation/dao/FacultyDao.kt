package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.User

@Dao
interface FacultyDao {

    @Upsert
    suspend fun upsertFaculty(faculty: Faculty)

}