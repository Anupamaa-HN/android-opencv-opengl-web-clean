package com.example.opedge

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.hardware.camera2.*
import android.os.Bundle
import android.view.TextureView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textureView: TextureView
    private lateinit var cameraManager: CameraManager
    private var cameraDevice: CameraDevice? = null
    private var session: CameraCaptureSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textureView = TextureView(this)
        val layout = FrameLayout(this)
        layout.addView(textureView)
        setContentView(layout)

        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val cameraId = cameraManager.cameraIdList[0]

        try {
            cameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
                override fun onOpened(device: CameraDevice) {
                    cameraDevice = device
                    startCameraPreview()
                }

                override fun onDisconnected(device: CameraDevice) {
                    device.close()
                }

                override fun onError(device: CameraDevice, error: Int) {
                    device.close()
                }
            }, null)
        } catch (e: Exception) {
            Toast.makeText(this, "Error opening camera", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCameraPreview() {
        val surfaceTexture = textureView.surfaceTexture ?: return

        val surface = android.view.Surface(surfaceTexture)

        cameraDevice?.createCaptureSession(
            listOf(surface),
            object : CameraCaptureSession.StateCallback() {
                override fun onConfigured(session: CameraCaptureSession) {
                    val request = cameraDevice!!.createCaptureRequest(
                        CameraDevice.TEMPLATE_PREVIEW
                    )
                    request.addTarget(surface)
                    session.setRepeatingRequest(request.build(), null, null)
                }

                override fun onConfigureFailed(session: CameraCaptureSession) {}
            }, null
        )
    }
}
