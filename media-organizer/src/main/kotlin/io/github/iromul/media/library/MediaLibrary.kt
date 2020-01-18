package io.github.iromul.media.library

import io.github.iromul.media.library.collection.MediaCollection
import io.github.iromul.media.library.collection.MediaCollectionType
import io.github.iromul.media.library.collection.MediaCollectionType.*
import io.github.iromul.media.library.layout.MediaCollectionLayout
import mu.KotlinLogging
import java.io.File

class MediaLibrary(
    private val mediaRoot: File,
    private val layout: MediaCollectionLayout
) {

    private val logger = KotlinLogging.logger {}

    fun forEachCollection(action: (MediaCollection) -> Unit) {
        mediaRoot.walkTopDown()
            .filter { it.isDirectory && it.isMediaCollection }
            .forEach {
                when {
                    layout.isPlaylist(it) -> {
                        logger.debug { "Found playlist $it" }
                        action(it.toMediaCollection(PLAYLIST))
                    }
                    layout.isAlbum(it) -> {
                        logger.debug { "Found album $it" }
                        action(it.toMediaCollection(ALBUM))
                    }
                    layout.isArtistEssential(it) -> {
                        logger.debug { "Found essential $it" }
                        action(it.toMediaCollection(ARTIST_ESSENTIAL_PLAYLIST))
                    }
                }
            }
    }

    private fun File.toMediaCollection(type: MediaCollectionType): MediaCollection {
        return MediaCollection(
            name = name,
            type = type,
            files = getMediaFiles()
        )
    }

    private fun File.getMediaFiles(): List<File> {
        val array = listFiles { _, name ->
            name.matches(".*\\.(mp3|aac|m4a)".toRegex())
        } ?: throw IllegalStateException("'${path}' is not readable directory")

        return array.toList()
    }

    private val File.isMediaCollection: Boolean
        get() = getMediaFiles().isNotEmpty()
}
