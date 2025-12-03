package day3

import java.io.File

fun main() {

    val batteriesList = File("2025/day3/input").readLines()

    // Test input
    // val batteriesList = listOf("987654321111111", "811111111111119", "234234234234278", "818181911112111")

    var totalMaxJoltage = 0L
    val maxNumberOfActiveBatteries = 12 // 2 for part 1

    for (batteries in batteriesList) {
        totalMaxJoltage += maxJoltage(batteries, maxNumberOfActiveBatteries)
    }

    println("Total max joltage is $totalMaxJoltage")
}

fun maxJoltage(batteries: String, maxNumberOfBatteries: Int): Long {
    val joltage = MutableList(maxNumberOfBatteries) { 0 }

    for ((i, battery) in batteries.withIndex()) {
        val intBattery = battery.toString().toInt()

        for ((j, activeBattery) in joltage.withIndex()) {
            if (intBattery > activeBattery && i + maxNumberOfBatteries - 1 - j < batteries.length) {
                joltage[j] = intBattery
                for (k in j+1 until joltage.size) {
                    joltage[k] = 0
                }
                break
            }
        }
    }

    return joltage.fold("", { str, b -> str + b.toString() }).toLong()
}