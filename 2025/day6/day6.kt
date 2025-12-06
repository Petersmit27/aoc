package day6

import java.io.File

// Transposing it, or directly iterating over the matrix is probably faster, but oh well ;)
data class VerticalListIterator<T>(val list: List<List<T>>) : Iterator<List<T>> {
    private var i = 0

    override fun next(): List<T> {
        val element = list.map { it[i] }
        i++
        return element
    }

    override fun hasNext() = i < list[0].size
}


typealias CephalopodProblem = Pair<(Long, Long) -> Long, Collection<Long>>
fun CephalopodProblem.solve() = second.reduce(first)

fun parseCephalopodProblems(homework: List<String>): Collection<CephalopodProblem> {
    val result = mutableSetOf<CephalopodProblem>()
    val numberLines = homework.dropLast(1)
    val operatorLine = homework.last()
    val problemEndIndices = operatorLine.indices.filter { i -> homework.all { it[i] == ' ' } }
        .toMutableList().also { it.add(operatorLine.lastIndex + 1) }

    for (endIndex in problemEndIndices) {
        val numbers = mutableSetOf<Long>()
        for (i in generateSequence(endIndex - 1) { it - 1 }) {
            // Parse this column's number
            var stringNumber = ""
            numberLines.forEach {
                if (it[i].isDigit()) {
                    stringNumber+=it[i]
                }
            }
            numbers.add(stringNumber.toLong())

            // Check if we encountered the operator
            if (operatorLine[i] == '+') {
                result.add(Pair(Long::plus, numbers))
                break
            } else if (operatorLine[i] == '*') {
                result.add(Pair(Long::times, numbers))
                break
            }
        }
    }
    return result
}

fun main() {
    val homework = File("2025/day6/input").readLines()
//    val homework = "123 328  51 64 \n 45 64  387 23 \n  6 98  215 314\n*   +   *   +  ".split('\n')

    val homeworkSum = homework
        .map { it.trim().split("\\s+".toRegex()) }
        .let { VerticalListIterator(it) }
        .asSequence()
        .sumOf { line ->
            line.dropLast(1)
                .map { it.toLong() }
                .reduce(if (line.last() == "+") Long::plus else Long::times)
        }
    println("Part 1: The cephalopod's homework's grand total is seemingly $homeworkSum")

    val realHomeworkSum = parseCephalopodProblems(homework).sumOf { it.solve() }
    println("Part 2: The cephalopod's homework's grand total is actually $realHomeworkSum")

}
