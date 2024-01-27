package com.example.newfacultyevaluation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newfacultyevaluation.dao.AdminDao
import com.example.newfacultyevaluation.dao.CourseDao
import com.example.newfacultyevaluation.dao.FacultyDao
import com.example.newfacultyevaluation.dao.ProgramDao
import com.example.newfacultyevaluation.dao.StudentDao
import com.example.newfacultyevaluation.dao.UserDao
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.CourseStudent
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Form
import com.example.newfacultyevaluation.data.model.FormStudentFaculty
import com.example.newfacultyevaluation.data.model.Program
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User
import com.example.newfacultyevaluation.ui.screens.dash.portal.admin.AdminPortal

@Database(
    entities = [
                User::class,
                Faculty::class,
                Student::class,
                Program::class,
                Course::class,
                Admin::class,
                CourseFaculty::class,
                CourseStudent::class,
                FormStudentFaculty::class,
                Form::class
               ],
    version = 1,
    exportSchema = false
)
abstract class FacultyEvaluationDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun facultyDao(): FacultyDao
    abstract fun programDao(): ProgramDao
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun adminDao(): AdminDao
    companion object{
        @Volatile
        private var Instance: FacultyEvaluationDatabase? = null

        fun getDatabase(context: Context): FacultyEvaluationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FacultyEvaluationDatabase::class.java, "FacultyAppDatabase")

                    .addCallback(addAdmin)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
        private val addAdmin = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val adminid = "\'400023\'"
                val fullName = "\'Admin\'"
                val password = "\'admin\'"
                val role = "\'Admin\'"
                db.execSQL("INSERT INTO admin (adminid, password) VALUES ($adminid, $password);")
                db.execSQL("INSERT INTO user (userid, fullName, password, role) VALUES ($adminid, $fullName, $password, $role);")
            }
        }
    }
}