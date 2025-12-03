package twothousandtwentyone.day8

import java.io.File

fun main() {
    val input = File("2021/day8/input.txt").readLines()
    val outputs = input.map { it.split("|")[1].trim().split(" ") }
    println(outputs.sumOf { it.count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 } })
}
