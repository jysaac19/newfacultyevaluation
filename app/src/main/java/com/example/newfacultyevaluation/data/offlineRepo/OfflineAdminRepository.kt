package com.example.newfacultyevaluation.data.offlineRepo

import com.example.newfacultyevaluation.dao.AdminDao
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.AdminRepo

class OfflineAdminRepository(private val adminDao: AdminDao): AdminRepo{
    override suspend fun upsertAdmin(admin: Admin) {
        adminDao.upsertAdmin(admin)
    }

    override suspend fun deleteAdmin(admin: Admin) {
        adminDao.deleteAdminByID(admin)
    }

    override suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty) {
        adminDao.upsertCourseFaculty(courseFaculty)
    }

    override suspend fun upsertCourse(course: Course) {
        adminDao.upsertCourse(course)
    }

    override suspend fun updateUser(user: User){
        adminDao.updateUser(user)
    }

    override suspend fun updateFaculty(faculty: Faculty){
        adminDao.updateFaculty(faculty)
    }
    override suspend fun updateStudent(student: Student){
        adminDao.updateStudent(student)
    }
}