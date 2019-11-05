package io.github.iromul.scripts.files

import groovy.transform.CompileStatic

import java.nio.file.Files
import java.nio.file.Paths

if (args.length != 1) {
    throw new IllegalArgumentException("No input path specified")
}

def path = args[0]

perform(path)

@CompileStatic
def perform(String path) {
    def playlistDir = new File(path)
    def outDir = new File(playlistDir, "shuffled")
    outDir.mkdirs()

    def musicFilesFilter = { File file, String name -> name.matches(/.*\.(mp3|aac|m4a)$/) } as FilenameFilter

    def files = playlistDir.listFiles(musicFilesFilter).toList()

    Collections.shuffle(files)

    files.eachWithIndex { File entry, int i ->
        def oldName = entry.name
        def oldNameWithoutIndex = oldName.replaceAll(/^\d+ - /, "")

        def newName = "${i + 1} - $oldNameWithoutIndex"

        println "$oldName -> $newName"

        Files.copy(entry.toPath(), Paths.get(outDir.path, newName))
    }
}
