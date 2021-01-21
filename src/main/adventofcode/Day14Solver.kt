package adventofcode

import java.io.InputStream
import java.math.BigInteger
import java.util.*


class Day14Solver(stream: InputStream) : Solver {

    private var instructions: ArrayList<Instruction> = arrayListOf()

    init {
        stream.bufferedReader().readLines().forEach { line: String ->
            if (line.startsWith("mask")) {
                instructions.add(Mask(line.replace("mask = ", "")))
            } else {
                val writeData = line.replace("\\[|\\]|mem| ".toRegex(), "").split("=")
                instructions.add(
                        WriteOperation(BigInteger.valueOf(writeData[0].toLong()), BigInteger.valueOf(writeData[1].toLong()))
                )
            }
        }
    }

    override fun getPartOneSolution(): Long {
        val memory = hashMapOf<BigInteger, BigInteger>()
        var mask = ""
        instructions.forEach { instruction ->
            if (instruction is Mask) {
                mask = instruction.value
            } else if (instruction is WriteOperation) {
                memory[instruction.address] = BitMasker.applyMaskToValue(mask, instruction.value)
            }
        }
        return memory.values.reduce { sum, value -> sum + value }.toLong()
    }

    override fun getPartTwoSolution(): Long {
        val memory = hashMapOf<BigInteger, BigInteger>()
        var mask = ""
        instructions.forEach { instruction ->
            if (instruction is Mask) {
                mask = instruction.value
            } else if (instruction is WriteOperation) {
                BitMasker.applyMaskToAddress(mask, instruction.address).forEach { address ->
                    memory[address] = instruction.value
                }
            }
        }
        return memory.values.reduce { sum, value -> sum + value }.toLong()
    }

    private interface Instruction

    private class Mask(var value: String) : Instruction

    private class WriteOperation(var address: BigInteger, var value: BigInteger) : Instruction

    private class BitMasker {
        companion object {
            fun applyMaskToValue(mask: String, value: BigInteger): BigInteger {
                val binaryValue = value.toString(2).padStart(mask.length, '0').toCharArray()
                for (i in (mask.length - 1) downTo 0) {
                    val maskBit = mask[i]
                    if (maskBit != 'X') {
                        val pos = binaryValue.size - 1 - (mask.length - 1 - i)
                        binaryValue[pos] = maskBit
                    }
                }
                return BigInteger(String(binaryValue), 2)
            }

            fun applyMaskToAddress(mask: String, address: BigInteger): List<BigInteger> {
                var addresses = listOf("")
                val binaryAddress = address.toString(2).padStart(mask.length, '0').toCharArray()
                mask.forEachIndexed { i, maskBit ->
                    addresses = when (maskBit) {
                        'X' -> addresses.flatMap { listOf(it + 0, it + 1) }
                        '1' -> addresses.map { it + maskBit }
                        else -> addresses.map { it + binaryAddress.getOrElse(binaryAddress.size - mask.length + i) { '0' } }
                    }
                }
                return addresses.map { it.toBigInteger(2) }
            }
        }
    }

}