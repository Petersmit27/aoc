package twothousandtwentyone.day10

import java.io.File

fun main() {
    val lines = File("2021/day10/input.txt").readLines()

    val lineScores = ArrayList<Long>()
    lines@ for (line in lines) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (c in "([{<") {
                stack.addFirst(c)
            } else {
                val opener = stack.removeFirst()
                if (c != openToClose(opener)) {
                    //Corruption found!
                    continue@lines
                }
            }
        }
        // deque now contains all remaining openers
        var score = 0L
        for (c in stack) {
            score *= 5
            score += when (c) {
                '(' -> 1
                '[' -> 2
                '{' -> 3
                '<' -> 4
                else ->throw Exception("no")
            }
        }
        lineScores.add(score)
    }
    println(lineScores.sorted()[lineScores.size/2])
}

private fun openToClose(c: Char) = when (c) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> throw Exception("no")
}
