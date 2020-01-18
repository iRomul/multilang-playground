package io.github.iromul.media.library.collection

import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import java.io.File

class MediaCollection(
    val name: String,
    val type: MediaCollectionType,
    private val files: List<File>
) {

    fun forEachMediaFile(action: (MediaFile) -> Unit) {
        files.forEach {
            val audioFile = AudioFileIO.read(it)

            val tag = audioFile.tag

            val artist = tag.getFirst(FieldKey.ARTIST)
            val album = tag.getFirst(FieldKey.ALBUM)
            val title = tag.getFirst(FieldKey.TITLE)

            action(MediaFile(it, null, artist, album, title))
        }
    }
}
