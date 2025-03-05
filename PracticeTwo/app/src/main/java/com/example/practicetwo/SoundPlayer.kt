package com.example.practicetwo

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log
class SoundPlayer(context: Context) {
    private val soundPool: SoundPool
    private val soundId: Int
    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1) // You can increase this if you need to play multiple sounds simultaneously
            .setAudioAttributes(audioAttributes)
            .build()

        // Load the sound from the assets folder
        val assetManager = context.assets
        val descriptor = assetManager.openFd("aNote.wav") // Replace "sound.wav" with your sound file
        soundId = soundPool.load(descriptor, 1)
    }
    fun playSound(rate: Float = 1.0f) {
        // Check if the sound is loaded
        if (soundId == 0) {
            Log.e("SoundPlayer", "Sound not loaded")
            return
        }
        soundPool.play(soundId, 1f, 1f, 1, 0, rate)
    }
    fun release() {
        soundPool.release()
    }
}