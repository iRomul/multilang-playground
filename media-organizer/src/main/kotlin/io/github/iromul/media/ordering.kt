package io.github.iromul.media

import io.github.iromul.media.playlist.PlaylistData
import java.io.File

fun reorderByPlaylist(files: List<File>, playlistData: PlaylistData): List<File> {
    return playlistData.map { playlistEntry ->
        val fileInList = files.find { winAwareNameWeakMatch(it.name, playlistEntry.name) }
            ?: throw IllegalStateException("Playlist entry '${playlistEntry.name}' is not found in directory")

        fileInList
    }
}
