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
        val l = processedInput
        for (i in l.indices) {
            if (l.contains(2020 - l[i])) {
                return l[i] * (2020 - l[i])
            }
        }
        return 0
    }

    override fun getSecondSolution(): Long {
        val l = processedInput
        for (i in l.indices) {
            for (j in 0 until i) {
                if (l[i] + l[j] < 2020) {
                    if (l.contains(2020 - l[i] - l[j])) {
                        return l[i] * l[j] * (2020 - l[i] - l[j])
                    }
                }
            }
        }
        return 0
    }
}