/*
 * Copyright 2019 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.appsenasoft2020.ui.utils

import android.util.Log
import com.huawei.hms.maps.model.LatLng
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * NetworkRequestManager
 */
object NetworkRequestManager {
    private const val TAG = "NetworkRequestManager"

    // Maximum number of retries.
    private const val MAX_TIMES = 10

    /**
     * @param latLng1 origin latitude and longitude
     * @param latLng2 destination latitude and longitude
     * @param listener network listener
     */
    fun getDrivingRoutePlanningResult(
        latLng1: LatLng, latLng2: LatLng,
        listener: OnNetworkListener?
    ) {
        getDrivingRoutePlanningResult(latLng1, latLng2, listener, 0, false)
    }

    /**
     * @param latLng1 origin latitude and longitude
     * @param latLng2 destination latitude and longitude
     * @param listener network listener
     * @param count last number of retries
     * @param needEncode dose the api key need to be encoded
     */
    private fun getDrivingRoutePlanningResult(
        latLng1: LatLng, latLng2: LatLng,
        listener: OnNetworkListener?, count: Int, needEncode: Boolean
    ) {
        var count = count
        val curCount = ++count
        Log.e(TAG, "current count: $curCount")
        Thread(Runnable {
            val response: Response? =
                NetClient().getDrivingRoutePlanningResult(latLng1, latLng2, needEncode)
            if (null != response && null != response.body() && response.isSuccessful) {
                try {
                    val result = response.body()!!.string()
                    listener?.requestSuccess(result)
                    return@Runnable
                } catch (e: IOException) {

                }
            }
            var returnCode = ""
            var returnDesc = ""
            var need = needEncode
            try {
                val result = response?.body()!!.string()
                val jsonObject = JSONObject(result)
                returnCode = jsonObject.optString("returnCode")
                returnDesc = jsonObject.optString("returnDesc")
            } catch (e: NullPointerException) {
                returnDesc = "Request Fail!"
            } catch (e: IOException) {
                returnDesc = "Request Fail!"
            } catch (e: JSONException) {

            }
            if (curCount >= MAX_TIMES) {
                listener?.requestFail(returnDesc)
                return@Runnable
            }
            if (returnCode == "6") {
                need = true
            }
            getDrivingRoutePlanningResult(latLng1, latLng2, listener, curCount, need)
        }).start()
    }

    interface OnNetworkListener {
        fun requestSuccess(result: String?)
        fun requestFail(errorMsg: String?)
    }
}