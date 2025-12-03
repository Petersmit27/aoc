package day2

import java.io.File

fun main() {

    val ranges = File("2025/day2/input").readLines()[0].split(',')

    // Part 1 test
//    val ranges =
//        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862"
//            .split(',')

    // Part 2 tests
//    val ranges =
//        "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
//            .split(',')


    var invalidSum = 0L
    val invalids = mutableListOf<Long>()

    for (range in ranges) {
        val split = range.split('-')

        for (id in split[0].toLong()..split[1].toLong()) {
            if (isInvalidPart2(id)) {
                invalidSum += id
                invalids.add(id)
            }
        }
    }

    println("Found the following invalid Product IDs:")
    invalids.forEach { println(it) }
    println("Sum of invalid Product IDs: $invalidSum")

}

fun isInvalidPart1(id: Long): Boolean {
    val idString = id.toString()
    return idString.take(idString.length / 2) == idString.drop(idString.length / 2)
}


fun isInvalidPart2(id: Long): Boolean {
    val stringId = id.toString()
    nextFraction@ for (fraction in 2..stringId.length) {
        if (stringId.length % fraction != 0) {
            // Cannot be divided evenly
            continue
        }
        val sequenceLength = stringId.length / fraction

        val firstSequence = stringId.take(sequenceLength)
        for (i in sequenceLength until stringId.length step sequenceLength) {
            // Check if the rest is the same. if not, not invalid
            if (firstSequence != stringId.substring(i, i + sequenceLength)) {
                continue@nextFraction
            }
        }
        return true

    }
    return false
}

