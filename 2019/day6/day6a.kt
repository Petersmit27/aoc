package twothousandnineteen.day6

import java.io.File

fun main() {
    val nodeToOrbiting = HashMap<String, String>()

    var counter=0

    File("2019/day6/input.txt").forEachLine {
        nodeToOrbiting[it.split(")")[1]] = it.split(")")[0]
    }
    for (x in nodeToOrbiting.keys) {
        var lastSeenNode = x
        while (lastSeenNode != "COM") {
            lastSeenNode = nodeToOrbiting[lastSeenNode]!!
            counter++
        }
    }
    println(counter)
}