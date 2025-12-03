package twothousandtwentyone.day5

import java.io.File


fun main() {
    // Holy shit lmao
    val lines = File("2021/day5/input.txt").readLines().map {
        it.split(" -> ").let {
            (it[0].split(",").let { it[0].toInt() to it[1].toInt() }
                    ) to (
                    it[1].split(",").let { it[0].toInt() to it[1].toInt() })
        }
    }

    // Get highest x and y
    val maxX = lines.maxOf { arrayOf(it.first.first, it.second.first).maxOrNull()!! }
    val maxY = lines.maxOf { arrayOf(it.first.second, it.second.second).maxOrNull()!! }

    // Initialise field
    val field = Array(maxY + 1) { Array(maxX + 1) { 0 } }

    // Cover vertical lines
    for (line in lines.filter { it.isVertical() }) {
        val x = line.first.first
        for (y in line.first.second towards line.second.second) {
            field[y][x]++
        }
    }

    // Cover horizontal lines
    for (line in lines.filter { it.isHorizontal() }) {
        val y = line.first.second
        for (x in line.first.first towards line.second.first) {
            field[y][x]++
        }

    }

    println(field.sumOf { it.count { it >= 2 } })
}

