package twothousandtwentyone.day11

import java.io.File

fun main() {
    val lines =
        File("2021/day11/input.txt").readLines().map { it.map { it.digitToInt() }.toMutableList() }

    val field = OctoField(lines)

    for (step in 1..100) {
        field.increaseAll()

        while (field.anyFlashing()) {
            val (x, y) = field.firstFlashingOrNull()
            field.flash(x, y)
        }
    }
    println(field.flashes)
}

