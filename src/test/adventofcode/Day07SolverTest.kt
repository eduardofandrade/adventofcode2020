package adventofcode

import org.junit.Assert
import org.junit.Test

class Day07SolverTest {

    @Test
    fun validateExampleOne() {
        val exampleInput = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
                "bright white bags contain 1 shiny gold bag.\n" +
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags."

        val solver = Day07Solver(exampleInput.byteInputStream())
        Assert.assertEquals(4, solver.getPartOneSolution())
        Assert.assertEquals(32, solver.getPartTwoSolution())
    }

    @Test
    fun validateExampleTwo() {
        val exampleInput = "shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags."

        val solver = Day07Solver(exampleInput.byteInputStream())
        Assert.assertEquals(126, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 07 ==========")
        val solver = Day07Solver(
                {}.javaClass.getResourceAsStream("day07Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}