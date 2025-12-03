package twothousandtwentyone.day4

import java.io.File

fun main() {
    var input = File("2021/day4/input.txt").readLines()

    //Get balls
    val balls = input[0].split(",").map { it.toInt() }

    val boards = ArrayList<Board>()

    // Parse all the boards
    input = input.drop(2)
    while (input.isNotEmpty()) {
        boards.add(Board.parse(input.takeWhile { it != "" }))
        input = input.dropWhile { it != "" }.drop(1)
    }

    //Play the game of Bingus
    var currBall = -1
    while (boards.none { it.hasWon() }) {
        currBall++
        boards.forEach { it.markNum(balls[currBall]) }
    }

    println("Winning score: ${balls[currBall]*boards.first { it.hasWon() }.unmarkedSum()}")
}

