@file:Suppress("DuplicatedCode")

package twothousandnineteen.day7

import java.io.File


fun main() {

    val phasePerms = permute("0,1,2,3,4".split(",").map { it.toInt() })
    val memory = File("2019/day7/input.txt").readText().split(",").map { it.trim().toInt() }

    val outputMap = HashMap<Int,List<Int>>()


    for (perm in phasePerms) {
        var input = 0
        for (phase in perm) {
            input = executeOnce(memory.toMutableList(), input, phase)
        }
        outputMap[input]=perm
    }
    val maxOutput = outputMap.keys.max()
    println("$maxOutput ${outputMap[maxOutput]}")



}

private fun executeOnce(memory: MutableList<Int>, input: Int, phase: Int): Int {
    var pc = 0
    var phaseUsed = false
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
                memory[memory[pc + 1]] = if (phaseUsed) input else phase
                phaseUsed = true
                pc += 2
            }
            '4' -> {
                return if (instruction[2] == '1')
                    memory[pc + 1]
                else
                    memory[memory[pc + 1]]
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
                pc = if (param1 == 0) param2 else pc + 3
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
    return -1
}

//stolen from https://rosettacode.org/wiki/Permutations#Kotlin
private fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}