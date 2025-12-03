package day1

import java.io.File
import kotlin.math.abs


enum class Direction {
    LEFT,
    RIGHT
}

class Safe {
    companion object {
        private const val DIAL_POSITIONS = 100
    }

    var dialPosition: Int = 50
        private set
    var postTurnZeroCount: Int = 0
        private set
    var zeroCount: Int = 0
        private set

    fun turn(direction: Direction, ticks: Int) {
        // TODO: Find out a formula to get the number of times it hit zero, so we don't have to do it step by step
//        zeroCount += when (direction) {
//            Direction.RIGHT -> (dialPosition + ticks) / DIAL_POSITIONS
//            Direction.LEFT -> abs(dialPosition - ticks) / DIAL_POSITIONS + (if (dialPosition - ticks < 0) 1 else 0)
//        }
//
//        dialPosition = when (direction) {
//            Direction.RIGHT -> (dialPosition + ticks) % DIAL_POSITIONS
//            Direction.LEFT -> (dialPosition - ticks + ((ticks / DIAL_POSITIONS + 1) * DIAL_POSITIONS)) % DIAL_POSITIONS
//        }

        (0 until ticks).forEach { _ ->
            dialPosition = when (direction) {
                Direction.RIGHT -> (dialPosition + 1) % DIAL_POSITIONS
                Direction.LEFT -> (dialPosition - 1 + DIAL_POSITIONS) % DIAL_POSITIONS
            }

            if (dialPosition == 0) {
                zeroCount++
            }
        }

        if (dialPosition == 0) {
            postTurnZeroCount++
        }
    }
}

fun main() {
    val safe = Safe()

    File("2025/day1/input").forEachLine { line ->
        val direction = if (line[0] == 'L') Direction.LEFT else Direction.RIGHT
        val ticks = line.substring(1).toInt()
        safe.turn(direction, ticks)
    }

    println("Final dial position: ${safe.dialPosition}")
    println("Number of times dial hit zero after turning (part 1): ${safe.postTurnZeroCount}")
    println("Number of times dial hit zero at any time (part 2): ${safe.zeroCount}")
}