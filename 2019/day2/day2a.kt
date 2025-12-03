package twothousandnineteen.day2

import java.io.File

fun main() {


    val memory = File("2019/day2/input.txt").readText().split(",").map { it.trim().toInt() }.toMutableList()

    memory[1] = 12
    memory[2] = 2


    loop@ for (i in memory.indices step 4) {
        when (memory[i]) {
            1 ->
                memory[memory[i + 3]] =
                    memory[memory[i + 1]] + memory[memory[i + 2]]
            2 ->
                memory[memory[i + 3]] =
                    memory[memory[i + 1]] * memory[memory[i + 2]]
            99 ->
                break@loop
            else ->
                throw IndexOutOfBoundsException()

        }
    }
    println(memory)

}