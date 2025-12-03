package twothousandtwentyone.day3

import java.io.File
import kotlin.math.pow

fun main() {

    val diagnostics = File("2021/day3/input.txt").readLines()

    val binaryLength = diagnostics[0].length

    var gammaRate = 0
    var epsilonRate = 0

    for (i in 0 until binaryLength) {
        val oneCount = diagnostics.count { it[i] == '1' }
        if (oneCount > diagnostics.size / 2) {
            gammaRate += 2 pow binaryLength - i - 1
        } else {
            epsilonRate += 2 pow binaryLength - i - 1
        }
    }
    println(gammaRate * epsilonRate)
}

//Cringe Kotlin has no pow function for ints so I just made one myself (still cringe with the toDouble)
private infix fun Int.pow(other: Int) = toDouble().pow(other).toInt()
