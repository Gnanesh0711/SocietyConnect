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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.OutlinedTextFieldDefaults

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

data class ServiceRequest(
    val title: String,
    val category: String,
    val description: String,
    val status: String
)
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
                        currentScreen = "events"
                    }
                )

                DashboardCard(
                    title = "Service Requests",
                    description = "Raise and track your requests",
                    onClick = {
                        currentScreen = "service_requests"
                    }
                )

                DashboardCard(
                    title = "Member Directory",
                    description = "Find and connect with residents",
                    onClick = {
                        currentScreen = "member_directory"
                    }
                )

                DashboardCard(
                    title = "Emergency Contacts",
                    description = "Quick access to important contacts",
                    onClick = {
                        currentScreen = "emergency_contacts"
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
        "events" -> {
            EventsScreen(
                onBack = { currentScreen = "home" }
            )
        }
        "service_requests" -> ServiceRequestsScreen(
            onBack = {
                currentScreen = "home"
            }
        )
        "member_directory" -> MemberDirectoryScreen(
            onBack = {
                currentScreen = "home"
            }
        )
        "emergency_contacts" -> EmergencyContactsScreen(
            onBack = {
                currentScreen = "home"
            }
        )
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
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
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
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                ),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = announcement.first,
                        fontSize = 19.sp,
                        color = Color.White,
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
@Composable
fun EventsScreen(
    onBack: () -> Unit
) {
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
            text = "Events & Meetings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Upcoming Events",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(16.dp))

        EventCard(
            date = "14 SEP",
            title = "Community Meetup",
            description = "Meet fellow residents and discuss upcoming society activities.",
            location = "Community Hall",
            time = "5:00 PM",
            accentColor = Color(0xFF4F7CFF)
        )
        EventCard(
            date = "21 SEP",
            title = "Society Sports Day",
            description = "A fun-filled sports day for residents of all age groups.",
            location = "Society Ground",
            time = "7:00 AM",
            accentColor = Color(0xFF4CAF50)
        )

        EventCard(
            date = "28 SEP",
            title = "Residents Meeting",
            description = "Monthly meeting to discuss society updates and resident concerns.",
            location = "Community Hall",
            time = "6:30 PM",
            accentColor = Color(0xFF9C27B0)
        )

        EventCard(
            date = "02 OCT",
            title = "Festival Celebration",
            description = "Join the community for an evening of celebration and activities.",
            location = "Community Hall",
            time = "6:00 PM",
            accentColor = Color(0xFFFF9800)
        )
        EventCard(
            date = "10 OCT",
            title = "Community Clean-Up",
            description = "Residents can join together for a community cleanliness drive.",
            location = "Society Entrance",
            time = "8:00 AM",
            accentColor = Color(0xFF009688)
        )
    }
}
@Composable
fun EventCard(
    date: String,
    title: String,
    description: String,
    location: String,
    time: String,
    accentColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Colored date section
            Column(
                modifier = Modifier
                    .background(
                        color = accentColor,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 14.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Event information
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$location • $time",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        }
    }
}
@Composable
fun ServiceRequestsScreen(
    onBack: () -> Unit
) {
    var showForm by remember { mutableStateOf(false) }
    var requestTitle by remember { mutableStateOf("") }
    var requestDescription by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val requests = remember { mutableStateListOf<ServiceRequest>() }
    var requestCategory by remember { mutableStateOf("Maintenance") }
    var categoryExpanded by remember { mutableStateOf(false) }
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
        if (showForm) {
            Text(
                text = "Raise a Request",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = requestTitle,
                onValueChange = { requestTitle = it },
                label = { Text("Request Title") },
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = requestCategory,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Category") },
                    textStyle = LocalTextStyle.current.copy(
                        color = Color.Black
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = Color.Black
                    ),
                    trailingIcon = {
                        Text(
                            text = "▼",
                            color = Color.Gray,
                            modifier = Modifier.clickable {
                                categoryExpanded = !categoryExpanded
                            }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = categoryExpanded,
                    onDismissRequest = {
                        categoryExpanded = false
                    }
                ) {
                    listOf(
                        "Maintenance",
                        "Plumbing",
                        "Electrical",
                        "Cleaning",
                        "Other"
                    ).forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(category)
                            },
                            onClick = {
                                requestCategory = category
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = requestDescription,
                onValueChange = { requestDescription = it },
                label = { Text("Description") },
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    showError = requestTitle.isBlank() || requestDescription.isBlank()

                    if (!showError) {
                        requests.add(
                            ServiceRequest(
                                title = requestTitle,
                                category = requestCategory,
                                description = requestDescription,
                                status = "Submitted"
                            )
                        )
                        showForm = false
                        requestTitle = ""
                        requestDescription = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Request")
            }

            if (showError) {
                Text(
                    text = "Please enter a title and description.",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        if (!showForm) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Service Requests",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Raise and track your service requests",
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    // Form will be added next
                    showForm = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("＋ Raise a Request")
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "My Requests",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (requests.isEmpty()) {
                Text(
                    text = "No service requests yet.",
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            } else {
                requests.forEach { request ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = request.title,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Category: ${request.category}\n\n${request.description}",
                                color = Color.LightGray,
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Status: ${request.status}",
                                color = Color.LightGray,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun MemberDirectoryScreen(
    onBack: () -> Unit
) {
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Member Directory",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Find residents in your society",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Residents",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        MemberCard(
            name = "Arun Kumar",
            flat = "Block A • Flat 102",
            role = "Resident"
        )

        MemberCard(
            name = "Priya Sharma",
            flat = "Block A • Flat 205",
            role = "Resident"
        )

        MemberCard(
            name = "Rahul Verma",
            flat = "Block B • Flat 301",
            role = "Resident"
        )

        MemberCard(
            name = "Meena Krishnan",
            flat = "Block B • Flat 405",
            role = "Resident"
        )

        MemberCard(
            name = "Vikram Rao",
            flat = "Block C • Flat 201",
            role = "Resident"
        )
    }
}
@Composable
fun MemberCard(
    name: String,
    flat: String,
    role: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = flat,
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = role,
                color = Color.LightGray,
                fontSize = 13.sp
            )
        }
    }
}
@Composable
fun EmergencyContactsScreen(
    onBack: () -> Unit
) {
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Emergency Contacts",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Quick access to important contacts",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        EmergencyContactCard(
            title = "Security Desk",
            number = "Available at society gate",
            icon = "🛡"
        )

        EmergencyContactCard(
            title = "Medical Emergency",
            number = "Emergency services • 112",
            icon = "🏥",
            phoneNumber = "112"
        )

        EmergencyContactCard(
            title = "Fire & Rescue",
            number = "Emergency services • 112",
            icon = "🚒",
            phoneNumber = "112"
        )

        EmergencyContactCard(
            title = "Society Maintenance",
            number = "Available at society office",
            icon = "🔧"
        )
    }
}
@Composable
fun EmergencyContactCard(
    title: String,
    number: String,
    icon: String,
    phoneNumber: String? = null
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = icon,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = number,
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
            }

            Button(
                onClick = {
                    phoneNumber?.let {
                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:$it")
                        )
                        context.startActivity(intent)
                    }
                }
            ) {
                Text("Call")
            }
        }
    }
}