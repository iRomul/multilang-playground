package io.github.iromul.media.meta

import io.github.iromul.media.enumify
import io.github.iromul.media.playlist.Entry
import io.github.iromul.media.playlist.PlaylistData
import java.io.File
import java.util.*

fun buildDirectoryMeta(directory: File): DirectoryMeta {
    val meta = DirectoryMeta()

    val metafile = File(directory, "meta.properties")

    if (metafile.exists() && metafile.isFile) {
        val props = Properties()

        metafile.inputStream().use(props::load)

        if (props.containsKey("kind")) {
            when (props["kind"]) {
                "playlist" -> {
                    meta.kind = DirectoryKind.PLAYLIST

                    if (props.containsKey("playlist.file")) {
                        val playlistFile = File(directory, props["playlist.file"].toString())

                        if (!playlistFile.exists() || !playlistFile.isFile) {
                            throw IllegalStateException("Playlist file is not readable")
                        }

                        meta.playlistData = loadPlaylistData(playlistFile)
                    }
                }
            }
        }

        if (props.containsKey("order")) {
            meta.order = TracksOrder.valueOf(enumify(props["order"]))

            if (meta.order == TracksOrder.PLAYLIST) {
                if (meta.playlistData == null) {
                    throw IllegalStateException("Can't perform playlist order without playlist file")
                }
            }
        }

        if (props.containsKey("numeration.in")) {
            meta.numeration = Numeration.valueOf(enumify(props["numeration.in"]))
        }
    }

    return meta
}

fun loadPlaylistData(file: File): PlaylistData {
    val data = PlaylistData()

    file.useLines { lineSequence ->
        lineSequence.drop(1).forEach { line ->
            val columns = line.split("\t")

            data.addEntry(Entry(name = columns[0], artist = columns[1], composer = columns[2], album = columns[3]))
        }
    }

    return data
}
