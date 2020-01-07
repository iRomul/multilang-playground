package io.github.iromul.media

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Usage: organizer INPUT_DIR OUTPUT_DIR")

        exitProcess(-1)
    }

    val input = args[0]
    val output = args[1]

    val dryRun = args.size > 2 && args[2] == "--dry-run"

    val organizer = MediaOrganizer(input, output, dryRun)

    organizer.perform()
}
