package twothousandtwentyone.day12

import java.io.File


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


    println(findPaths("start", listOf(), bothWayPaths, setOf()).size)
}

private fun findPaths(
    cave: Cave,
    currentPath: List<Cave>,
    paths: Map<Cave, Collection<Cave>>,
    visited: Set<Cave>
): Collection<List<Cave>> {
    val newPath = currentPath.toMutableList() + cave

    if (cave == "end") {
        return setOf(newPath)
    }
    val newVisited = visited.toMutableSet() + cave

    val res = HashSet<List<Cave>>()

    for (possibleCave in paths[cave] ?: listOf()) {
        if (possibleCave.isBig() || possibleCave !in visited) {
            res += findPaths(possibleCave, newPath, paths, newVisited)
        }
    }
    return res

}

