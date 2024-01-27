package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty

interface FacultyRepo {
    suspend fun upsertFaculty(faculty: Faculty)
    fun getCourses(id: String): LiveData<List<Course>>

    fun getStudentCount(id: String): LiveData<Int>

    fun getOverallPoints(id: String): LiveData<Int>
}