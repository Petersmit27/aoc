package twothousandtwentyone.day13

import kotlin.math.abs

enum class Axis { X, Y }

data class Fold(val axis: Axis, val position: Int)

data class Coordinate(val x: Int, val y: Int)

data class Field(val dots: Set<Coordinate>) {

    fun horizFold(position: Int) =
        Field(dots.map { dot -> Coordinate(dot.x, position - abs(dot.y - position)) }.toSet())

    fun vertFold(position: Int) =
        Field(dots.map { dot -> Coordinate(position - abs(dot.x - position), dot.y) }.toSet())


    override fun toString(): String {
        val xLen = dots.maxOf { it.x }
        val yLen = dots.maxOf { it.y }
        val res = StringBuilder()
        for (y in 0..yLen) {
            val xs = dots.filter { it.y == y }.map { it.x }
            for (x in 0..xLen) {
                res.append(if (x in xs) '█' else ' ')
            }
            res.append('\n')
        }
        return res.toString()
    }

}
