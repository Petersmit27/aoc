package twothousandtwentyone.day11

import java.io.File

fun main() {
    val lines =
        File("2021/day11/input.txt").readLines().map { it.map { it.digitToInt() }.toMutableList() }

    val field = OctoField(lines)

    var step = 1
    while (true) {
        field.increaseAll()

        while (field.anyFlashing()) {
            val (x, y) = field.firstFlashingOrNull()
            field.flash(x, y)
        }

        if (field.allFlashed()) {
            println(step)
            return
        }
        step++
    }

}
