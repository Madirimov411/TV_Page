package com.uzb_khiva.videoplayerappinxml.CustomPlayer

import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.databinding.ActivityWatchBinding

class WatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchBinding
    private lateinit var btnFullScreen: ImageView
    private lateinit var btnLockScreen: ImageView
    private lateinit var secControlMid: LinearLayout
    private lateinit var secControlBottom: LinearLayout
    private lateinit var simpleExoPlayer: SimpleExoPlayer

    private var isFullScreen = false
    private var isLock = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnFullScreen = findViewById(R.id.exo_fullscreen)
        btnLockScreen = findViewById(R.id.exo_lock)
        secControlMid = findViewById<LinearLayout>(R.id.secControlVid1)
        secControlBottom = findViewById<LinearLayout>(R.id.secControlVid2)
        initViews()

        btnFullScreen.setOnClickListener {
            fullScreen()
        }

        btnLockScreen.setOnClickListener {
            lockScreen()
        }
    }


    private fun fullScreen() {
        if (!isFullScreen) {
            btnFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    this@WatchActivity,
                    R.drawable.ic_fullscreen_exit
                )
            )
            //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
        } else {

            btnFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    this@WatchActivity,
                    R.drawable.ic_fullscreen
                )
            )
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
        isFullScreen = !isFullScreen
    }

    private fun lockScreen() {
        if (!isLock) {
            btnLockScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    this@WatchActivity,
                    R.drawable.ic_lock
                )
            )
        } else {
            btnLockScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    this@WatchActivity,
                    R.drawable.ic_lock_open
                )
            )
        }
        isLock = !isLock

        if (isLock) {
            secControlMid.visibility = View.INVISIBLE
            secControlBottom.visibility = View.INVISIBLE
        } else {
            secControlMid.visibility = View.VISIBLE
            secControlBottom.visibility = View.VISIBLE
        }

    }

    private fun initViews() {
        binding.apply {

            simpleExoPlayer = SimpleExoPlayer
                .Builder(this@WatchActivity)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build()


            exoPlayerView.player = simpleExoPlayer
            exoPlayerView.keepScreenOn = true
            simpleExoPlayer.addListener(object : Player.Listener {

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    if (playbackState == Player.STATE_BUFFERING) {
                        progressBar.visibility = View.VISIBLE
                    } else if (playbackState == Player.STATE_READY) {
                        progressBar.visibility = View.GONE
                    }
                }

            })

            val videoSource =
                Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")

            val mediaItem = MediaItem.fromUri(videoSource)
            simpleExoPlayer.setMediaItem(mediaItem)

            simpleExoPlayer.prepare()
            simpleExoPlayer.play()
        }
    }

    override fun onBackPressed() {
        if (isLock) return
        if (isFullScreen) fullScreen()
        else super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.pause()
    }
}