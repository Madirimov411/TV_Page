package com.uzb_khiva.videoplayerappinxml.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.model.Video
import java.io.File

class VideosAdapter(
    val videoFolder: ArrayList<Video>,
    val context: Context
) : RecyclerView.Adapter<VideosAdapter.VideoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.files_view, parent, false)
        )
    }

    override fun getItemCount(): Int = videoFolder.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoDetail = videoFolder[position]
        holder.apply {
            Glide.with(context).load(videoDetail.path).into(thumbnail)
            videoTitle.text = videoDetail.title
            videoSize.text = videoDetail.size
            videoDuration.text = videoDetail.duration
            menu.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
                val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.file_menu, null)

                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()

                bottomSheetView.findViewById<ImageView>(R.id.menu_down).setOnClickListener {
                    bottomSheetDialog.dismiss()
                }

                bottomSheetView.findViewById<LinearLayout>(R.id.menu_rename)
                    .setOnClickListener { view ->
                        bottomSheetDialog.dismiss()
                        renameFiles(position, view)
                    }
                bottomSheetView.findViewById<LinearLayout>(R.id.menu_share).setOnClickListener {
                    bottomSheetDialog.dismiss()
                    shareFile(position)
                }
                bottomSheetView.findViewById<LinearLayout>(R.id.menu_delete)
                    .setOnClickListener { view ->
                        bottomSheetDialog.dismiss()
                        deleteFile(position, view)
                    }
                bottomSheetView.findViewById<LinearLayout>(R.id.menu_properties)
                    .setOnClickListener {
                        bottomSheetDialog.dismiss()
                        showProperties(position)
                    }
            }
        }

    }

    private fun shareFile(index: Int) {
        val uri = Uri.parse(videoFolder[index].path)
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("video/*")
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        context.startActivity(Intent.createChooser(intent, "share file"))
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
    }

    private fun deleteFile(index: Int, view: View) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Delete")
            .setMessage(videoFolder[index].title)
            .setNegativeButton("Cansel") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("Ok") { dialog, which ->
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    videoFolder[index].id.toLong()
                )
                val file = File(videoFolder[index].path)
                val delete = file.delete()
                if (delete) {
                    context.applicationContext.contentResolver.delete(
                        contentUri,
                        null,
                        null
                    )
                    videoFolder.remove(videoFolder[index])
                    notifyItemRemoved(index)
                    notifyItemRangeChanged(index, videoFolder.size)
                    Snackbar.make(view, "File deleted successfully", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, "File deleted failed", Snackbar.LENGTH_SHORT).show()
                }
            }
        alertDialog.create().show()
    }

    private fun renameFiles(index: Int, view: View) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.file_rename_layout)
        val editText = dialog.findViewById<EditText>(R.id.rename_edit_text)
        val cancelBtn = dialog.findViewById<TextView>(R.id.cancel_rename_button)
        val renameBtn = dialog.findViewById<TextView>(R.id.rename_button)
        val renameFile = File(videoFolder[index].path)
        var nameText = renameFile.name
        nameText = nameText.substring(0, nameText.lastIndexOf("."))
        editText.setText(nameText)
        editText.clearFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        renameBtn.setOnClickListener {
            val onlyPath = renameFile.parentFile.absolutePath
            val ext = renameFile.absolutePath.substring(renameFile.absolutePath.lastIndexOf("."))
            val newPath = "$onlyPath/${editText.text}$ext"
            val newFile = File(newPath)
            val result = renameFile.renameTo(newFile)
            if (result) {
                context.applicationContext.contentResolver.delete(
                    MediaStore.Files.getContentUri("external"),
                    MediaStore.MediaColumns.DATA + "=?",
                    arrayOf(renameFile.absolutePath)
                )
                val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                intent.setData(Uri.fromFile(newFile))
                context.applicationContext.sendBroadcast(intent)
                Snackbar.make(view, "File renamed successfully", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(view, "File renamed failed", Snackbar.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showProperties(index: Int) {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.file_properties)
        val name = videoFolder[index].title
        val path = videoFolder[index].path
        val size = videoFolder[index].size
        val duration = videoFolder[index].duration
        val resolution = videoFolder[index].resolution

        dialog.findViewById<TextView>(R.id.pro_title).text = name
        dialog.findViewById<TextView>(R.id.pro_storage).text = path
        dialog.findViewById<TextView>(R.id.pro_size).text = size
        dialog.findViewById<TextView>(R.id.pro_duration).text = duration
        dialog.findViewById<TextView>(R.id.pro_resolution).text = "${resolution}p"
        dialog.show()

    }


    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail = view.findViewById<ImageView>(R.id.thumbnail)
        val videoTitle = view.findViewById<TextView>(R.id.video_title)
        val videoSize = view.findViewById<TextView>(R.id.video_size)
        val videoDuration = view.findViewById<TextView>(R.id.video_duration)
        val menu = view.findViewById<ImageView>(R.id.video_menu)
    }

}
