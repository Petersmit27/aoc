package twothousandnineteen.day1

import java.io.File

fun main() {
    var totalFuel = 0

    File("2019/day1/input.txt").forEachLine {

        totalFuel += (it.toInt() / 3) - 2
    }
    println(totalFuel)
}