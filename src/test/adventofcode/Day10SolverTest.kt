package adventofcode

import org.junit.Assert
import org.junit.Test

class Day10SolverTest {

    @Test
    fun validateExample() {
        val exampleInput1 = "16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4"

        val exampleInput2 = "28\n" +
                "33\n" +
                "18\n" +
                "42\n" +
                "31\n" +
                "14\n" +
                "46\n" +
                "20\n" +
                "48\n" +
                "47\n" +
                "24\n" +
                "23\n" +
                "49\n" +
                "45\n" +
                "19\n" +
                "38\n" +
                "39\n" +
                "11\n" +
                "1\n" +
                "32\n" +
                "25\n" +
                "35\n" +
                "8\n" +
                "17\n" +
                "7\n" +
                "9\n" +
                "4\n" +
                "2\n" +
                "34\n" +
                "10\n" +
                "3"

        var solver = Day10Solver(exampleInput1.byteInputStream())
        Assert.assertEquals(35, solver.getPartOneSolution())
        Assert.assertEquals(8, solver.getPartTwoSolution())

        solver = Day10Solver(exampleInput2.byteInputStream())
        Assert.assertEquals(220, solver.getPartOneSolution())
        Assert.assertEquals(19208, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 10 ==========")
        val solver = Day10Solver(
                {}.javaClass.getResourceAsStream("day10Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}