package twothousandtwentyone.day7

import java.io.File
import kotlin.math.abs

fun main() {
    val krabs = File("2021/day7/input.txt").readLines()[0].split(",").map { it.toInt() }
    println(Array(krabs.maxOrNull()!! - krabs.minOrNull()!!) { currPos -> krabs.sumOf { krabPos -> abs(krabPos - currPos) } }.minOrNull())
}
