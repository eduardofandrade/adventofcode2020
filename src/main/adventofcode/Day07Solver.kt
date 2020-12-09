package adventofcode

import java.io.InputStream

class Day07Solver(stream: InputStream) : Solver {

    private val bags: HashMap<String, Bag> = hashMapOf()

    init {
        processInput(stream)
    }

    private fun processInput(stream: InputStream) {
        val bagRuleRegex = " bags| bag[s]? | bag[s]?\\.|\\.\$| bag".toRegex()
        val containsRuleRegex = "([0-9]*) ([a-zA-Z]* [a-zA-Z]*)".toRegex()
        stream.bufferedReader().readLines().stream()
                .forEach { line ->
                    val ruleLines = line.replace(bagRuleRegex, "").split(" contain |, ".toPattern())
                    var b = Bag(ruleLines[0], 1)
                    for (i in 1 until ruleLines.size) {
                        containsRuleRegex.find(ruleLines[i])?.groupValues?.let {
                            val bagQuantity = it[1].toInt()
                            val bagName = it[2]
                            b.contains.put(bagName, Bag(bagName, bagQuantity))
                        }
                    }
                    bags[b.name] = b
                }
    }

    override fun getPartOneSolution(): Long {
        return bags.values.stream().filter { bag -> bag.containsShinyGoldBag(bags) }.count()
    }

    override fun getPartTwoSolution(): Long {
        return bags["shiny gold"]!!.getNumberOfContainedBags(bags).toLong()
    }

    private class Bag(val name: String, val quantity: Int) {
        val contains = hashMapOf<String, Bag>()

        fun containsShinyGoldBag(bags: HashMap<String, Bag>): Boolean {
            return contains.values.stream().anyMatch { bag ->
                (bag.quantity > 0 && bag.name == "shiny gold") || bags[bag.name]!!.containsShinyGoldBag(bags)
            }
        }

        fun getNumberOfContainedBags(bags: HashMap<String, Bag>): Int {
            return contains.values.map { bag ->
                bag.quantity + (bag.quantity * bags[bag.name]!!.getNumberOfContainedBags(bags))
            }.sum()
        }
    }

}