package twothousandtwentyone.day9

import java.io.File

fun main() {
    val heightMap = File("2021/day9/input.txt").readLines()
    var totalRisk = 0

    for (y in heightMap.indices) {
        for (x in heightMap[y].indices) {
            val adjacent = ArrayList<Char>()
            if (y - 1 >= 0) {
                adjacent.add(heightMap[y - 1][x])
            }
            if (x - 1 >= 0) {
                adjacent.add(heightMap[y][x - 1])
            }
            if (y + 1 < heightMap.size) {
                adjacent.add(heightMap[y + 1][x])
            }
            if (x + 1 < heightMap[y].length) {
                adjacent.add(heightMap[y][x + 1])
            }
            if (adjacent.all { it > heightMap[y][x] }) {
                totalRisk += 1 + heightMap[y][x].digitToInt()
            }

        }
    }
    println(totalRisk)
}
