package io.github.iromul.media.scripts

import io.github.iromul.media.eraseNumeration
import io.github.iromul.media.excludeRoot
import io.github.iromul.media.meta.TracksOrder.*
import io.github.iromul.media.meta.buildDirectoryMeta
import io.github.iromul.media.reorderByPlaylist
import mu.KotlinLogging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class OrganizeAndCopyFilesScript(
    mediaRoot: File,
    private val output: File,
    dryRun: Boolean = false
) : Script(mediaRoot, dryRun) {

    private val logger = KotlinLogging.logger {}

    override fun perform() {
        mediaRoot.walkTopDown()
            .maxDepth(3)
            .filter { it.isDirectory }
            .forEach(::processDirectory)
    }

    private fun processDirectory(directory: File) {
        val musicFiles = directory.listFiles { _, name ->
            name.matches(".*\\.(mp3|aac|m4a)".toRegex())
        } ?: throw IllegalStateException("Can't read filed from '${directory.path}'")

        if (musicFiles.isNotEmpty()) {
            val relative = Paths.get(directory.path).excludeRoot(Paths.get(mediaRoot.path))

            val targetOutput = Paths.get(output.toURI())
                .resolve(relative)
                .toFile()
                .apply { mkdirs() }

            logger.info { "Copying music files from '${directory.path}' to '${targetOutput.path}'..." }

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
            val oldNameWithoutIndex =
                eraseNumeration(oldName, meta.numeration)

            val position = (i + 1).toString().padStart(digits, '0')
            val newName = "$position - $oldNameWithoutIndex"


            val targetPath = Paths.get(outputDir.path, newName)
            val targetFile = targetPath.toFile()

            if (!targetFile.exists()) {
                logger.info { "Copying '$oldName' to '$newName'" }

                fileOperation {
                    Files.copy(file.toPath(), targetPath)
                }
            } else {
                logger.info { "Skipping '$newName' because file is already exists" }
            }
        }
    }
}
