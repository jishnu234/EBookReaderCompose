package com.example.readerapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readerapplication.R


@Composable
fun ReaderLogo(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "A. Reader",
        color = Color.Red.copy(
            alpha = 0.5f
        ),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun EmailInput(
    modifier: Modifier,
    label: String,
    emailState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Next,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        label = label,
        valueState = emailState,
        imeAction = imeAction,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        keyboardActions = keyboardActions
    )
}

@Composable
fun InputField(
    modifier: Modifier,
    label: String,
    valueState: MutableState<String>,
    enabled: Boolean,
    imeAction: ImeAction = ImeAction.Next,
    isSingleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth(),
        enabled = enabled,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        singleLine = isSingleLine,
        keyboardActions = keyboardActions
    )
}


@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    passwordState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Done,
    passwordVisibility: MutableState<Boolean>,
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions) {


    val visualTransformation = if(passwordVisibility.value) VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            ),
        label = { Text(text = label) },
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        singleLine = true,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (passwordState.value.isNotEmpty())
                Icon(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(CircleShape)
                        .clickable {
                            passwordVisibility.value = !passwordVisibility.value
                        },
                    painter = painterResource(
                        id = if(passwordVisibility.value) R.drawable.ic_password_hide
                        else R.drawable.ic_password_show),
                    contentDescription = "password toggle")
        }
    )
}


@Composable
fun SubmitButton(
    modifier: Modifier,
    textId: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = CircleShape,
        enabled = !loading && validInputs,
        onClick = onClick
    ) {

        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}




















