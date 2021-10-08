package com.wuhao.paymentpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.wuhao.paymentpage.presentation.ExpiredDateVisualTransformation
import com.wuhao.paymentpage.presentation.PANVisualTransformation
import com.wuhao.paymentpage.presentation.PaymentViewModel

@Composable
fun PaymentPayge(viewModel: PaymentViewModel = hiltViewModel()) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(

            bottomBar = {
                Text(
                    text = stringResource(id = R.string.continue_hint),
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable(onClick = {})
                        .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors =
                                arrayListOf(
                                    Color(0xFF25BC6B),
                                    Color(0xFFFFCA1C)
                                )
                            )
                        )
                        .padding(6.dp)
                )

            }

        ) {
            Column(
                modifier = Modifier
                    .background(color = Color(0x14018801))
                    .fillMaxHeight()
            ) {
                Header()
                PaymentDetail(viewModel)
                MotoType()
            }
        }
    }
}

@Composable
fun Header() {
    Row {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Close",
                    modifier = Modifier.align(Alignment.TopStart)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
            Text(
                stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
fun PaymentDetail(viewModel: PaymentViewModel = hiltViewModel()) {
    Row {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                viewModel.state.value.currency + viewModel.state.value.amount,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp, start = 30.dp, end = 30.dp)
    ) {
        Text(
            text = stringResource(id = R.string.enter_pan),
            style = MaterialTheme.typography.caption,
            fontSize = 10.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp))

        TextField(
            value = viewModel.cardNumber.value,
            onValueChange = { viewModel.cardNumber.value = it },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                letterSpacing = 5.sp,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            visualTransformation = PANVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFFFFFFF)
            )
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp, start = 30.dp, end = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.expire_date),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(2.dp))
            TextField(
                value = viewModel.expireDate.value,
                onValueChange = { viewModel.expireDate.value = it },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                visualTransformation = ExpiredDateVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFFFFFFF)
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.security_code),
                style = MaterialTheme.typography.caption
            )
            Spacer(modifier = Modifier.height(2.dp))
            TextField(
                value = viewModel.securityCode.value,
                onValueChange = {
                    viewModel.securityCode.value =
                        if (it.length < 3) it else it.subSequence(0, 3) as String
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFFFFFFF)
                )
            )
        }
    }
}


@Composable
fun MotoType() {
    //TODO  save the state in viewmodel
    var expanded by remember { mutableStateOf(false) }
    var storeOnFile by remember { mutableStateOf(true) }
    val suggestions = listOf("SINGLE MOTO", "RECURRING MOTO")
    var selectedText by remember { mutableStateOf("SINGLE MOTO") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    Column {

        Text(
            text = stringResource(id = R.string.select_moto),
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))

        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                }
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Blue
            ),
            shape = RoundedCornerShape(20),

            ) {
            Text(
                text = selectedText,
                fontSize = 30.sp
            )
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "content description",
                tint = Color.Gray,
                modifier = Modifier.padding(end = 10.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                        expanded = !expanded
                    }) {
                        Text(text = label)
                    }
                }
            }
        }

        if (selectedText == "RECURRING MOTO") {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.stored_credentials),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 30.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.height(50.dp)) {
                Text(
                    text = stringResource(id = R.string.card_stored),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
                )
                OutlinedButton(
                    onClick = { storeOnFile = true },
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 20.dp, top = 2.dp, bottom = 2.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Gray,
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                ) {
                    if (storeOnFile) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "content description", tint = Color.Gray
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.yes),
                        fontSize = 10.sp
                    )
                }
                OutlinedButton(
                    onClick = { storeOnFile = false },
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 5.dp, top = 2.dp, bottom = 2.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Gray,
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(50),
                ) {
                    if (!storeOnFile) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "content description", tint = Color.Gray
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.no),
                        fontSize = 10.sp
                    )
                }
            }
        }

    }
}