package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day02Solver(stream: InputStream) : Solver {

    private val processedInput: List<PasswordPolicyChecker>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<PasswordPolicyChecker> {
        return stream.bufferedReader().readLines().stream()
                .map { line ->
                    val tokens = line.split(" ")
                    val range = tokens[0].split("-")
                    PasswordPolicyChecker(range[0].toInt(), range[1].toInt(), tokens[1][0], tokens[2])
                }.collect(Collectors.toList())
    }

    override fun getPartOneSolution(): Long {
        return processedInput.stream().filter { p -> p.validOld() }.count()
    }

    override fun getPartTwoSolution(): Long {
        return processedInput.stream().filter { p -> p.validNew() }.count()
    }

    private class PasswordPolicyChecker(
            val min: Int,
            val max: Int,
            val letter: Char,
            val passwd: String) {

        fun validOld(): Boolean {
            val occurrences = passwd.toCharArray()
                    .filter { c -> c == this.letter }
                    .count()
            return occurrences in min..max
        }

        fun validNew(): Boolean {
            return (passwd[min - 1] == letter && passwd[max - 1] != letter) ||
                    (passwd[min - 1] != letter && passwd[max - 1] == letter)
        }
    }

}
