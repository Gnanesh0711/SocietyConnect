package com.societyconnect.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.societyconnect.app.ui.theme.SocietyConnectTheme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.runtime.*
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SocietyConnectTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var currentScreen by remember {
        mutableStateOf("home")
    }
    when (currentScreen) {
        "home" -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(start = 20.dp, end = 20.dp, top = 98.dp)
            ) {

                Text(
                    text = "SocietyConnect",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "Connecting communities, one place.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 28.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(40.dp))
                DashboardCard(
                    title = "Announcements",
                    description = "Stay updated with society news",
                    onClick = {
                        currentScreen = "announcements"
                    }
                )

                DashboardCard(
                    title = "Events & Meetings",
                    description = "Discover upcoming community events",
                    onClick = {

                    }
                )

                DashboardCard(
                    title = "Service Requests",
                    description = "Raise and track your requests",
                    onClick = {

                    }
                )

                DashboardCard(
                    title = "Member Directory",
                    description = "Find and connect with residents",
                    onClick = {

                    }
                )

                DashboardCard(
                    title = "Emergency Contacts",
                    description = "Quick access to important contacts",
                    onClick = {

                    }
                )
            }
        }

        "announcements" -> {
            AnnouncementsScreen(
                onBack = {
                    currentScreen = "home"
                }
            )
        }
    }
}
    @Composable
    fun DashboardCard(
        title: String,
        description: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(36.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = description,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }

@Composable
fun AnnouncementsScreen(
    onBack: () -> Unit
) {
    val announcements = listOf(
        Triple(
            "Water Supply Maintenance",
            "Water supply will be temporarily unavailable tomorrow from 10 AM to 1 PM.",
            "8 Sep 2026"
        ),
        Triple(
            "Community Meeting",
            "A society meeting will be held in the community hall this Saturday at 5 PM.",
            "7 Sep 2026"
        ),
        Triple(
            "Parking Area Cleaning",
            "The basement parking area will be cleaned on Friday. Please move vehicles before 9 AM.",
            "5 Sep 2026"
        ),
        Triple(
            "Lift Maintenance",
            "The lift in Block A will be unavailable for maintenance tomorrow from 2 PM to 4 PM.",
            "4 Sep 2026"
        ),
        Triple(
            "Garden Maintenance",
            "The society garden will undergo routine maintenance this weekend. Please avoid the area during the work.",
            "3 Sep 2026"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Button(
            onClick = onBack
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Announcements",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        announcements.forEach { announcement ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = announcement.first,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = announcement.second,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = announcement.third,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}