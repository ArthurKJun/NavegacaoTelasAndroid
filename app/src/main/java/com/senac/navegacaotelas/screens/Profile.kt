package com.senac.navegacaotelas.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun Profile(){

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Perfil",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfile(){
    Profile()
}