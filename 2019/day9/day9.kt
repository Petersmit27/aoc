@file:Suppress("DuplicatedCode")

package twothousandnineteen.day9

import java.io.File


private const val INPUT = 2

fun main() {

    var pc: Long = 0
    val program = File("2019/day9/input.txt").readText().split(",").map { it.trim().toLong() }.toMutableList()

    val memory = MemoryMap()
    for ((i, x) in program.withIndex()) {
        memory[i.toLong()] = x
    }

    var relativeBase: Long = 0

    loop@ while (pc < memory.size) {
        val instruction = String.format("%05d", memory[pc])
        when (instruction.last()) {
            '1' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                when (instruction[0]) {
                    '0' -> memory[memory[pc + 3]] = param1 + param2
                    '1' -> memory[pc + 3] = param1 + param2
                    '2' -> memory[relativeBase + memory[pc + 3]] = param1 + param2
                }
                pc += 4
            }
            '2' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                when (instruction[0]) {
                    '0' -> memory[memory[pc + 3]] = param1 * param2
                    '1' -> memory[pc + 3] = param1 * param2
                    '2' -> memory[relativeBase + memory[pc + 3]] = param1 * param2
                }
                pc += 4
            }
            '3' -> {
                if (instruction[2] == '0') {
                    memory[memory[pc + 1]] = INPUT.toLong()
                } else {
                    memory[memory[pc + 1] + relativeBase] = INPUT.toLong()
                }
                pc += 2
            }
            '4' -> {
                when (instruction[2]) {
                    '0' -> println("${memory[memory[pc + 1]]} $pc")
                    '1' -> println("${memory[pc + 1]} $pc")
                    '2' -> println("${memory[memory[pc + 1] + relativeBase]} $pc")
                }
                pc += 2
            }
            '5' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                if (param1 != 0L) {
                    pc = param2
                } else {
                    pc += 3
                }
            }
            '6' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                if (param1 == 0L) {
                    pc = param2
                } else {
                    pc += 3
                }
            }
            '7' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                val valueToStore = if (param1 < param2) 1L else 0L
                when (instruction[0]) {
                    '0' -> memory[memory[pc + 3]] = valueToStore
                    '1' -> memory[pc + 3] = valueToStore
                    '2' -> memory[relativeBase + memory[pc + 3]] = valueToStore
                }
                pc += 4
            }
            '8' -> {
                val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                val param2 = getParam(instruction[1], memory, pc + 2, relativeBase)
                val valueToStore = if (param1 == param2) 1L else 0L
                when (instruction[0]) {
                    '0' -> memory[memory[pc + 3]] = valueToStore
                    '1' -> memory[pc + 3] = valueToStore
                    '2' -> memory[relativeBase + memory[pc + 3]] = valueToStore
                }
                pc += 4
            }
            '9' -> {
                if (instruction == "00099") {
                    break@loop
                } else {
                    val param1 = getParam(instruction[2], memory, pc + 1, relativeBase)
                    relativeBase += param1
                    pc += 2
                }
            }
            else ->
                throw Exception("bruh this aint good")
        }
    }
}

private fun getParam(
    mode: Char,
    memory: MemoryMap,
    location: Long,
    relativeBase: Long
) =
    when (mode) {
        '0' -> memory[memory[location]]
        '1' -> memory[location]
        else -> memory[relativeBase + memory[location]]
    }

private class MemoryMap : HashMap<Long, Long>() {
    override fun get(key: Long): Long {
        val getValue = super.get(key)
        return getValue ?: 0
    }
}
