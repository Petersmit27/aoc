package twothousandtwentyone.day6

import java.io.File

fun main() {
    val fishes =
        File("2021/day6/test.txt").readLines()[0].split(",").map { it.toInt() }.toMutableList()

    for (day in 0 until 80) {
        for (i in fishes.indices) {
            if (fishes[i] == 0) {
                fishes[i] = 6
                fishes.add(8)
            } else {
                fishes[i]--
            }
        }
    }
    println(fishes.size)
}

