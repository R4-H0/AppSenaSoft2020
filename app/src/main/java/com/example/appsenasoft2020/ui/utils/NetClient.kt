package com.example.appsenasoft2020.ui.utils

import com.huawei.hms.maps.model.LatLng
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.concurrent.TimeUnit


class NetClient() {

    private val mDefaultKey = "CgB6e3x9lYkSCh21PbhPyDBk0yTP+CuSI9JiFibnZhkglf3mNf7t+HC564h813HU0BO7PWQ9/JbkL/JW2JvduA1X"

    private val mDrivingRoutePlanningURL =
        "https://mapapi.cloud.huawei.com/mapApi/v1/routeService/driving"

    fun initOkHttpClient(): OkHttpClient? {
        if (client == null) {
            client = OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build()
        }
        return client
    }

    fun getDrivingRoutePlanningResult(
        latLng1: LatLng,
        latLng2: LatLng,
        needEncode: Boolean
    ): Response? {
        var key = mDefaultKey
        if (needEncode) {
            try {
                key = URLEncoder.encode(mDefaultKey, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }
        val url = "$mDrivingRoutePlanningURL?key=$key"
        var response: Response? = null
        val origin = JSONObject()
        val destination = JSONObject()
        val json = JSONObject()
        try {
            origin.put("lat", latLng1.latitude)
            origin.put("lng", latLng1.longitude)
            destination.put("lat", latLng2.latitude)
            destination.put("lng", latLng2.longitude)
            json.put("origin", origin)
            json.put("destination", destination)
            val requestBody = RequestBody.create(JSON, json.toString())
            val request = Request.Builder().url(url).post(requestBody).build()
            response = netClient!!.initOkHttpClient()!!.newCall(request).execute()
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return response
    }

    companion object {
        private const val TAG = "NetClient"
        var netClient: NetClient? = null
            get() {
                if (field == null) {
                    field = NetClient()
                }
                return field
            }
            private set
        private var client: OkHttpClient? = null
        private val JSON = MediaType.parse("application/json; charset=utf-8")
    }

    init {
        client = initOkHttpClient()
    }
}