package com.senac.navegacaotelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senac.navegacaotelas.screens.About
import com.senac.navegacaotelas.screens.Profile
import com.senac.navegacaotelas.screens.Register
import com.senac.navegacaotelas.ui.theme.NavegacaoTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacaoTelasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){

   val navControler = rememberNavController()

    Scaffold {
        Column (
            modifier = Modifier
                .padding(it)
        ){
            Text(
                text = "Texto fixo",
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Button(onClick = {navControler.navigate("about")}) {
                Text(text = "About")
            }

            Button(onClick = {navControler.navigate("register")}) {
                Text(text = "Register")
            }

            Button(onClick = {navControler.navigate("profile")}) {
                Text(text = "Profile")
            }

            NavHost(
                navController = navControler,
                startDestination = "profile"
            ){
                composable("profile"){
                    Profile(onProfile = {
                        navControler.navigate("register")
                    })
                }
                composable("about"){
                    About(onBack = {
                        navControler.navigateUp()
                    })
                }
                composable("register"){
                    Register(
                        onBack = {
                            navControler.navigateUp()
                        },
                        onAbout = {
                            navControler.navigate("about")
                        }
                    )
                }
            }
        }
    }
}