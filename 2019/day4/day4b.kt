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
    var i = 0
    while (i <= input.length - 2) {
        if (input[i] == input[i + 1]) {
            if (i >= 1 && input[i] == input[i - 1]) {
                i+=2
                continue
            }
            if (i == input.length - 2) {
                return true
            }
            if (input[i] == input[i + 2]) {
                i += 2
                continue
            }
            return true
        }
        i++
    }
    return false
}


private val puzzleInput = 124075..580769


fun main() {
    var counter = 0

    for (x in puzzleInput) {
        val toString = x.toString()
        if (isIncreasing(toString) && hasValidDouble(toString)) {
            counter++
        }
    }
    println(counter)
    println("answer is correct: ${counter==1462}")
}