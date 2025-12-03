package twothousandtwentyone.day11


data class OctoField(val octopi: List<MutableList<Int>>) {

    var flashes = 0

    fun adjacents(x: Int, y: Int): Collection<Pair<Int, Int>> {
        val res = HashSet<Pair<Int, Int>>()
        if (y > 0) res.add(x to y - 1)
        if (x > 0) res.add(x - 1 to y)
        if (y > 0 && x > 0) res.add(x - 1 to y - 1)
        if (x < octopi.lastIndex) res.add(x + 1 to y)
        if (y < octopi.lastIndex) res.add(x to y + 1)
        if (x > 0 && y < octopi.lastIndex) res.add(x - 1 to y + 1)
        if (x < octopi.lastIndex && y > 0) res.add(x + 1 to y - 1)
        if (x < octopi.lastIndex && y < octopi.lastIndex) res.add(x + 1 to y + 1)
        return res
    }

    fun anyFlashing() = octopi.any { it.any { it > 9 } }

    fun firstFlashingOrNull(): Pair<Int, Int> {
        for (y in octopi.indices) {
            for (x in octopi[y].indices) {
                if (octopi[y][x] > 9) return x to y
            }
        }
        throw Exception("no")
    }

    fun flash(x: Int, y: Int) {
        flashes++
        octopi[y][x] = 0

        val adjacents = adjacents(x, y)
        adjacents.forEach { (x, y) ->
            if (octopi[y][x] != 0) {
                octopi[y][x]++
            }
            if (octopi[y][x] > 9) {
                flash(x, y)
            }
        }
    }

    fun increaseAll() {
        for (y in octopi.indices) {
            for (x in octopi[y].indices) {
                octopi[y][x]++
            }
        }
    }

    fun allFlashed() = octopi.all { it.all { it == 0 } }

    override fun toString(): String {
        var res = ""
        octopi.forEach {
            res += it.joinToString("")
            res += "\n"
        }
        return res
    }
}
