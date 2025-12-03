@file:Suppress("DuplicatedCode")

package twothousandnineteen.day7

import java.io.File


fun main() {

    val phasePerms = permute("5,6,7,8,9".split(",").map { it.toInt() })
    val memory = File("2019/day7/input.txt").readText().split(",").map { it.trim().toInt() }

    val outputMap = HashMap<Int, List<Int>>()


    //go through all possible phases
    for (perm in phasePerms) {
        //create a list of 5 amplifiers and fill it
        val amplifiers = ArrayList<Amplifier>()
        for (i in 0..4) {
            amplifiers.add(Amplifier(memory.toMutableList(), i))
        }

        //execute once with the phases as the input
        for (i in 0..4) {
            amplifiers[i].executeOnce(perm[i])
        }
        //keep executing until one of the programs is halted
        var done = false
        while (!done) {
            for (i in 0..4) {
                val amplifier = amplifiers[i]
                amplifier.executeOnce(amplifiers[(i + 4) % 5].output)
                if (amplifier.halted) done = true
            }
        }
        //add output + phase configuration to a map
        outputMap[amplifiers[4].output] = perm
    }
    //get max output and print it together with its phase configuration (kinda useless, but oh well)
    val maxOutput = outputMap.keys.max()
    println("$maxOutput ${outputMap[maxOutput]}")
}

class Amplifier(private val memory: MutableList<Int>, val type: Int) {
    var halted = false

    var output: Int = 0
    var pc = 0

    fun executeOnce(input: Int) {

        //lmao what the fuck am i doing
        memory[memory[pc + 1]] = input
        pc += 2

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
                    return
                }
                '4' -> {
                    output = if (instruction[2] == '1') memory[pc + 1] else memory[memory[pc + 1]]
                    pc += 2
                }
                '5' -> {
                    val param1 = if (instruction[2] == '0') memory[memory[pc + 1]] else memory[pc + 1]
                    val param2 = if (instruction[1] == '0') memory[memory[pc + 2]] else memory[pc + 2]
                    pc = if (param1 != 0) param2 else pc + 3
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
                        halted = true
                        pc = 6
                        return
                    } else
                        throw Exception("wat?")
                }
                else ->
                    throw Exception("bruh this aint good")
            }
        }
    }

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