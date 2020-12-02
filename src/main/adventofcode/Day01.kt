package adventofcode

import java.io.InputStream
import java.util.*

class Day01(stream: InputStream) {

    private val processedInput: ArrayList<Int>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): ArrayList<Int> {
        val l = arrayListOf<Int>()

        val s = Scanner(stream)
        while (s.hasNextLine()) {
            val i = s.nextLine()
            if (i != "") {
                l.add(Integer.valueOf(i))
            } else {
                break
            }
        }

        return l
    }

    fun getFirstSolution(): Int {
        val l = processedInput
        for (i in 0 until l.size) {
            if (l.contains(2020 - l[i])) {
                return l[i] * (2020 - l[i])
            }
        }
        return 0
    }

    fun getSecondSolution(): Int {
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