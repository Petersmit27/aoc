package twothousandnineteen.day4

private fun isIncreasing(input: String): Boolean {
    for (i in 0..input.length - 2) {
        if (input[i] > input[i + 1]) {
            return false
        }
    }
    return true
}

private fun hasValidDouble(input: String): Boolean {

    for (i in 0..input.length - 2) {
        if (input[i] == input[i + 1]) {
            return true
        }
    }
    return false
}


private val puzzleInput = 124075..580769


fun main() {
    var counter = 0

    for (x in puzzleInput) {
        val toString = x.toString()
        if (hasValidDouble(toString) && isIncreasing(toString)) {
            counter++
        }
    }
    println(counter)
}