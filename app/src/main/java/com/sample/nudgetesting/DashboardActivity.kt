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

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SmtCompose.screenName = "dashboard_screen"
        setContent {
            DashboardScreen()
        }
    }
}

@Composable
fun DashboardScreen() {
    val stats = listOf(
        DashboardStat("Orders", 120),
        DashboardStat("Revenue", 5400),
        DashboardStat("Visitors", 3200),
        DashboardStat("Tasks", 7)
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
            itemsIndexed(stats) { index, stat ->
                DashboardStatItem(stat, index)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class DashboardStat(val label: String, val value: Int)

@Composable
fun DashboardStatItem(stat: DashboardStat, index: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .smtTag(screenName = "dashboard_screen", tag = "dashboard_stat_$index")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stat.label, style = MaterialTheme.typography.titleMedium)
            Text(text = stat.value.toString())
        }
    }
}

