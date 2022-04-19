package com.example.readerapplication.screens.login

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.readerapplication.R
import com.example.readerapplication.components.EmailInput
import com.example.readerapplication.components.PasswordInput
import com.example.readerapplication.components.ReaderLogo
import com.example.readerapplication.components.SubmitButton
import com.example.readerapplication.navigation.ReaderBookScreens


@ExperimentalComposeUiApi
@Composable
fun ReaderLoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {

    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    Surface(
        modifier = Modifier
            .padding()
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReaderLogo(
                modifier = Modifier.padding(
                    top = 24.dp
                )
            )
            if (showLoginForm.value) {
                UserForm(loading = false, isCreateAccount = false)
                { email,password ->
                    viewModel.signInWithEmailAndPassword( email, password ){
                        navController.navigate(ReaderBookScreens.HomeScreen.name)
                    }
                }
            }
            else UserForm(loading = false, isCreateAccount = true)
            { email, password ->
                viewModel.createAccountWithEmailAndPassword(email, password){
                    navController.navigate(ReaderBookScreens.HomeScreen.name)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val text = if (showLoginForm.value) "SignUp" else "Login"
                Text("New User?")
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { showLoginForm.value = !showLoginForm.value },
                    text = text,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF064D46).copy(alpha = 0.7f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@ExperimentalComposeUiApi
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, pwd ->
        Log.d("Form Response", "UserForm: $email and $pwd")
    }
) {

    val emailState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordState = rememberSaveable {
        mutableStateOf("")
    }
    val valid = rememberSaveable(emailState.value, passwordState.value) {
        emailState.value.trim().isNotEmpty() && passwordState.value.trim().isNotEmpty()
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    // to request focus to next field
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current

    val modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
        .height(250.dp)
        .verticalScroll(rememberScrollState())

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        if (isCreateAccount)
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = stringResource(id = R.string.create_acc_text))
        EmailInput(
            modifier = Modifier,
            label = "Email",
            emailState = emailState,
            keyboardActions = KeyboardActions {
                passwordFocusRequest.requestFocus()
            },
            enabled = !loading
        )
        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            label = "Password",
            passwordState = passwordState,
            passwordVisibility = passwordVisibility,
            enabled = !loading,
            keyboardActions = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(emailState.value.trim(), passwordState.value.trim())
            }
        )
        SubmitButton(
            modifier = Modifier,
            textId = if (!isCreateAccount) "Login" else "Create Account",
            loading = loading,
            validInputs = valid
        ) {
            keyboardController?.hide()
            onDone(emailState.value.trim(), passwordState.value.trim())
        }
    }
}




