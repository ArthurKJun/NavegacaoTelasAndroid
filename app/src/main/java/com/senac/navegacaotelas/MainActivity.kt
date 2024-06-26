package com.senac.navegacaotelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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

private fun isSelected(currentDestination: NavDestination?, route:String): Boolean {
    return currentDestination?.hierarchy?.any {it.route == route} == true
}
@Composable
fun MyApp(){

   val navControler = rememberNavController()

    Scaffold (
        bottomBar = {

            val navBackStackEntry = navControler.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.value?.destination

            BottomNavigation {

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "profile"),
                    onClick = { navControler.navigate("profile") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = ""
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "register"),
                    onClick = { navControler.navigate("register") },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.AddCircle,
                            contentDescription = ""
                        )
                    }
                )

                BottomNavigationItem(
                    selected = isSelected(currentDestination, "about"),
                    onClick = { navControler.navigate("about") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = ""
                        )
                    }
                )

            }

        }
    ){
        Column (
            modifier = Modifier
                .padding(it)
        ){
           /* Text(
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
            }*/

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