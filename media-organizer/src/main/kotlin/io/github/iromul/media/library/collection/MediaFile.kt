package io.github.iromul.media.library.collection

import java.io.File

data class MediaFile(
    val file: File,

    val trackNumber: String?,
    val artist: String,
    val album: String,
    val title: String
)
