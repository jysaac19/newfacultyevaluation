package com.example.newfacultyevaluation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.User

@Dao
interface FacultyDao {

    @Upsert
    suspend fun upsertFaculty(faculty: Faculty)
    @Query("SELECT course.courseid, course.courseName FROM coursefaculty INNER JOIN course ON coursefaculty.courseCode = course.courseid WHERE facultyID=:id")
    fun getCourses(id: String): LiveData<List<Course>>

    @Query("SELECT COUNT(*) FROM formstudentfaculty WHERE formstudentfaculty.facultyID = :id")
    fun getStudentCount(id: String): LiveData<Int>

    @Query("SELECT form.overallPoints FROM form INNER JOIN formstudentfaculty ON formstudentfaculty.formID = form.formID WHERE formstudentfaculty.facultyID = :id")
    fun getOverallPoints(id: String): LiveData<Int>
}