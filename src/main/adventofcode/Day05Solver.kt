package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day05Solver(stream: InputStream) : Solver {

    private val processedInput: List<Seat>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<Seat> {
        return stream.bufferedReader().readLines().stream()
                .map { code -> Seat(code) }
                .collect(Collectors.toList())
    }

    override fun getPartOneSolution(): Long {
        return processedInput.stream()
                .map { seat -> seat.getSeatID() }
                .max { o1, o2 -> o1.compareTo(o2) }
                .orElse(0)
    }

    override fun getPartTwoSolution(): Long {
        val listOfSeatIDs = processedInput.stream()
                .map { seat -> seat.getSeatID() }
                .sorted()
                .collect(Collectors.toList())

        for (i in 1 until listOfSeatIDs.size) {
            if (listOfSeatIDs[i] - listOfSeatIDs[i - 1] == 2L) {
                return listOfSeatIDs[i] - 1
            }
        }
        return 0
    }

    private class Seat(val code: String) {

        fun getSeatID(): Long {
            return getRow().toLong() * 8 + getColumn().toLong()
        }

        private fun getRow(): Int {
            var lower = 0
            var upper = 127
            for (i in 0..6) {
                when {
                    code[i] == 'F' -> upper -= ((upper - lower + 1) / 2)
                    code[i] == 'B' -> lower += ((upper - lower + 1) / 2)
                }
            }
            return upper// or lower, they are the same
        }

        private fun getColumn(): Int {
            var lower = 0
            var upper = 7
            for (i in 7..9) {
                when {
                    code[i] == 'L' -> upper -= ((upper - lower + 1) / 2)
                    code[i] == 'R' -> lower += ((upper - lower + 1) / 2)
                }
            }
            return upper // or lower, they are the same
        }
    }

}
