package twothousandtwentyone.day1

import java.io.File

fun main() {
    val depths = File("2021/day1/input.txt").readLines().map { it.toInt() }

    // Convert to 3-measurement windows
    val windows = depths.indices.drop(2).map { depths[it - 2] + depths[it - 1] + depths[it] }

    var totalIncreases = 0

    var prevWindow = windows[0]
    for (window in windows.drop(1)) {
        if (window > prevWindow) {
            totalIncreases++
        }
        prevWindow = window
    }
    println(totalIncreases)
}
