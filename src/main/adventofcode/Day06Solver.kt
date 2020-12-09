package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day06Solver(stream: InputStream) : Solver {

    private val groups: ArrayList<Group> = arrayListOf()

    init {
        processInput(stream)
    }

    private fun processInput(stream: InputStream) {
        var g: Group? = null
        stream.bufferedReader().readLines().stream()
                .forEach { line ->
                    if (g == null || line.isBlank()) {
                        g = Group()
                        groups.add(g!!)
                    }
                    val p = Person()
                    p.addAnswer(line)
                    g!!.persons.add(p)
                }
    }

    override fun getPartOneSolution(): Long {
        return groups.stream().map { g -> g.getNumberOfUniqueAnswers() }.collect(Collectors.toList()).sum().toLong()
    }

    override fun getPartTwoSolution(): Long {
        return groups.stream().map { g -> g.getNumberOfUniqueAnswersThatEveryoneGave() }.collect(Collectors.toList()).sum().toLong()
    }

    private class Group {
        val persons: ArrayList<Person> = arrayListOf()

        fun getNumberOfUniqueAnswers(): Int {
            return persons.stream().flatMap { p -> p.answers.stream() }.collect(Collectors.toSet()).count()
        }

        fun getNumberOfUniqueAnswersThatEveryoneGave(): Int {
            val answersPerPerson = persons.stream().map { p -> p.answers }.filter { l -> l.isNotEmpty() }.collect(Collectors.toList())
            var answersEveryoneGave: MutableSet<Char>? = if (answersPerPerson.isNotEmpty()) answersPerPerson[0] else mutableSetOf()
            for (answers in answersPerPerson) {
                answersEveryoneGave!!.retainAll(answers)
            }
            return answersEveryoneGave!!.count()
        }
    }

    private class Person {
        val answers = mutableSetOf<Char>()

        fun addAnswer(answer: String) {
            for (c in answer) {
                answers.add(c)
            }
        }
    }

}