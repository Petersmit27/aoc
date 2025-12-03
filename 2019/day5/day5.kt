@file:Suppress("DuplicatedCode")

package twothousandnineteen.day5

import java.io.File


private const val input = 5

fun main() {

    var pc = 0
    val memory = File("2019/day5/input.txt").readText().split(",").map { it.trim().toInt() }.toMutableList()


    loop@ while (pc < memory.size) {
        val instruction = String.format("%05d", memory[pc])
        when (instruction.last()) {
            '1' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                if (instruction[0] == '0')
                    memory[memory[pc + 3]] = param1 + param2
                else
                    memory[pc + 3] = param1 + param2
                pc += 4
            }
            '2' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                if (instruction[0] == '0')
                    memory[memory[pc + 3]] = param1 * param2
                else
                    memory[pc + 3] = param1 * param2
                pc += 4
            }
            '3' -> {
                memory[memory[pc + 1]] = input
                pc += 2
            }
            '4' -> {
                if (instruction[2] == '1')
                    println(memory[pc + 1])
                else
                    println(memory[memory[pc + 1]])
                pc += 2
            }
            '5' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                if (param1 != 0) {
                    pc = param2
                } else {
                    pc += 3
                }
            }
            '6' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                if (param1 == 0) {
                    pc = param2
                } else {
                    pc += 3
                }
            }
            '7' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                val valueToStore = if (param1 < param2) 1 else 0
                if (instruction[0] == '0')
                    memory[memory[pc + 3]] = valueToStore
                else
                    memory[pc + 3] = valueToStore
                pc += 4
            }
            '8' -> {
                val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                val valueToStore = if (param1 == param2) 1 else 0
                if (instruction[0] == '0')
                    memory[memory[pc + 3]] = valueToStore
                else
                    memory[pc + 3] = valueToStore
                pc += 4
            }
            '9' -> {
                if (instruction == "00099") {
                    break@loop
                }
            }
            else ->
                throw Exception("bruh this aint good")
        }
    }
}

