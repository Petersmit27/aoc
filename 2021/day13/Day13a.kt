package twothousandtwentyone.day13

import java.io.File

fun main() {
    val lines = File("2021/day13/input.txt").readLines()

    var field = Field(lines.takeWhile { it.isNotEmpty() }
        .map { it.split(",").let { Coordinate(it[0].toInt(), it[1].toInt()) } }.toSet())

    val folds = lines.dropWhile { it.isNotEmpty() }.drop(1).map {
        it.drop(11).split("=").let { Fold(if (it[0] == "x") Axis.X else Axis.Y, it[1].toInt()) }
    }

    field = when (folds[0].axis) {
        Axis.X -> field.vertFold(folds[0].position)
        Axis.Y -> field.horizFold(folds[0].position)
    }

    println(field.dots.size)
}

