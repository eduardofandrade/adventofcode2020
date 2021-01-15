package adventofcode

import org.junit.Assert
import org.junit.Test

class Day12SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "F10\n" +
                "N3\n" +
                "F7\n" +
                "R90\n" +
                "F11"

        val solver = Day12Solver(exampleInput.byteInputStream())
        Assert.assertEquals(25, solver.getPartOneSolution())
        Assert.assertEquals(286, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 12 ==========")
        val solver = Day12Solver(
                {}.javaClass.getResourceAsStream("day12Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}