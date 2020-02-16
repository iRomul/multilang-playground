package io.github.iromul.scripts.whichwatch

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

val ferry = ZonedDateTime.of(LocalDateTime.parse("2019-11-24T17:00:00"), ZoneId.of("Europe/Helsinki"))!!

`if I'd like to` {
    `arrive at`(ferry)

    `with stops` {
        at { "road to russian border with short stop on gas-station" `for a` "2h22m" }
        at { "possible delay on border" `for a` "2h30m" }
        at { "road to Olympiaterminal South Harbour with long stop on gas-station" `for a` "2h30m" }
        at { "before check-in on car terminal" `for a` "1h" }
    }

    `assuming that departure is in zone`("Europe/Moscow")
    `when should I start?`()

    `print full route`()
}
