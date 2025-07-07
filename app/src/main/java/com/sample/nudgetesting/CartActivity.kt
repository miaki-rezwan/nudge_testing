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
import io.hansel.compose.smtTag
import io.hansel.compose.SmtCompose

class CartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SmtCompose.screenName = "cart_screen"
        setContent {
            CartScreen()
        }
    }
}

@Composable
fun CartScreen() {
    val cartItems = listOf(
        CartItem("Cart Item 1", 2, 9.99),
        CartItem("Cart Item 2", 1, 19.99),
        CartItem("Cart Item 3", 3, 5.99)
    )
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cart", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
            itemsIndexed(cartItems) { index, item ->
                CartItemView(item, index)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

data class CartItem(val name: String, val quantity: Int, val price: Double)

@Composable
fun CartItemView(item: CartItem, index: Int) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .smtTag(screenName = "cart_screen", tag = "cart_item_$index")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Quantity: ${item.quantity}")
            Text(text = "Price: $${item.price}")
        }
    }
}

