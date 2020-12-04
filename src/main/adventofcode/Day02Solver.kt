package adventofcode

import java.io.InputStream
import java.util.*

class Day02Solver(stream: InputStream) : Solver {

    private val processedInput: ArrayList<PasswordPolicyChecker>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): ArrayList<PasswordPolicyChecker> {
        val lines = stream.bufferedReader().readLines()

        val l = arrayListOf<PasswordPolicyChecker>()
        for (line in lines) {
            val tokens = line.split(" ")
            val range = tokens[0].split("-")
            l.add(
                    PasswordPolicyChecker(
                            range[0].toInt(),
                            range[1].toInt(),
                            tokens[1][0],
                            tokens[2]
                    )
            )

        }
        return l
    }

    override fun getFirstSolution(): Long {
        return processedInput.stream().filter { p -> p.validOld() }.count()
    }

    override fun getSecondSolution(): Long {
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
            return occurrences in min..max;
        }

        fun validNew(): Boolean {
            return (passwd[min - 1] == letter && passwd[max - 1] != letter) ||
                    (passwd[min - 1] != letter && passwd[max - 1] == letter)
        }
    }

}
