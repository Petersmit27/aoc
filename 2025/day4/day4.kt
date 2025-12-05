package day4

import java.io.File


typealias Coordinate = Pair<Int, Int>
val Coordinate.x: Int
    get() = first
val Coordinate.y: Int
    get() = second
operator fun Coordinate.plus(other: Coordinate) = x + other.x to y + other.y


typealias PaperGrid = MutableList<MutableList<Boolean>>
fun PaperGrid.isValidCoordinate(c: Coordinate) = c.x < this[0].size && c.y < size && c.x >= 0 && c.y >= 0
operator fun PaperGrid.get(c: Coordinate) = this[c.y][c.x]
operator fun PaperGrid.set(c: Coordinate, value: Boolean) {
    this[c.y][c.x] = value
}
val PaperGrid.coordinates: Collection<Coordinate>
    get() = (0 until this[0].size).flatMap { x -> (0 until this.size).map { y -> x to y } }

val adjacentMutations = listOf(-1 to -1, 0 to -1, 1 to -1, -1 to 0, 1 to 0, -1 to 1, 0 to 1, 1 to 1)

fun PaperGrid.isMovable(c: Coordinate): Boolean {
    if (!this[c]) {
        return false
    }
    var adjacentCount = 0

    for (dc in adjacentMutations) {
        if (isValidCoordinate(c + dc) && this[c + dc]) {
            adjacentCount++
            if (adjacentCount >= 4) {
                return false
            }
        }
    }

    return true
}

fun main() {
    val paperGrid = File("2025/day4/input")
        .readLines()
        .map { paperLine -> paperLine.map { it == '@' }.toMutableList() }.toMutableList()

    // Test input
//    val paperGrid = listOf(
//        "..@@.@@@@.",
//        "@@@.@.@.@@",
//        "@@@@@.@.@@",
//        "@.@@@@..@.",
//        "@@.@@@@.@@",
//        ".@@@@@@@.@",
//        ".@.@.@.@@@",
//        "@.@@@.@@@@",
//        ".@@@@@@@@.",
//        "@.@.@@@.@."
//    ).map { paperLine -> paperLine.map { it == '@' }.toMutableList() }.toMutableList()

    // Part 1
    val movableCount = paperGrid.coordinates.filter { paperGrid.isMovable(it) }.size
    println("Part 1: There are $movableCount movable paper rolls initially")

    // Part 2
    var paperRemovedCount = 0
    while (true) {
        var gridChanged = false
        for (c in paperGrid.coordinates) {
            if (paperGrid.isMovable(c)) {
                paperGrid[c] = false
                paperRemovedCount++
                gridChanged = true
            }
        }
        if (!gridChanged) {
            break
        }
    }
    println("Part 2: Was able to remove $paperRemovedCount paper rolls")
}
