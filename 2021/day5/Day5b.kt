package twothousandtwentyone.day5

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min


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

    // Cover same diagonal lines (meaning that startX-endX == startY-endY
    for (line in lines.filter { it.isSameDiagonal() }) {
        val xStart = min(line.first.first, line.second.first)
        val yStart = min(line.first.second, line.second.second)
        val xEnd = max(line.first.first, line.second.first)

        for (diff in 0 towards xEnd - xStart) {
            field[yStart + diff][xStart + diff]++
        }
    }

    // Cover differing diagonal lines (meaning that startX-endX == -1 * startY-endY
    for (line in lines.filter { it.isDiffDiagonal() }) {
        val xStart = min(line.first.first, line.second.first)
        val yEnd = max(line.first.second, line.second.second)
        val xEnd = max(line.first.first, line.second.first)

        for (diff in 0 towards xEnd - xStart) {
            field[yEnd - diff][xStart + diff]++
        }
    }
    //Print the field
//    for (row in field) {
//        for (num in row) {
//            print(num)
//        }
//        println()
//    }

    println(field.sumOf { it.count { it >= 2 } })
}

