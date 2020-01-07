package io.github.iromul.media

import io.github.iromul.media.meta.TracksOrder.*
import io.github.iromul.media.meta.buildDirectoryMeta
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class MediaOrganizer(
    input: String,
    output: String,
    private val dryRun: Boolean = false
) {

    private val filter = listOf("Cover", "Covers", "Artists", "Playlists")

    private val inputRoot = File(input)
    private val outputRoot = File(output)

    fun perform() {
        inputRoot.walkTopDown()
            .maxDepth(2)
            .filter { it.isDirectory && it.name !in filter }
            .forEach(this::processDirectory)
    }

    private fun processDirectory(directory: File) {
        val musicFiles = directory.listFiles { _, name ->
            name.matches(".*\\.(mp3|aac|m4a)".toRegex())
        } ?: throw IllegalStateException("Can't read filed from '${directory.path}'")

        if (musicFiles.isNotEmpty()) {
            val relative = Paths.get(directory.path).excludeRoot(Paths.get(inputRoot.path))

            val targetOutput = Paths.get(outputRoot.toURI())
                .resolve(relative)
                .toFile()
                .apply { mkdirs() }

            println("Copying music files from '${directory.path}' to '${targetOutput.path}'...")

            organizeAndCopyDirectory(directory, musicFiles.asList(), targetOutput)
        }
    }

    private fun organizeAndCopyDirectory(directory: File, musicFiles: List<File>, outputDir: File) {
        val meta = buildDirectoryMeta(directory)

        val total = musicFiles.size
        val digits = total.toString().length

        val orderedMusicFiles = when (meta.order) {
            KEEP -> musicFiles
            PLAYLIST -> reorderByPlaylist(musicFiles, meta.playlistData!!)
            SHUFFLE -> musicFiles.shuffled()
        }

        orderedMusicFiles.forEachIndexed { i, file ->
            val oldName = file.name
            val oldNameWithoutIndex = eraseNumeration(oldName, meta.numeration)

            val position = (i + 1).toString().padStart(digits, '0')
            val newName = "$position - $oldNameWithoutIndex"

            println("Copying '$oldName' to '$newName'")

            fileOperation {
                val targetPath = Paths.get(outputDir.path, newName)
                val targetFile = targetPath.toFile()

                if (!targetFile.exists()) {
                    Files.copy(file.toPath(), targetPath)
                } else {
                    println("Skipping '$newName' because file is already exists")
                }
            }
        }
    }

    private fun fileOperation(callback: () -> Unit) {
        if (!dryRun) {
            callback()
        }
    }
}
