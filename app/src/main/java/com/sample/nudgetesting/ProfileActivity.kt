package com.sample.nudgetesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import io.hansel.compose.SmtCompose
import io.hansel.compose.smtTag

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SmtCompose.screenName = "profile_screen"
        setContent {
            ProfileScreen()
        }
    }
}

@Composable
fun ProfileScreen() {
    val userDetails = listOf(
        UserDetail("Name", "John Doe"),
        UserDetail("Email", "john.doe@example.com"),
        UserDetail("Membership", "Gold"),
        UserDetail("Address", "123 Main St, City")
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
            itemsIndexed(userDetails) { index, detail ->
                UserDetailItem(detail, index)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class UserDetail(val label: String, val value: String)

@Composable
fun UserDetailItem(detail: UserDetail, index: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .smtTag(screenName = "profile_screen", tag = "user_detail_$index")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = detail.label, style = MaterialTheme.typography.titleMedium)
            Text(text = detail.value)
        }
    }
}

