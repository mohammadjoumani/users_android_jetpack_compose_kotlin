package com.mmj.madarsofttask.presentation.users

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mmj.madarsofttask.R
import com.mmj.madarsofttask.domain.model.User
import com.mmj.madarsofttask.presentation.component.ItemMessage
import com.mmj.madarsofttask.presentation.main.MainViewModel
import com.mmj.madarsofttask.presentation.resources.route.ADD_USER_SCREEN
import com.mmj.madarsofttask.presentation.resources.theme.colorFemale
import com.mmj.madarsofttask.presentation.resources.theme.colorMale

@Composable
fun UsersScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val stateAdd = mainViewModel.isAdd.value
    LaunchedEffect(stateAdd) {
        if (stateAdd) {
            viewModel.onEvent(UsersEvent.GetUsers)
            mainViewModel.isAdd.value = false
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ADD_USER_SCREEN)
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    ) {
        Box(Modifier.fillMaxSize()
        ) {
            val state = viewModel.state
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (state.value.isSuccess) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    item {
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                    val users = state.value.users
                    items(count = users.size) {
                        ItemUser(users[it])
                    }
                }
            } else if (state.value.isEmpty) {
                ItemMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.strNoData)
                )
            } else if (state.value.isError) {
                ItemMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.strError)
                )
            }
        }
    }
}

@Composable
private fun ItemUser(user: User) {
    Card(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = if(user.gender == 0)
                    painterResource(id = R.drawable.img_male_place_holder)
                else
                    painterResource(id = R.drawable.img_female_place_holder),
                contentDescription = "Image User",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )

            Spacer(modifier = Modifier.padding(4.dp))
            Column(
                modifier = Modifier.weight(1.0f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = if (user.gender == 0) colorMale else colorFemale
                            )
                            .width(25.dp)
                            .height(15.dp)
                    ) {
                        Icon(
                            painter = if (user.gender == 0) painterResource(id = R.drawable.ic_male)
                            else painterResource(id = R.drawable.ic_female),
                            contentDescription = null,
                            modifier = Modifier
                                .size(10.dp)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(2.dp))

                Text(
                    text = stringResource(id = R.string.strAge) + ": " + "${user.age}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = stringResource(id = R.string.strJob) + ": " + "${user.jobTitle}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

