package io.github.iromul.media

import io.github.iromul.media.meta.Numeration
import io.github.iromul.media.meta.Numeration.*
import java.nio.file.Path

fun eraseNumeration(name: String, numeration: Numeration): String {
    return when (numeration) {
        NO -> name
        NUMBER_POINT_SPACE -> name.replace("^\\d+\\. ".toRegex(), "")
        NUMBER_SPACE -> name.replace("^\\d+ ".toRegex(), "")
        NUMBER_SPACED_DASH -> name.replace("^\\d+ - ".toRegex(), "")
    }
}

fun enumify(obj: Any?) = obj.toString().toUpperCase().replace("-", "_")

fun Path.excludeRoot(exclusion: Path): Path {
    return (this - exclusion).reduce { acc, path -> acc.resolve(path) }
}
