package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day01Solver(stream: InputStream) : Solver {

    private val processedInput: List<Long>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<Long> {
        return stream.bufferedReader().readLines().stream()
                .map { s -> s.toLong() }
                .collect(Collectors.toList())
    }

    override fun getFirstSolution(): Long {
        for (n in processedInput) {
            if (processedInput.contains(2020 - n)) {
                return n * (2020 - n)
            }
        }
        return 0
    }

    override fun getSecondSolution(): Long {
        for (n in processedInput) {
            for (m in 0 until n) {
                if (n + m < 2020 && processedInput.contains(2020 - n - m)) {
                    return n * m * (2020 - n - m)
                }
            }
        }
        return 0
    }
}