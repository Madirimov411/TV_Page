package com.uzb_khiva.videoplayerappinxml.Playin_in_AndroidStudio_Devices_Online

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.databinding.ActivityVideoPlayingBinding

class VideoPlayingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playingOnAndroidStudioVideos()

        playinOnDeviceVideos()

        playingOnInternetVideos()
    }

    private fun playinOnDeviceVideos() {
        binding.apply {

            var fileChooser = registerForActivityResult(
                ActivityResultContracts.GetContent(),
                ActivityResultCallback { uri ->
                    videoView.setVideoURI(uri)
                    videoView.start()
                }
            )

            btnClick.setOnClickListener {
                fileChooser.launch("video/*")
            }
        }
    }

    private fun playingOnAndroidStudioVideos() {
        binding.apply {
            btnClick.setOnClickListener {
                val path = "android.resource://$packageName/${R.raw.video}"
                val uri = Uri.parse(path)
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }
    }

    private fun playingOnInternetVideos() {
        binding.apply {
            btnClick.setOnClickListener {
                val path = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
                val uri = Uri.parse(path)
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }
    }
}
