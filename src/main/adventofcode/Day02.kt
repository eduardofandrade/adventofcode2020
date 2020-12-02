package adventofcode

import java.io.InputStream
import java.util.*

class Day02(stream: InputStream) {

    private val processedInput: ArrayList<PasswordPolicyChecker>

    init {
        processedInput = processInput(stream)
    }

    fun getFirstSolution(): Int {
        return processedInput.stream().filter { p -> p.validOld() }.count().toInt()
    }

    fun getSecondSolution(): Int {
        return processedInput.stream().filter { p -> p.validNew() }.count().toInt()
    }

    private fun processInput(stream: InputStream): ArrayList<PasswordPolicyChecker> {
        val l = arrayListOf<PasswordPolicyChecker>()

        val s = Scanner(stream)
        while (s.hasNextLine()) {
            val line = s.nextLine()
            if (line != "") {
                val tokens = line.split(" ")
                val occurrencesRange = tokens[0].split("-")
                l.add(
                        PasswordPolicyChecker(
                                Integer.valueOf(occurrencesRange[0]),
                                Integer.valueOf(occurrencesRange[1]),
                                tokens[1][0],
                                tokens[2]
                        )
                )
            } else {
                break
            }
        }
        return l
    }

    private class PasswordPolicyChecker(
            val minOccurrences: Int,
            val maxOccurrences: Int,
            val letter: Char,
            val password: String) {

        fun validOld(): Boolean {
            val occurrences = password.toCharArray()
                    .filter { c -> c == this.letter }
                    .count()
            return occurrences in minOccurrences..maxOccurrences;
        }

        fun validNew(): Boolean {
            return (password[minOccurrences - 1] == letter && password[maxOccurrences - 1] != letter) ||
                    (password[minOccurrences - 1] != letter && password[maxOccurrences - 1] == letter)
        }
    }

}
