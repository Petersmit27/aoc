package twothousandnineteen.day3

import kotlin.math.absoluteValue


data class Coordinate(val x: Int, val y: Int) {
    fun getDistance(): Int {
        return x.absoluteValue + y.absoluteValue
    }
}

data class Move(val direction: Char, val amount: Int)