package io.github.iromul.scripts.whichwatch

import java.time.Duration
import java.time.ZoneId
import java.time.ZonedDateTime

class RouteDsl {

    private val stops = mutableListOf<Duration>()

    fun at(spec: StopSpec.() -> Duration) {
        stops += StopSpec.spec()
    }

    fun totalDelay(): Duration {
        return stops.fold(Duration.ZERO) { acc, v -> acc + v }
    }

    object StopSpec {

        infix fun String.`for a`(duration: String) = `for a`(parseDuration(duration))

        infix fun String.`for a`(duration: Duration) = duration

        private fun parseDuration(str: String): Duration {
            return Duration.parse("PT" + str.toUpperCase())
        }
    }
}

class TimeManagementDsl {

    private var departureTime: ZonedDateTime? = null
    private var departureZone: ZoneId? = null

    private var arrivalTime: ZonedDateTime? = null
    private var arrivalZone: ZoneId? = null

    private val route: RouteDsl = RouteDsl()

    private var question: Question? = null

    fun `arrive at`(time: ZonedDateTime): RouteDsl {
        arrivalTime = time

        return route
    }

    infix fun `with stops`(stopsDsl: RouteDsl.() -> Unit) {
        route.stopsDsl()
    }

    fun `assuming that departure is in zone`(zoneId: String) {
        departureZone = ZoneId.of(zoneId)
    }

    fun `when should I start?`() {
        val arrivalTime = requireNotNull(arrivalTime)

        question = Question.WhenToStart(arrivalTime, route)
    }

    fun `print full route`() {
        val question = requireNotNull(question)

        val departureTime = departureTime
        val arrivalTime = arrivalTime

        val firstMsg = when {
            arrivalTime != null -> {
                val adjusted = arrivalZone?.let { arrivalTime.withZoneSameInstant(it) }
                    ?: arrivalTime

                "If I'd like to arrive at $adjusted"
            }
            departureTime != null -> {
                val adjusted = departureZone?.let { departureTime.withZoneSameInstant(it) }
                    ?: departureTime

                "If I'd like to departure at $adjusted"
            }
            else -> throw IllegalStateException("Please specify arrival or departure time")
        }

        val secondMsg = when (question) {
            is Question.WhenToStart -> {
                val adjusted = departureZone?.let { question.departureTime.withZoneSameInstant(it) }
                    ?: question.departureTime

                "I should start at $adjusted"
            }
        }

        println("$firstMsg $secondMsg")
    }

    private sealed class Question {

        abstract val departureTime: ZonedDateTime
        abstract val arrivalTime: ZonedDateTime

        class WhenToStart(
            override val arrivalTime: ZonedDateTime,
            private val route: RouteDsl
        ) : Question() {

            override val departureTime: ZonedDateTime
                get() = arrivalTime.minus(route.totalDelay())
        }
    }
}

fun `if I'd like to`(body: TimeManagementDsl.() -> Unit) = TimeManagementDsl().body()
