package twothousandtwentyone.day9

import java.io.File


data class Coordinate(val x: Int, val y: Int) {
    fun adjacents(xMax: Int, yMax: Int): Collection<Coordinate> {
        val adjacent = ArrayList<Coordinate>()
        if (y - 1 >= 0) {
            adjacent.add(Coordinate(x, y - 1))
        }
        if (x - 1 >= 0) {
            adjacent.add(Coordinate(x - 1, y))
        }
        if (y + 1 < yMax) {
            adjacent.add(Coordinate(x, y + 1))
        }
        if (x + 1 < xMax) {
            adjacent.add(Coordinate(x + 1, y))
        }
        return adjacent
    }
}

fun main() {
    val heightMap = File("2021/day9/input.txt").readLines()

    val basins = ArrayList<Set<Coordinate>>()
    val covered = HashSet<Coordinate>()

    for (y in heightMap.indices) {
        for (x in heightMap[y].indices) {
            if (heightMap[y][x] != '9' && Coordinate(x, y) !in covered) {
                basins.add(findBasin(Coordinate(x, y), heightMap, covered))
            }
        }
    }
    println(basins.map { it.size }.sorted().reversed().take(3).fold(1) { acc, size -> acc * size })
}

fun findBasin(
    c: Coordinate, heightMap: List<String>, covered: MutableCollection<Coordinate>
): Set<Coordinate> {
    val res = HashSet<Coordinate>()
    res.add(c)
    covered.add(c)

    for (adj in c.adjacents(heightMap[0].length, heightMap.size)) {
        if (heightMap[adj.y][adj.x] != '9' && adj !in covered) {
            res.addAll(findBasin(adj, heightMap, covered))
        }
    }
    return res
}
