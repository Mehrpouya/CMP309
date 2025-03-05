package com.example.practicetwo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.data.position
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.values
import com.example.practicetwo.ui.theme.PracticeTwoTheme
import kotlin.io.path.moveTo
import kotlin.ranges.coerceIn

class SensorsExercise : ComponentActivity() {

    private lateinit var sensorManager: SensorManager
    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        soundPlayer = SoundPlayer(this)
        setContent {
            SensorScreen(sensorManager, soundPlayer)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        soundPlayer.release()
    }
}
@Composable
fun SensorScreen(sensorManager: SensorManager, soundPlayer: SoundPlayer) {
    var accelerometerData by remember { mutableStateOf("Accelerometer: Not Available") }
    var gyroscopeData by remember { mutableStateOf("Gyroscope: Not Available") }
    var lightSensorData by remember { mutableStateOf("Light Sensor: Not Available") }
    var gyroY by remember { mutableStateOf(1.0f) }



    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    SensorEffect(sensorManager, accelerometer) { event ->
        accelerometerData = "Accelerometer:\n x: ${event.values[0]}\n y: ${event.values[1]}\n z: ${event.values[2]}"
    }

    SensorEffect(sensorManager, gyroscope) { event ->
        gyroscopeData = "Gyroscope:\n x: ${event.values[0]}\n y: ${event.values[1]}\n z: ${event.values[2]}"
            val rotationRate = event.values[1] // Use the y-axis rotation
            val mappedRate = mapGyroscopeToPitch(rotationRate)
        if(gyroY>=mappedRate)
        gyroY = mappedRate
           // soundPlayer.playSound(gyroY)

    }

    SensorEffect(sensorManager, lightSensor) { event ->
        lightSensorData = "Light Sensor:\n ${event.values[0]} lux"
    }

    Column {
        Text(text = accelerometerData)
        Text(text = gyroscopeData)
        Text(text = lightSensorData)
        Button(onClick = {
            soundPlayer.playSound(gyroY)
        }) {
            Text("Play Sound")
        }
    }
}

@Composable
fun SensorEffect(
    sensorManager: SensorManager,
    sensor: Sensor?,
    onSensorChanged: (SensorEvent) -> Unit
) {
    val sensorEventListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                onSensorChanged(event)
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                // Handle accuracy changes if needed
            }
        }
    }

    LaunchedEffect(sensor) {
        if (sensor != null) {
            sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    DisposableEffect(sensor) {
        onDispose {
            if (sensor != null) {
                sensorManager.unregisterListener(sensorEventListener)
            }
        }
    }
}
fun mapGyroscopeToPitch(rotationRate: Float): Float {
    // Define the range of gyroscope values you expect
    val minGyroscopeValue = -5.0f
    val maxGyroscopeValue = 5.0f

    // Define the range of pitch values (playback rates) you want
    val minPitch = 0.5f // Lower pitch (slower)
    val maxPitch = 2.0f // Higher pitch (faster)

    // Clamp the gyroscope value to the defined range
    val clampedRotationRate = rotationRate.coerceIn(minGyroscopeValue, maxGyroscopeValue)

    // Map the clamped gyroscope value to the pitch range
    val mappedPitch = (clampedRotationRate - minGyroscopeValue) / (maxGyroscopeValue - minGyroscopeValue) * (maxPitch - minPitch) + minPitch

    return mappedPitch
}
