package com.mmj.madarsofttask.presentation.adduser

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mmj.madarsofttask.R
import com.mmj.madarsofttask.presentation.component.ItemSelectRadioButton
import com.mmj.madarsofttask.presentation.main.MainViewModel
import com.mmj.validation.presentation.component.CustomTextFieldApp

@Composable
fun AddUserScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: AddUserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    LaunchedEffect(state) {
        if (state.isSuccess) {
            navController.popBackStack()
            Toast.makeText(context, "Added success", Toast.LENGTH_LONG).show()
            mainViewModel.isAdd.value = true
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = stringResource(id = R.string.strName),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )

            CustomTextFieldApp(
                placeholder = stringResource(id = R.string.strName),
                text = state.name,
                onValueChange = {
                    viewModel.onEvent(AddUserEvent.NameChange(it))
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                isError = state.nameError != null,
                errorMessage = state.nameError,
            )

            Text(
                text = stringResource(id = R.string.strJob),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp)
            )

            CustomTextFieldApp(
                placeholder = stringResource(id = R.string.strJob),
                text = state.jobTitle,
                onValueChange = {
                    viewModel.onEvent(AddUserEvent.JobTitleChanged(it))
                },
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                singleLine = true,
                isError = state.jobTitleError != null,
                errorMessage = state.jobTitleError,
            )

            Text(
                text = stringResource(id = R.string.strGender),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )

            ItemSelectRadioButton(
                title = stringResource(id = R.string.strMale),
                onClick = {
                    viewModel.onEvent(AddUserEvent.GenderChanged(0))
                },
                isSelect = state.gender == 0
            )

            ItemSelectRadioButton(
                title = stringResource(id = R.string.strFemale),
                onClick = {
                    viewModel.onEvent(AddUserEvent.GenderChanged(1))
                },
                isSelect = state.gender == 1
            )


            Text(
                text = stringResource(id = R.string.strAge),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        viewModel.onEvent(AddUserEvent.MinusCounter)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = state.age.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                IconButton(
                    onClick = {
                        viewModel.onEvent(AddUserEvent.AddCounter)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.padding(8.dp))

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                Button(
                    onClick = {
                        viewModel.onEvent(AddUserEvent.AddUser)
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = stringResource(id = R.string.strAdd))
                }
            }
        }
    }
}