@file:Suppress("DuplicatedCode")

package twothousandnineteen.day2

import java.io.File

fun main() {
    val initMemory = File("2019/day2/input.txt").readText().split(",").map { it.trim().toInt() }
    var memory: MutableList<Int>


    startLoop@ for (verb in 0..99) {
        for (noun in 0..99) {

            memory = initMemory.toMutableList()

            memory[1] = verb
            memory[2] = noun

            try {
                loop@ for (i in memory.indices step 4) {
                    when (memory[i]) {
                        1 ->
                            memory[memory[i + 3]] =
                                memory[memory[i + 1]] + memory[memory[i + 2]]
                        2 ->
                            memory[memory[i + 3]] =
                                memory[memory[i + 1]] * memory[memory[i + 2]]
                        99 -> {
                            break@loop
                        }
                        else ->
                            throw IndexOutOfBoundsException()
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
            }

            if (memory[0] == 19690720) {
                println(memory[0])
                println("verb is $verb")
                println("noun is $noun")
                break@startLoop
            }
        }
    }

}

