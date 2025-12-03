package twothousandtwentyone.day2

import java.io.File

fun main() {

    val commands =
        File("2021/day2/input.txt").readLines()
            .map { it.split(" ").let { Pair(it[0], it[1].toInt()) } }

    var forward = 0
    var depth = 0

    for (command in commands) {
        when (command.first) {
            "forward" -> forward += command.second
            "down" -> depth += command.second
            "up" -> depth -= command.second
        }
    }
    println(forward * depth)

}
