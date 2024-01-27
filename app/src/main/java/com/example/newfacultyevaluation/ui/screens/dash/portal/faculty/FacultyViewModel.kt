package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.repo.FacultyRepo

class FacultyViewModel(private val facultyRepo: FacultyRepo): ViewModel() {

    fun getCourses(id: String): LiveData<List<Course>>{
        return facultyRepo.getCourses(id)
    }

    fun getStudentCountAnswered(id: String): LiveData<Int>{
        return facultyRepo.getStudentCount(id)
    }

    fun getOverallPoints(id: String): LiveData<Int>{
        return facultyRepo.getOverallPoints(id)
    }

}