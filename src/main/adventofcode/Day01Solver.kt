package adventofcode

import java.io.InputStream
import java.util.*

class Day01Solver(stream: InputStream) : Solver {

    private val processedInput: ArrayList<Long>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): ArrayList<Long> {
        val lines = stream.bufferedReader().readLines()

        val l = arrayListOf<Long>()
        for (line in lines) {
            l.add(line.toLong())
        }
        return l
    }

    override fun getFirstSolution(): Long {
        val l = processedInput
        for (i in 0 until l.size) {
            if (l.contains(2020 - l[i])) {
                return l[i] * (2020 - l[i])
            }
        }
        return 0
    }

    override fun getSecondSolution(): Long {
        val l = processedInput
        for (i in 0 until l.size) {
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