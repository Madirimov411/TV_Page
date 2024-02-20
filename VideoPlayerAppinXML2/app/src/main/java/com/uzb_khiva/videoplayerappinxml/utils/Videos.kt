package com.uzb_khiva.videoplayerappinxml.utils

import android.content.Context
import android.provider.MediaStore
import com.uzb_khiva.videoplayerappinxml.R
import com.uzb_khiva.videoplayerappinxml.model.Video
import com.uzb_khiva.videoplayerappinxml.ui.VideosActivity
import java.util.Locale

fun fetchAllVideos(context: Context): Pair<ArrayList<Video>, ArrayList<String>> {

    var folderList = ArrayList<String>()
    var videos = ArrayList<Video>()
    val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

    val orderBy = MediaStore.Video.Media.DATE_ADDED + " DESC"

    val projection = arrayOf(
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.DATA,
        MediaStore.Video.Media.TITLE,
        MediaStore.Video.Media.SIZE,
        MediaStore.Video.Media.HEIGHT,
        MediaStore.Video.Media.DURATION,
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.RESOLUTION
    )

    val cursor = context.contentResolver.query(uri, projection, null, null, orderBy)

    if (cursor != null) {
        while (cursor.moveToNext()) {

            val id = cursor.getString(0)
            val path = cursor.getString(1)
            val title = cursor.getString(2)
            val size = cursor.getString(3)
            val resolution = cursor.getString(4)
            val duration = cursor.getString(5)
            val disName = cursor.getString(6)
            val width_height = cursor.getString(7)
            val videoFile =
                Video(id, path, title, size, resolution, duration, disName, width_height)

            val slashFirstIndex = path.lastIndexOf("/")
            val subString = path.substring(0, slashFirstIndex)
            if (!folderList.contains(subString)) {
                folderList.add(subString)
            }

            videos.add(videoFile)
        }
        cursor.close()
    }
    return Pair(videos, folderList)

}

fun getAllVideoFromFolder(
    context: Context,
    name: String
): ArrayList<Video> {

    val videos = ArrayList<Video>()

    val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

    val orderBy = MediaStore.Video.Media.DATE_ADDED + " DESC"
    val projection = arrayOf(
        MediaStore.Video.Media._ID,
        MediaStore.Video.Media.DATA,
        MediaStore.Video.Media.TITLE,
        MediaStore.Video.Media.SIZE,
        MediaStore.Video.Media.HEIGHT,
        MediaStore.Video.Media.DURATION,
        MediaStore.Video.Media.DISPLAY_NAME,
        MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
        MediaStore.Video.Media.RESOLUTION
    )

    var selection = MediaStore.Video.Media.DATA + " like ?"
    var selectionArgs = arrayOf("%$name%")

    val cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, orderBy)

    if (cursor != null) {
        while (cursor.moveToNext()) {

            val id = cursor.getString(0)
            val path = cursor.getString(1)
            val title = cursor.getString(2)
            val size = cursor.getInt(3)
            val resolution = cursor.getString(4)
            val duration = cursor.getInt(5)
            val disName = cursor.getString(6)
            val bucket_display_name = cursor.getString(7)
            val width_height = cursor.getString(8)

            var human_can_read: String = ""
            if (size < 1024) {
                human_can_read =
                    String.format(context.getString(R.string.size_in_b), size.toDouble())
            } else if (size < Math.pow(1024.0, 2.0)) {
                human_can_read =
                    String.format(context.getString(R.string.size_in_kb), size.toDouble() / 1024)
            } else if (size < Math.pow(1024.0, 3.0)) {
                human_can_read = String.format(
                    context.getString(R.string.size_in_mb),
                    size.toDouble() / Math.pow(1024.0, 2.0)
                )
            } else {
                human_can_read = String.format(
                    context.getString(R.string.size_in_gb),
                    size.toDouble() / Math.pow(1024.0, 3.0)
                )
            }

            var durationFormatted = ""
            val sec = (duration / 1000) % 60
            val min = (duration / (1000 * 60)) % 60
            val hrs = duration / (1000 * 60 * 60)

            if (hrs == 0) {
                durationFormatted = "${min}:${String.format(Locale.UK, "%02d", sec)}"
            } else {
                durationFormatted = "${hrs}:${String.format(Locale.UK, "%02d", min)}:${
                    String.format(
                        Locale.UK,
                        "%02d",
                        sec
                    )
                }"
            }

            val videoFile =
                Video(
                    id,
                    path,
                    title,
                    human_can_read,
                    resolution,
                    durationFormatted,
                    disName,
                    width_height
                )
            if (name.endsWith(bucket_display_name)) {
                videos.add(videoFile)
            }
        }
        cursor.close()
    }

    return videos
}