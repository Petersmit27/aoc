package day5

import java.io.File
import kotlin.math.max

fun compareRanges(r1: LongRange, r2: LongRange): Int {
    if (r1.first > r2.first) {
        return 1
    }
    if (r1.first < r2.first) {
        return -1
    }
    if (r1.last > r2.last) {
        return 1
    }
    if (r1.last < r2.last) {
        return -1
    }
    return 0
}

fun main() {
    val inputLines = File("2025/day5/input").readLines()
//    val inputLines = "3-5\n10-14\n16-20\n12-18\n\n1\n5\n8\n11\n17\n32".split("\n")

    val freshIngredientRanges = inputLines
        .takeWhile { it.isNotEmpty() }
        .map { it.split('-') }
        .map { it[0].toLong()..it[1].toLong() }
        .sortedWith(::compareRanges)

    val availableIngredients = inputLines
        .takeLastWhile { it.isNotEmpty() }
        .map { it.toLong() }

    var freshCount = 0
    nextIngredient@ for (i in availableIngredients) {
        for (range in freshIngredientRanges) {
            if (i in range) {
                freshCount++
                continue@nextIngredient
            }
        }
    }
    println("Part 1: Found $freshCount fresh ingredients")


    val mergedRanges = mutableListOf(freshIngredientRanges[0])
    freshIngredientRanges.drop(1).forEach { newRange ->
        val lastRange = mergedRanges.last()
        if (newRange.first <= lastRange.last) {
            mergedRanges[mergedRanges.lastIndex] = lastRange.first..max(lastRange.last, newRange.last)
        } else {
            mergedRanges.add(newRange)
        }
    }
    println("Part 2: There are ${mergedRanges.sumOf { it.last - it.first + 1 }} fresh ingredient IDs in the database")
}
