package twothousandtwentyone.day12

import java.io.File

typealias Cave = String

fun Cave.isBig() = any { it.isUpperCase() }


fun main() {
    val oneWayPaths =
        File("2021/day12/input.txt").readLines().map { it.split("-") }.map { Pair(it[0], it[1]) }

    val bothWayPaths = HashMap<Cave, MutableCollection<Cave>>()

    for ((from, to) in oneWayPaths) {
        if (from !in bothWayPaths) {
            bothWayPaths[from] = HashSet()
        }
        bothWayPaths[from]!!.add(to)

        if (to !in bothWayPaths) {
            bothWayPaths[to] = HashSet()
        }
        bothWayPaths[to]!!.add(from)

    }


    println(findPaths("start", listOf(), bothWayPaths, setOf(), false).size)
}

private fun findPaths(
    cave: Cave,
    currentPath: List<Cave>,
    paths: Map<Cave, Collection<Cave>>,
    visited: Set<Cave>,
    visitedSmallTwice: Boolean
): Collection<List<Cave>> {
    val newPath = currentPath.toMutableList() + cave

    if (cave == "end") {
        return setOf(newPath)
    }
    val newVisited = visited.toMutableSet() + cave

    val res = HashSet<List<Cave>>()

    for (possibleCave in paths[cave] ?: listOf()) {
        if (possibleCave.isBig() || possibleCave !in visited || (!visitedSmallTwice && possibleCave != "start" && possibleCave != "end")) {
            res += if (!possibleCave.isBig() && !visitedSmallTwice && possibleCave in visited) {
                findPaths(possibleCave, newPath, paths, newVisited, true)
            } else {
                findPaths(possibleCave, newPath, paths, newVisited, visitedSmallTwice)
            }
        }

    }
    return res

}

