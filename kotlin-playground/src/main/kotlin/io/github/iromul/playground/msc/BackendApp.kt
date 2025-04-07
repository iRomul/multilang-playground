package io.github.iromul.playground.msc

object MSCAuto : BaseMscClicker() {

    fun setupWheelAlignment() {
        withReachButton {
            longDelay()

            repeat(121) {
                scrollUp()
                delay()
            }

            repeat(60) {
                scrollDown()
                delay()
            }

            delay()
        }
    }

    fun setupCarburetor() {
        withReachButton {
            longDelay()

            repeat(44) {
                scrollUp()
                delay()
            }

            repeat(22) {
                scrollDown()
                delay()
            }

            delay()
        }
    }
}

fun main(argv: Array<String>) {
    MSCAuto.longDelay()

    MSCAuto.setupWheelAlignment()
//    MSCAuto.setupCarburetor()
}
