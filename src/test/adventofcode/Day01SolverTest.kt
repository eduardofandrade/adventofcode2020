package adventofcode

import org.junit.Assert
import org.junit.Test

class Day01SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "1721\n" +
                "979\n" +
                "366\n" +
                "299\n" +
                "675\n" +
                "1456"

        val solver = Day01Solver(exampleInput.byteInputStream())
        Assert.assertEquals(514579, solver.getPartOneSolution())
        Assert.assertEquals(241861950, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 01 ==========")
        val solver = Day01Solver(
                {}.javaClass.getResourceAsStream("day01Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}