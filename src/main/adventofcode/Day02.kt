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
                val range = tokens[0].split("-")
                l.add(
                        PasswordPolicyChecker(
                                Integer.valueOf(range[0]),
                                Integer.valueOf(range[1]),
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
