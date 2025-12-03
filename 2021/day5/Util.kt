package twothousandtwentyone.day5

typealias Point = Pair<Int, Int>

typealias Line = Pair<Point, Point>

fun Line.isHorizontal() = first.second == second.second
fun Line.isVertical() = first.first == second.first
fun Line.isSameDiagonal() = first.first - second.first == first.second - second.second
fun Line.isDiffDiagonal() = first.first - second.first == second.second - first.second

infix fun Int.towards(to: Int): IntRange = if (this < to) this..to else to..this
