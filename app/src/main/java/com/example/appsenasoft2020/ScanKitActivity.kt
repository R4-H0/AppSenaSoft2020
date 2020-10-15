package com.example.appsenasoft2020

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions


class ScanKitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

    }

    private val CAMERA_REQ_CODE: Int = 1;
    private val REQUEST_CODE_SCAN_ONE: Int = 2;
    private var container: ViewGroup? = null
    private var inflater: LayoutInflater? = null


    @RequiresApi(Build.VERSION_CODES.M)
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.container = container
        this.inflater = inflater

        val root = inflater.inflate(R.layout.activity_scan, container, false)
        val btnLeer: Button = root.findViewById(R.id.btnLeerCodigo)

        btnLeer.setOnClickListener {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE),
                CAMERA_REQ_CODE
            )
        }

        return root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
// Check whether requestCode is set to the value of CAMERA_REQ_CODE during permission application, and then check whether the permission is enabled.
        if (requestCode == CAMERA_REQ_CODE && grantResults.size == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            // Call the barcode scanning API to build the scanning capability.

            val options = HmsScanAnalyzerOptions.Creator()
                .setHmsScanTypes(HmsScan.PDF417_SCAN_TYPE, HmsScan.QRCODE_SCAN_TYPE).create()

            ScanUtil.startScan(this, REQUEST_CODE_SCAN_ONE, options)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }
        if (requestCode == 2) {
            val obj: HmsScan? = data.getParcelableExtra(ScanUtil.RESULT)
            obj?.let {

                var resultado : TextView = findViewById(R.id.txtResultado)
                resultado?.setText(obj.originalValue)
            }
        }


    }


}