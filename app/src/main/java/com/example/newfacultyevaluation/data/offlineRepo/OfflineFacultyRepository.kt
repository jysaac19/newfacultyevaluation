package com.example.newfacultyevaluation.data.offlineRepo

import androidx.lifecycle.LiveData
import com.example.newfacultyevaluation.dao.FacultyDao
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo

class OfflineFacultyRepository(private val facultyDao: FacultyDao): FacultyRepo {

    override suspend fun upsertFaculty(faculty: Faculty) = facultyDao.upsertFaculty(faculty)
    override fun getCourses(id: String): LiveData<List<Course>> {
        return facultyDao.getCourses(id)
    }
    override fun getStudentCount(id: String): LiveData<Int>{
        return facultyDao.getStudentCount(id)
    }

    override fun getOverallPoints(id: String): LiveData<Int>{
        return facultyDao.getOverallPoints(id)
    }
}