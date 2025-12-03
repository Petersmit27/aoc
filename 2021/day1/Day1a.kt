package twothousandtwentyone.day1

import java.io.File

fun main() {
    val depths = File("2021/day1/input.txt").readLines().map { it.toInt() }

    var totalIncreases=0

    var prevDepth = depths[0]
    for (depth in depths.drop(1)) {
        if (depth > prevDepth) {
            totalIncreases++
        }
        prevDepth=depth
    }
    println(totalIncreases)
}
