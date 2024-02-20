package com.uzb_khiva.videoplayerappinxml.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.model.Video
import com.uzb_khiva.videoplayerappinxml.ui.VideosActivity

class FolderAdapter(
    val videos: ArrayList<Video>,
    val folderNames: ArrayList<String>,
    val context: Context,
) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {


    class FolderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.folderName)
        val count = view.findViewById<TextView>(R.id.videosCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.folder_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return folderNames.size
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {

        val index = folderNames[position].lastIndexOf("/")
        val folderName = folderNames[position].substring(index + 1)
        holder.apply {
            name.text = folderName
            count.text = countVideos(folderName).toString()
            itemView.setOnClickListener {
                val intent = Intent(context, VideosActivity::class.java)
                intent.putExtra("folderName", folderName)
                context.startActivity(intent)
            }
        }

    }

    fun countVideos(folderName: String): Int {
        var count = 0
        for (video in videos) {
            if (video.path.substring(0, video.path.lastIndexOf("/")).endsWith(folderName)) {
                count++
            }
        }
        return count
    }

}