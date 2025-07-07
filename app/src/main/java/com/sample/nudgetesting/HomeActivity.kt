package com.sample.nudgetesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.hansel.compose.smtTag
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import io.hansel.compose.SmtCompose

class HomeActivity : ComponentActivity() {
    private val profileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle profile result if needed
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SmtCompose.screenName = "home_screen"
        setContent {
            HomeScreen(
                onProfileClick = {
                    val intent = Intent(this, ProfileActivity::class.java)
                    profileLauncher.launch(intent)
                },
                onCartClick = {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivityForResult(intent, 1001)
                },
                onDashboardClick = {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun HomeScreen(
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    onDashboardClick: () -> Unit
) {
    val products = listOf(
        Product("Product A", 19.99),
        Product("Product B", 29.99),
        Product("Product C", 39.99)
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Home", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
            itemsIndexed(products) { index, product ->
                ProductItem(product, index)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onProfileClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Profile")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCartClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Cart")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onDashboardClick, modifier = Modifier.fillMaxWidth()) {
            Text("Go to Dashboard")
        }
    }
}

data class Product(val name: String, val price: Double)

@Composable
fun ProductItem(product: Product, index: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .smtTag(screenName = "home_screen", tag = "product_item_$index")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Price: $${product.price}")
        }
    }
}

