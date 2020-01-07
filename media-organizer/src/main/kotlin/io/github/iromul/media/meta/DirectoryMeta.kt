package io.github.iromul.media.meta

import io.github.iromul.media.playlist.PlaylistData

data class DirectoryMeta(
    var kind: DirectoryKind = DirectoryKind.ALBUM,
    var playlistData: PlaylistData? = null,
    var order: TracksOrder = TracksOrder.KEEP,
    var numeration: Numeration = Numeration.NO
)
