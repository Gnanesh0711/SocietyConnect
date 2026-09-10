package com.societyconnect.app.data

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class ServiceRequest(
    val title: String,
    val category: String,
    val description: String,
    val status: String = "Submitted"
)

class ServiceRequestRepository(context: Context) {
    private val preferences = context.getSharedPreferences("society_connect_requests", Context.MODE_PRIVATE)

    fun load(): List<ServiceRequest> = runCatching {
        val items = JSONArray(preferences.getString(KEY_REQUESTS, "[]"))
        List(items.length()) { index ->
            val item = items.getJSONObject(index)
            ServiceRequest(
                title = item.getString("title"),
                category = item.getString("category"),
                description = item.getString("description"),
                status = item.optString("status", "Submitted")
            )
        }
    }.getOrDefault(emptyList())

    fun save(requests: List<ServiceRequest>) {
        val items = JSONArray()
        requests.forEach { request ->
            items.put(
                JSONObject()
                    .put("title", request.title)
                    .put("category", request.category)
                    .put("description", request.description)
                    .put("status", request.status)
            )
        }
        preferences.edit().putString(KEY_REQUESTS, items.toString()).apply()
    }

    private companion object {
        const val KEY_REQUESTS = "requests"
    }
}
