package twothousandnineteen.day6

import java.io.File
import kotlin.system.exitProcess

fun main() {
    val nodeToOrbiting = HashMap<String, String>()

    File("2019/day6/input.txt").forEachLine {
        nodeToOrbiting[it.split(")")[1]] = it.split(")")[0]
    }

    val youList = ArrayList<String>()
    val sanList = ArrayList<String>()
    var lastSeenYou = "YOU"
    var lastSeenSan = "SAN"
    while (true) {
        lastSeenYou = nodeToOrbiting[lastSeenYou]!!
        youList.add(lastSeenYou)
        lastSeenSan = nodeToOrbiting[lastSeenSan]!!
        sanList.add(lastSeenSan)


        if ((youList intersect sanList).isNotEmpty()) {
            val intersection = (youList intersect sanList).first()
            var counter = 0
            var lastSeenNode = "YOU"
            while (lastSeenNode != intersection) {
                lastSeenNode = nodeToOrbiting[lastSeenNode]!!
                counter++
            }
            lastSeenNode = "SAN"
            while (lastSeenNode != intersection) {
                lastSeenNode = nodeToOrbiting[lastSeenNode]!!
                counter++
            }
            counter-=2
            println(counter)
            exitProcess(420)
        }
    }
}