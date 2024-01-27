package com.example.newfacultyevaluation.data.offlineRepo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.StudentDao
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Form
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.StudentFaculty
import com.example.newfacultyevaluation.data.repo.StudentRepo

class OfflineStudentRepository(private val studentDao: StudentDao): StudentRepo {
    override suspend fun upsertStudent(student: Student) {
        studentDao.upsertStudent(student)
    }

    override suspend fun upsertFormStudentFaculty(formStudentFaculty: FormStudentFaculty) {
        studentDao.upsertFormStudentFaculty(formStudentFaculty)
    }
//
    override suspend fun upsertCourseStudent(courseStudent: CourseStudent) {
        studentDao.upsertCourseStudent(courseStudent)
    }
//
//    override fun getFacultyCourses(): LiveData<List<CourseFaculty>> {
//        TODO("Not yet implemented")
//    }
//
    override fun getCoursesByStudentID(id: String): LiveData<List<Course>> {
        return studentDao.getCoursesByStudentID(id)
    }

    override suspend fun upsertCourse(course: Course) {
        studentDao.upsertCourse(course)
    }

    override fun getAllCourses(): LiveData<List<Course>> {
        return studentDao.getAllCourses()
    }

    override fun getStudentFaculty(id: String, selectedCourse: String): LiveData<Faculty> {
        return studentDao.getStudentFaculty(id, selectedCourse)
    }

    override suspend fun deleteCourse(courseStudent: CourseStudent) {
        studentDao.deleteCourse(courseStudent)
    }

    override suspend fun upsertForm(form: Form){
        studentDao.upsertForm(form)
    }
}