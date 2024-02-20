package com.uzb_khiva.videoplayerappinxml.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.adapter.VideosAdapter
import com.uzb_khiva.videoplayerappinxml.model.Video
import com.uzb_khiva.videoplayerappinxml.utils.getAllVideoFromFolder

class VideosActivity : AppCompatActivity() {


    private var name: String = ""
    private var videoList = ArrayList<Video>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: VideosAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
        initViews()
    }

    private fun initViews() {
        name = intent.getStringExtra("folderName").toString()
        recyclerView = findViewById<RecyclerView>(R.id.videoRecyclerView)
        loadVideos()
    }

    private fun loadVideos() {
        videoList.clear()
        videoList = getAllVideoFromFolder(this, name)
        if (name != null && videoList.size > 0) {
            adapter = VideosAdapter(videoList, this)
            recyclerView.setHasFixedSize(true)
            recyclerView.setItemViewCacheSize(20)
            recyclerView.isDrawingCacheEnabled = true
            recyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            recyclerView.isNestedScrollingEnabled = false

            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            Toast.makeText(this, "Can't find any videos", Toast.LENGTH_SHORT).show()
        }
    }


}