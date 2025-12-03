package twothousandtwentyone.day10

import java.io.File

fun main() {

    val lines = File("2021/day10/input.txt").readLines()
    var totalScore = 0
    for (line in lines) {
        val stack = ArrayDeque<Char>()
        for (c in line) {
            if (c in "([{<") {
                stack.addFirst(c)
            } else {
                val opener = stack.removeFirst()
                if (c != openToClose(opener)) {
                    //Corruption found!
                    totalScore += when (c) {
                        ')' -> 3
                        ']' -> 57
                        '}' -> 1197
                        '>' -> 25137
                        else -> throw   Exception("no")
                    }
                }
            }
        }
    }
    println(totalScore)
}

private fun openToClose(c: Char) = when (c) {
    '(' -> ')'
    '[' -> ']'
    '{' -> '}'
    '<' -> '>'
    else -> throw Exception("no")
}
