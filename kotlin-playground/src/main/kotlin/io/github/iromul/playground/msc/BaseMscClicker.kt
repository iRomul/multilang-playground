package io.github.iromul.playground.msc

import java.awt.Robot
import java.awt.event.KeyEvent
import java.lang.Thread.sleep
import java.time.Duration

open class BaseMscClicker {

    private val robot = Robot()

    protected val preSleep = Duration.ofSeconds(10)!!
    protected val sleep = Duration.ofMillis(400)!!

    fun scrollUp() {
        robot.mouseWheel(-20)
    }

    fun scrollDown() {
        robot.mouseWheel(20)
    }

    fun longDelay() {
        sleep(preSleep)
    }

    fun delay() {
        sleep(sleep)
    }

    fun swear() {
        robot.keyPress(KeyEvent.VK_N)
    }

    protected fun withReachButton(body: () -> Unit) {
        robot.keyPress(KeyEvent.VK_Q)

        body()

        robot.keyRelease(KeyEvent.VK_Q)
    }
}
