package adventofcode

import org.junit.Assert
import org.junit.Test

class Day09SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "35\n" +
                "20\n" +
                "15\n" +
                "25\n" +
                "47\n" +
                "40\n" +
                "62\n" +
                "55\n" +
                "65\n" +
                "95\n" +
                "102\n" +
                "117\n" +
                "150\n" +
                "182\n" +
                "127\n" +
                "219\n" +
                "299\n" +
                "277\n" +
                "309\n" +
                "576"

        val solver = Day09Solver(exampleInput.byteInputStream(), 5)
        Assert.assertEquals(127, solver.getPartOneSolution())
        Assert.assertEquals(62, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 09 ==========")
        val solver = Day09Solver(
                {}.javaClass.getResourceAsStream("day09Input.txt"), 25
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}