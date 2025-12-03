@file:Suppress("DuplicatedCode")

package twothousandnineteen.day3

import java.io.File

fun main() {

    val allMoves = File("2019/day3/input.txt").readText()
    val moves1 = allMoves.split("\n")[0].split(",").map {
        Move(it[0], it.substring(1).toInt())
    }
    val moves2 = allMoves.split("\n")[1].split(",").map {
        Move(it[0], it.substring(1).toInt())
    }


    val intersections = ArrayList<Coordinate>()
    val geweesteCoordinaten1 = ArrayList<Coordinate>()
    val geweesteCoordinaten2 = ArrayList<Coordinate>()
    var lastCoordinate = Coordinate(0, 0)
    var newCoordinate = Coordinate(Int.MAX_VALUE, Int.MAX_VALUE)


    moves1.forEach {
        when (it.direction) {
            'U' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y + i)
                    geweesteCoordinaten1.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'R' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x + i, lastCoordinate.y)
                    geweesteCoordinaten1.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'D' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y - i)
                    geweesteCoordinaten1.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'L' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x - i, lastCoordinate.y)
                    geweesteCoordinaten1.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
        }
    }

    lastCoordinate = Coordinate(0, 0)

    moves2.forEach {
        when (it.direction) {
            'U' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y + i)
                    if (geweesteCoordinaten1.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                    geweesteCoordinaten2.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'R' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x + i, lastCoordinate.y)
                    if (geweesteCoordinaten1.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                    geweesteCoordinaten2.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'D' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x, lastCoordinate.y - i)
                    if (geweesteCoordinaten1.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                    geweesteCoordinaten2.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
            'L' -> {
                for (i in 1..it.amount) {
                    newCoordinate = Coordinate(lastCoordinate.x - i, lastCoordinate.y)
                    if (geweesteCoordinaten1.contains(newCoordinate)) {
                        intersections.add(newCoordinate)
                    }
                    geweesteCoordinaten2.add(newCoordinate)
                }
                lastCoordinate = newCoordinate
            }
        }
    }

    val combinedSteps = ArrayList<Int>()



    intersections.forEach {
        combinedSteps.add(
            determineStepsToIntersection(geweesteCoordinaten1, it)
                    + determineStepsToIntersection(geweesteCoordinaten2, it)
                    + 2
        )//+2 (eerste of laatse stappen nog niet geinclude)
    }

    combinedSteps.forEach { println(it) }
    println(combinedSteps.min())
}

fun determineStepsToIntersection(geweest: List<Coordinate>, intersection: Coordinate): Int {
    for (i in 0..geweest.size) {
        if (geweest[i] == intersection) {
            return i
        }
    }
    return -1
}