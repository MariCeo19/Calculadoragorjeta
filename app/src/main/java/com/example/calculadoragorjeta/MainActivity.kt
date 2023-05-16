package com.example.calculadoragorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoragorjeta.ui.theme.CalculadoragorjetaTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoragorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Calculadora()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Calculadora(){

    var valorEntrada by remember { mutableStateOf("") }
    var gorjeta = 0.0
    var percentagemGorjeta by remember { mutableStateOf("" ) }

    val focusManager = LocalFocusManager.current

    gorjeta = CalcularGorjeta(valorEntrada,percentagemGorjeta)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
           )
    {
    }
    Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,

    modifier = Modifier.fillMaxSize()
          )
    {
     Text(
         text = "Calculadora de Gorjeta",
         fontSize = 30.sp,
         fontWeight = FontWeight.SemiBold,
         modifier = Modifier
          .padding(top = 40.dp)
         )
        CampoTextoEditavel(
            value = valorEntrada ,
            label = "Valor de Entrada" ,
            onvalueChange = {valorEntrada= it},
            imeAction = ImeAction.Next,
            keyboardActions = KeyboardActions (
                onNext = {focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        CampoTextoEditavel(
            value = percentagemGorjeta,
            label = "% Gorjeta",
            onvalueChange = {percentagemGorjeta= it},
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions (
                onNext = {focusManager.clearFocus() }
            )
        )
     Text(
          text = "Valor da Gorjeta ${NumberFormat.getCurrencyInstance().format(gorjeta)}",
         fontSize = 25.sp,
         fontWeight = FontWeight.Normal,
         modifier = Modifier
             .padding(top = 30.dp),
         )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoTextoEditavel (
    value: String,
    label: String,
    onvalueChange: (String) -> Unit,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions
) {
      TextField(
        value = value ,
        label= {
            Text(
                text = label
                )
               },
        onValueChange = onvalueChange,
        modifier = Modifier
            .padding(top = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

fun CalcularGorjeta (
    valorEntrada: String,
    percentagemGorjeta: String
):Double{
     return  (valorEntrada.toDoubleOrNull()?:0.0)*(percentagemGorjeta.toDoubleOrNull()?:0.0)/100
}