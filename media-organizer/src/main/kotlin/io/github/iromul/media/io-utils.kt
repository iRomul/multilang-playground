package io.github.iromul.media

import java.nio.file.Path

fun Path.excludeRoot(exclusion: Path): Path {
    val diff = this - exclusion

    return if (diff.isNotEmpty()) {
        diff.reduce { acc, path -> acc.resolve(path) }
    } else {
        this
    }
}

fun String.sanitizeWindowsFileName() = replace("[<>:\"/\\\\|?*]".toRegex(), "_").run {
    if (endsWith(".")) this + "_" else this
}

fun winAwareNameWeakMatch(fileName: String, lookup: String): Boolean {
    return fileName.contains(lookup.sanitizeWindowsFileName())
}