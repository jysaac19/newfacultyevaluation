package com.example.newfacultyevaluation.data.repo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Form
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.StudentFaculty

interface StudentRepo {

    suspend fun upsertStudent(student: Student)
//    suspend fun upsertFormStudentFaculty(formID: Int, studentID: String, facultyID: String)
    suspend fun upsertCourseStudent(courseStudent: CourseStudent)
//    fun getFacultyCourses(): LiveData<List<CourseFaculty>>
//    fun getStudentFaculty(): LiveData<List<StudentFaculty>>
    fun getCoursesByStudentID(id: String): LiveData<List<Course>>
    suspend fun upsertCourse(course: Course)
    fun getAllCourses(): LiveData<List<Course>>

    fun getStudentFaculty(id: String, selectedCourse: String): LiveData<Faculty>
    suspend fun deleteCourse(courseStudent: CourseStudent)

    suspend fun upsertFormStudentFaculty(formStudentFaculty: FormStudentFaculty)

    suspend fun upsertForm(form: Form)
}