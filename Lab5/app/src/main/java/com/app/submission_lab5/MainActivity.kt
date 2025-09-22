package com.app.submission_lab5

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.ImageView
import android.view.View
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var accelSensor: Sensor? = null
    private lateinit var imageView: ImageView
    private lateinit var container: View
    private val gravity = FloatArray(3)
    private val alpha = 0.85f
    private var sensitivity = 14f
    private var rotationSensitivity = 3.5f
    private val maxRotationDeg = 25f
    private var maxTranslationX = 0f
    private var maxTranslationY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        container = findViewById(R.id.main)
        imageView = findViewById(R.id.imageView2)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        imageView.post {
            computeMaxTranslations()
            imageView.translationX = 0f
            imageView.translationY = 0f
            imageView.rotation = 0f
            //Log.d("SENSOR_DEBUG", "Computed maxTranslationX=$maxTranslationX, maxTranslationY=$maxTranslationY")
        }
    }

    private fun computeMaxTranslations() {
        val containerW = container.width.toFloat()
        val containerH = container.height.toFloat()
        val imageW = imageView.width.toFloat()
        val imageH = imageView.height.toFloat()

        maxTranslationX = max(0f, (containerW - imageW) / 2f)
        maxTranslationY = max(0f, (containerH - imageH) / 2f)
    }

    override fun onResume() {
        super.onResume()
        accelSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER) return

        val rawX = event.values[0]
        val rawY = event.values[1]
        val rawZ = event.values[2]

        gravity[0] = alpha * gravity[0] + (1 - alpha) * rawX
        gravity[1] = alpha * gravity[1] + (1 - alpha) * rawY
        gravity[2] = alpha * gravity[2] + (1 - alpha) * rawZ

        val tiltX = -gravity[0]
        val tiltY = gravity[1]

        var targetX = tiltX * sensitivity
        var targetY = tiltY * sensitivity

        if (maxTranslationX > 0f) {
            targetX = min(maxTranslationX, max(-maxTranslationX, targetX))
        }
        if (maxTranslationY > 0f) {
            targetY = min(maxTranslationY, max(-maxTranslationY, targetY))
        }

        imageView.translationX = targetX
        imageView.translationY = targetY

        var rot = tiltX * rotationSensitivity
        rot = min(maxRotationDeg, max(-maxRotationDeg, rot))
        imageView.rotation = rot

        //Log.d("SENSOR_DEBUG", "raw=($rawX,$rawY,$rawZ) gravity=(${gravity[0]},${gravity[1]},${gravity[2]}) target=($targetX,$targetY) rot=$rot")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // interface import
    }
}