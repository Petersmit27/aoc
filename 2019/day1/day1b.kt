package twothousandnineteen.day1

import java.io.File

fun main() {
    var totalFuel = 0
    File("2019/day1/input.txt").forEachLine {
        var requiredFuel = it.toInt() / 3 - 2
        totalFuel += requiredFuel
        while (requiredFuel/3-2 > 0) {
            requiredFuel = requiredFuel / 3 - 2
            totalFuel += requiredFuel
        }
    }
    println(totalFuel)
}