package twothousandtwentyone.day4

import java.io.File

fun main() {
    var input = File("2021/day4/input.txt").readLines()

    //Get balls
    val balls = input[0].split(",").map { it.toInt() }

    var boards = ArrayList<Board>()

    // Parse all the boards
    input = input.drop(2)
    while (input.isNotEmpty()) {
        boards.add(Board.parse(input.takeWhile { it != "" }))
        input = input.dropWhile { it != "" }.drop(1)
    }

    //Play the game of Bingus
    var currBall = -1
    while (!boards.all { it.hasWon() }) {
        currBall++
        boards = boards.filter { !it.hasWon() } as ArrayList<Board>
        boards.forEach { it.markNum(balls[currBall]) }
    }

    println("Losing score: ${balls[currBall] * boards[0].unmarkedSum()}")
}
