package adventofcode

import org.junit.Assert
import org.junit.Test

class Day06SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b"

        val solver = Day06Solver(exampleInput.byteInputStream())
        Assert.assertEquals(11, solver.getPartOneSolution())
        Assert.assertEquals(6, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 06 ==========")
        val solver = Day06Solver(
                {}.javaClass.getResourceAsStream("day06Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}