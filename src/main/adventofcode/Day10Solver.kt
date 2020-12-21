package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day10Solver(stream: InputStream) : Solver {

    private val processedInput: List<Long>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<Long> {
        val input = arrayListOf<Long>(0)
        stream.bufferedReader().readLines().stream()
                .map { s -> s.toLong() }
                .sorted()
                .collect(Collectors.toCollection { input })
        input.add(input[input.size - 1] + 3)
        return input
    }

    override fun getPartOneSolution(): Long {
        var joltDiffs = hashMapOf<Long, Long>()

        joltDiffs[processedInput[0]] = 1
        for (i in 1 until processedInput.size) {
            val diff = processedInput[i] - processedInput[i - 1]
            val value = joltDiffs.getOrDefault(diff, 0)
            joltDiffs[diff] = value + 1
        }

        return joltDiffs[1]!! * joltDiffs[3]!!
    }

    override fun getPartTwoSolution(): Long {
        return countArrangements(processedInput.size - 1, hashMapOf())
    }


    private fun countArrangements(index: Int, cache: HashMap<Long, Long>): Long {
        val inputValue = processedInput[index]

        if (cache.containsKey(inputValue)) {
            return cache[inputValue]!!
        }

        var result: Long = 0

        if (index == 0) {
            result = 1

        } else {
            if (index >= 1 && inputValue - processedInput[index - 1] <= 3) {
                result += countArrangements(index - 1, cache)
            }
            if (index >= 2 && inputValue - processedInput[index - 2] <= 3) {
                result += countArrangements(index - 2, cache)
            }
            if (index >= 3 && inputValue - processedInput[index - 3] <= 3) {
                result += countArrangements(index - 3, cache)
            }
        }
        cache[inputValue] = result

        return result
    }
}