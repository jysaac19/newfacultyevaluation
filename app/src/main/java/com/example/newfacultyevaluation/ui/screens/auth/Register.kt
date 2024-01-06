package com.example.newfacultyevaluation.ui.screens.auth


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Key
import androidx.compose.material.icons.rounded.KeyOff
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.newfacultyevaluation.R
import com.example.newfacultyevaluation.ui.nav.Auth
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newfacultyevaluation.ui.FacultyAppViewModelProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel(factory = FacultyAppViewModelProvider.Factory),
    roles: HashMap<String, String> = hashMapOf("20" to "Student", "30" to "Faculty", "40" to "Admin")
) {

    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var userID by rememberSaveable {
            mutableStateOf("")
        }
        var fullName by rememberSaveable {
            mutableStateOf("")
        }
        var pass by rememberSaveable {
            mutableStateOf("")
        }

        var seePass by rememberSaveable {
            mutableStateOf(false)
        }
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        var course = listOf("COURSE: ","BSCS", "BSEntrep", "BSA", "BSAIS", "BSTM")
        var selectedCourse by rememberSaveable {
            mutableStateOf(course[0])
        }
        Icon(
            painterResource(id = R.drawable.nbsc_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .border(1.dp, Color.Black)
                .graphicsLayer(
                    translationX = 10f
                )
                .size(200.dp),
            tint = Color.Unspecified)
        Column(
            modifier = Modifier.border(1.dp, Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Register to create your account", fontWeight = FontWeight.W300, letterSpacing = 2.sp, modifier = Modifier.padding(10.dp))
        }

        Column(
            modifier = Modifier
                .size(400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    value = userID,
                    onValueChange = { userID = it },
                    label = { Text(text = "User ID", letterSpacing = 4.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(200.dp)
                )
                Column(
                    modifier = Modifier
                        .clickable { expanded = !expanded },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(text = selectedCourse)
                        Icon(imageVector = if(expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown, contentDescription = "")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(100.dp)
                            .background(Color.White),
                        offset = DpOffset(x = 10.dp, y= 10.dp)
                    ) {
                        course.forEach { c -> DropdownMenuItem(text = { Text(text = c) }, onClick = { selectedCourse = c; expanded = false }) }
                    }
                }
            }

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text(text = "Full Name", letterSpacing = 4.sp) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text(text = "Password", letterSpacing = 4.sp) },
                visualTransformation = if(seePass) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = { Icon(imageVector = if(seePass) Icons.Rounded.Key else Icons.Rounded.KeyOff, contentDescription = "Key", modifier = Modifier.clickable (
                    onClick = { seePass = !seePass }
                )) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            val context = LocalContext.current
            Button(
                onClick =
                {
                    viewModel.userID = userID
                    viewModel.fullName = fullName
                    viewModel.pass = pass
                    viewModel.selectedCourse = selectedCourse
                    roles.keys.forEach {
                        if(userID.startsWith(it)){
                            viewModel.role = roles[it].toString()
                        }
                    }
                    if(viewModel.insertUser()){
                        Toast.makeText(context, "Successfully Registered", Toast.LENGTH_LONG).show()
                        navController.popBackStack()
                        navController.navigate(Auth.LOGIN.name)
                    }
                    else Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_LONG).show()

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Register", fontSize = 16.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Already have an account?")
                Text(text = "Login Now!", modifier = Modifier.clickable(
                    onClick = {

                        navController.popBackStack()
                        navController.navigate(Auth.LOGIN.name)
                    }
                ), color = Color.Red, fontWeight = FontWeight.W600)
            }
        }


    }

}