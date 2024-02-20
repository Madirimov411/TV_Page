package com.uzb_khiva.videoplayerappinxml.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.adapter.FolderAdapter
import com.uzb_khiva.videoplayerappinxml.model.Video
import com.uzb_khiva.videoplayerappinxml.utils.fetchAllVideos

class MainActivity : AppCompatActivity() {

    private var folderList = ArrayList<String>()
    private var videoList = ArrayList<Video>()
    lateinit var adapter: FolderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val (fetchVideoList, fetchFolderList) = fetchAllVideos(this)

        videoList.clear()
        videoList = fetchVideoList

        folderList.clear()
        folderList = fetchFolderList

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        if (folderList != null && folderList.size > 0 && videoList != null && videoList.size > 0) {
            adapter = FolderAdapter(videoList, folderList, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            Toast.makeText(this, "Can't find any videos folder", Toast.LENGTH_SHORT).show()
        }
    }


}