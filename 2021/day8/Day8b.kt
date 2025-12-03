package twothousandtwentyone.day8

import java.io.File

fun main() {
    val inputs = File("2021/day8/test.txt").readLines()



    for (input in inputs) {
        val signal = input.split("|")[0].trim().split(" ")
        val output = input.split("|")[1].trim().split(" ")

        val mapping = HashMap<Char, Char>()

        val knownNumbers = Array<String?>(10) { null }

        while (mapping.size < 6) {

            // Find known numbers by checking lengths of the numbers
            (0..9).map { it }.groupBy { numToChars(it).length }.forEach { (length, nums) ->

                val knownNumSet = (0..9)
                    .filter { knownNumbers[it] != null && knownNumbers[it]!!.length == length }
                    .toSet()
                val numsLeft = nums.toSet() - knownNumSet
                if (numsLeft.size == 1) {
                    val foundNum = numsLeft.first()
                    knownNumbers[foundNum] =
                        signal.first { it.length == length && it !in knownNumbers }
                }
            }

            for (num1String in signal) {
                for (num2String in signal) {
                    if (num1String == num2String) continue

                    val diff = num1String - num2String - mapping.values.joinToString("")

                    if (num1String !in knownNumbers && num2String in knownNumbers) {
                        val findNum1: Int? = findNum1(
                            num1String,
                            num2String,
                            knownNumbers.indexOf(num2String),
                            mapping,
                            knownNumbers
                        )
                        if (findNum1 != null) {
                            knownNumbers[findNum1] = num1String
                        }
                    }

                    if (num2String !in knownNumbers && num1String in knownNumbers) {
                        val findNum2: Int? = findNum2(
                            num1String,
                            knownNumbers.indexOf(num1String),
                            num2String,
                            mapping,
                            knownNumbers
                        )
                        if (findNum2 != null) {
                            knownNumbers[findNum2] = num2String
                        }
                    }

                    if (diff.length == 1 &&
                        num1String in knownNumbers &&
                        num2String in knownNumbers
                    ) {
                        val num1 = knownNumbers.indexOf(num1String)
                        val num2 = knownNumbers.indexOf(num2String)

                        val sourceChar =
                            numToChars(num1) - numToChars(num2) - mapping.values.joinToString("")
                        if (sourceChar.isEmpty()) {
                            continue
                        }
                        if (sourceChar.length > 1) {
                            continue
                        }
                        mapping[sourceChar[0]] = diff[0]
                    }
                }
            }
            for ((from, to) in mapping) {
                println("$from -> $to")
            }
            println()
        }

        if (mapping.size != 7) {
            //One more left, easy by just mapping the missing values
            val from = ("abcdef".toSet() - mapping.keys.toSet()).first()
            val to = ("abcdef".toSet() - mapping.values.toSet()).first()
            mapping[from] = to
        }

        println(output.map { charsToNum(it.map { mapping[it] }.joinToString("").sorted()) })
    }
}

private operator fun String.minus(other: String): String =
    (toSet() - other.toSet()).joinToString("").sorted()

fun findNum1(
    num1String: String,
    num2String: String,
    num2Int: Int,
    mapping: java.util.HashMap<Char, Char>,
    knownNumbers: Array<String?>
): Int? {
    val candidates = (0..9).filter {
        numToChars(it).length == num1String.length &&
                (num1String - num2String - mapping.values.joinToString("")).length == 1 &&
                (numToChars(it) - numToChars(num2Int) - mapping.keys.joinToString("")).length == 1
    }
    if (candidates.size == 1) {
        return candidates[0]
    }
//    for (candidate in 0..9) {
//        val candidateString = numToChars(candidate)
//        if (candidateString.length == num1String.length &&
//            num1String - num2String - mapping.values.joinToString("") ==
//            candidateString - num2String - mapping.values.joinToString("")
//        ) {
//            return candidate
//        }
//    }

    return null
}


fun findNum2(
    num1String: String,
    num1Int: Int,
    num2String: String,
    mapping: java.util.HashMap<Char, Char>,
    knownNumbers: Array<String?>
): Int? {
    val candidates = (0..9).filter {
        numToChars(it).length == num1String.length &&
                (num1String - num2String - mapping.values.joinToString("")).length == 1 &&
                (numToChars(num1Int) - numToChars(it) - mapping.keys.joinToString("")).length == 1
    }
    if (candidates.size == 1) {
        return candidates[0]
    }

    return null
}


private infix fun String.diff(other: String): String {
    val thisSet = toSet()
    val otherSet = other.toSet()
    val intersect = thisSet.intersect(otherSet)
    return ((thisSet - intersect) + (otherSet - intersect)).joinToString("").sorted()
}

fun String.sorted() = toCharArray().sorted().joinToString("")

fun charsToNum(chars: String) = when (chars.sorted()) {
    "abcefg" -> 0
    "cf" -> 1
    "acdeg" -> 2
    "acdfg" -> 3
    "bdcf" -> 4
    "abdfg" -> 5
    "abdefg" -> 6
    "acf" -> 7
    "abcdefg" -> 8
    "abcdfg" -> 9
    else -> throw Exception("No")
}

fun numToChars(num: Int) = when (num) {
    0 -> "abcefg"
    1 -> "cf"
    2 -> "acdeg"
    3 -> "acdfg"
    4 -> "bdcf"
    5 -> "abdfg"
    6 -> "abdefg"
    7 -> "acf"
    8 -> "abcdefg"
    9 -> "abcdfg"
    else -> throw Exception("No")
}
