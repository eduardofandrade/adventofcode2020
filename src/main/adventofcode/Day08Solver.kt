package adventofcode

import java.io.InputStream
import java.util.*

class Day08Solver(stream: InputStream) : Solver {

    private val instructions: ArrayList<Instruction> = arrayListOf()

    init {
        processInput(stream)
    }

    private fun processInput(stream: InputStream) {
        var i = 0
        stream.bufferedReader().readLines().stream()
                .forEach { line ->
                    val instruction = line.split(" ")
                    instructions.add(
                            Instruction(i++, Operation.valueOf(instruction[0].toUpperCase()), instruction[1].toInt())
                    )
                }
    }

    override fun getPartOneSolution(): Long {
        return run(instructions).accumulator
    }

    override fun getPartTwoSolution(): Long {
        val indexesToModify = instructions.filter { i -> i.operation in EnumSet.of(Operation.ACC, Operation.JMP) }.map { i -> i.index }
        return runWithModifications(instructions, indexesToModify).accumulator
    }

    private fun run(instructions: ArrayList<Instruction>): Runtime {
        val runtime = Runtime()
        runtime.run(instructions)
        return runtime
    }

    private fun runWithModifications(instructions: ArrayList<Instruction>, indexesToModify: List<Int>): Runtime {
        indexesToModify.forEach {
            modifyInstructionOperation(instructions, it)
            val runtime = run(instructions)
            modifyInstructionOperation(instructions, it)
            if (!runtime.infiniteLoopDetected) {
                return runtime
            }
        }
        return Runtime()
    }

    private fun modifyInstructionOperation(instructions: ArrayList<Instruction>, index: Int) {
        when (instructions[index].operation) {
            Operation.JMP -> instructions[index].operation = Operation.NOP
            Operation.NOP -> instructions[index].operation = Operation.JMP
        }
    }

    private class Instruction(val index: Int, var operation: Operation, var argument: Int)

    private enum class Operation {
        NOP, ACC, JMP
    }

    private class Runtime {
        private val executedInstructions: HashSet<Int> = hashSetOf()
        var accumulator: Long = 0
        var infiniteLoopDetected = false

        // runs instructions avoiding infinite loop
        fun run(instructions: ArrayList<Instruction>) {
            var index = 0
            while (index < instructions.size && !infiniteLoopDetected(index)) {
                index = executeInstruction(instructions[index], index)
            }
        }

        private fun executeInstruction(instruction: Instruction, i: Int): Int {
            var index = i
            when (instruction.operation) {
                Operation.ACC -> {
                    accumulator += instruction.argument; index++
                }
                Operation.JMP -> index += instruction.argument
                Operation.NOP -> index++
            }
            return index
        }

        private fun infiniteLoopDetected(i: Int): Boolean {
            infiniteLoopDetected = executedInstructions.contains(i)
            if (!infiniteLoopDetected) {
                executedInstructions.add(i)
            }
            return infiniteLoopDetected
        }
    }
}