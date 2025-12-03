package twothousandtwentyone.day6

import java.io.File

fun main() {
    val fishies =
        File("2021/day6/input.txt").readLines()[0].split(",").map { it.toInt() }.toMutableList()

    var fishesPerDay = Array(9) { 0L }
    for (daysLeft in fishesPerDay.indices) {
        fishesPerDay[daysLeft] = fishies.count { it == daysLeft }.toLong()
    }

    for (day in 0 until 256) {
        val newFishiesPerDay = Array(9) { 0L }

        //Handle fishie aging
        for (i in 0..7) {
            newFishiesPerDay[i] += fishesPerDay[i + 1]
        }
        //Handle new fishies
        newFishiesPerDay[6] += fishesPerDay[0]
        newFishiesPerDay[8] += fishesPerDay[0]

        fishesPerDay = newFishiesPerDay
    }

    println(fishesPerDay.sum())
}

