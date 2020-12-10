package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day09Solver(stream: InputStream, var preambleSize: Int) : Solver {

    private val processedInput: List<Long>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<Long> {
        return stream.bufferedReader().readLines().stream()
                .map { s -> s.toLong() }
                .collect(Collectors.toList())
    }

    override fun getPartOneSolution(): Long {
        return findInvalidNumber()
    }

    override fun getPartTwoSolution(): Long {
        val contiguousSetForInvalidNumber = findContiguousSetForInvalidNumber(findInvalidNumber())
        val max = contiguousSetForInvalidNumber.maxOrNull()!!
        val min = contiguousSetForInvalidNumber.minOrNull()!!
        return max + min
    }

    private fun findInvalidNumber(): Long {
        for (i in preambleSize until processedInput.size) {
            val number = processedInput[i]
            if (!isSumOfAnyTwoNumbersInList(number, processedInput.subList(i - preambleSize, i))) {
                return number
            }
        }
        return 0
    }

    private fun isSumOfAnyTwoNumbersInList(number: Long, list: List<Long>): Boolean {
        for (i in list.indices) {
            for (j in i until list.size) {
                if (list[i] + list[j] == number) {
                    return true
                }
            }
        }
        return false
    }

    private fun findContiguousSetForInvalidNumber(invalidNumber: Long): List<Long> {
        var contiguousSum: Long
        val invalidNumberIndex: Int = processedInput.indexOf(invalidNumber)

        for (i in 0..invalidNumberIndex - 2) {
            contiguousSum = processedInput[i]
            for (j in i + 1 until invalidNumberIndex) {
                contiguousSum += processedInput[j]
                if (contiguousSum > invalidNumber) {
                    break
                } else if (contiguousSum == invalidNumber) {
                    return processedInput.subList(i, j + 1)
                }
            }
        }
        return arrayListOf()
    }
}