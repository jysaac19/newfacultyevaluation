package com.example.newfacultyevaluation.ui.screens.dash.portal.admin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.data.repo.AdminRepo
import com.example.newfacultyevaluation.data.repo.FacultyRepo
import com.example.newfacultyevaluation.data.repo.StudentRepo
import com.example.newfacultyevaluation.data.repo.UserRepository
import kotlinx.coroutines.launch

class AdminViewModel(private val userRepository: UserRepository,
                     private val adminRepo: AdminRepo,
                     private val studentRepo: StudentRepo,
                     private val facultyRepo: FacultyRepo
                    ): ViewModel() {


    var fullName = ""
    var password = ""
    var facultyID = ""
    var program = ""
    var course  = ""
    var date = ""

    var userID = ""
    var pass = ""
    var selectedProgram = ""
    var role = ""



    private val _facultyID: String
        get() = facultyID
    private val _program: String
        get() = program
    private val _course: String
        get() = course

    private val _date: String
        get() = date
    private val _fullName: String
        get() = fullName
    private val _password: String
        get() = password

    private val _userID: String
        get() = userID

    private val _pass: String
        get() = pass

    private val _selectedCourse: String
        get() = selectedProgram

    private val _role: String
        get() = role

    private var insertSuccessful by mutableStateOf(false)

    fun getAllUsers(): LiveData<List<User>>{
       return userRepository.getAllUsers()
    }

    fun insertCourseFaculty(courseFaculty: CourseFaculty){
        viewModelScope.launch {
            adminRepo.upsertCourseFaculty(courseFaculty)

        }
    }

    fun checkUserID(userID: String): LiveData<User>{
        return userRepository.getUsers(userID)
    }

    fun insertUser(): Boolean{
        println("UserID : $userID")
        println("FullName : $fullName")
        println("Course : $selectedProgram")
        println("Pass : $pass")
        if(userID.isNotBlank() && fullName.isNotBlank() && pass.isNotBlank() && role != "ROLE: "){
            viewModelScope.launch {
                if(role == "Student" && selectedProgram != "PROGRAM: "){
                    studentRepo.upsertStudent(
                        Student(
                        studentID = _userID,
                        fullName = _fullName,
                        password = _pass,
                        selectedCourse = _selectedCourse,
                        role = _role,
                        dateCreated = _date
                        )
                    )
                }
                else if(role == "Faculty"){
                    facultyRepo.upsertFaculty(Faculty(
                        facultyID = _userID,
                        fullName = _fullName,
                        password = _pass,
                        )
                    )
                } else if (selectedProgram == "PROGRAM: "){
                    return@launch
                }
                userRepository.upsertUser(
                    User(
                        userID = _userID,
                        fullName = _fullName,
                        password = _pass,
                        selectedCourse = if(role == "Student") _selectedCourse else "",
                        role = _role,
                        dateCreated = _date
                    )
                )
            }
            insertSuccessful = true
        }

        println("Success : $insertSuccessful")
        return insertSuccessful
    }

    fun upsertCourse(course: Course){
        viewModelScope.launch {
            adminRepo.upsertCourse(course)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch {
            adminRepo.updateUser(user)
        }
    }

    fun updateFaculty(faculty: Faculty){
        viewModelScope.launch {
            adminRepo.updateFaculty(faculty)
        }
    }
    fun updateStudent(student: Student){
        viewModelScope.launch {
            adminRepo.updateStudent(student)
        }
    }

}