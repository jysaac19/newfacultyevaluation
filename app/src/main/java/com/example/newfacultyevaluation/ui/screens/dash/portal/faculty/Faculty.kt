package com.example.newfacultyevaluation.ui.screens.dash.portal.faculty

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import com.example.newfacultyevaluation.ui.nav.Main
import com.example.newfacultyevaluation.ui.nav.StudentNav
import com.example.newfacultyevaluation.ui.screens.auth.LoginViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacultyPortal(
    loginViewModel: LoginViewModel,
    viewModel: FacultyViewModel = viewModel(factory = FacultyAppViewModelProvider.FacultyFactory),
    navController: NavController
) {

    /*
        Select courses
        Course Name, Total Students, Rating
     */
   Row(

   ) {
       val courses by viewModel.getCourses(loginViewModel.userID).observeAsState()
       val studentAnswered by viewModel.getStudentCountAnswered(loginViewModel.userID).observeAsState()
       val rating by viewModel.getOverallPoints(loginViewModel.userID).observeAsState()
       Column {

           Text(text = "Course Name")
           courses?.forEach {
               Text(text = it.courseName)
           }
       }
       Column {

           Text(text = "Student Answered")
           Text(text = studentAnswered.toString())
       }
       Column {
           Text(text = "Ratings")
           Text(text = rating.toString())
       }


   }
    Column(
        verticalArrangement = Arrangement.Bottom
    ) {
       val context1 = LocalContext.current
       Button(
           onClick = {
               loginViewModel.status = false
               val prefer = context1.getSharedPreferences("prefs", 0)
               prefer.edit()
                   .clear()
                   .apply()

               navController.popBackStack()
               navController.navigate(Main.AUTH.name)
           }
       ) {
           Text(text = "Log out")
       }
    }


}