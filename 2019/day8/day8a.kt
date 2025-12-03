@file:Suppress("DuplicatedCode")

package twothousandnineteen.day8

import java.io.File

private const val WIDTH = 25
private const val HEIGHT = 6

fun main() {
    val layers = ArrayList<ArrayList<List<Int>>>()

    val numbers = File("2019/day8/input.txt").readText().split("").drop(1)
    var counter = 0

    //too overcomplicated for part a but oh well ¯\_(ツ)_/¯
    try {
        while (counter < numbers.size) {
            val layer = ArrayList<List<Int>>()
            for (i in 0 until HEIGHT) {
                //add a row to the layer
                layer.add(numbers.subList(counter, counter + WIDTH).map { it.toInt() })
                counter += WIDTH
            }
            layers.add(layer)
        }
    } catch (e: IndexOutOfBoundsException) {
    }


    val zeroCounts = ArrayList<Int>()
    for (layer in layers) {
        zeroCounts.add(layer.flatten().count { it == 0 })
    }
    val lowestZeroes = layers[zeroCounts.indexOf(zeroCounts.min())]
    val flattenedLayer = lowestZeroes.flatten()
    println("Amount of zeroes: ${flattenedLayer.count { it == 0 }}")
    println("Amount of ones: ${flattenedLayer.count { it == 1 }}")
    println("Amount of twos: ${flattenedLayer.count { it == 2 }}")
    println("Ones * twos == ${flattenedLayer.count { it == 1 } * flattenedLayer.count { it == 2 }}")

}





