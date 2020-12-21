package adventofcode

import org.junit.Assert
import org.junit.Test

class Day11SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "L.LL.LL.LL\n" +
                "LLLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLLL\n" +
                "L.LLLLLL.L\n" +
                "L.LLLLL.LL"

        var solver = Day11Solver(exampleInput.byteInputStream())
        Assert.assertEquals(37, solver.getPartOneSolution())
        Assert.assertEquals(26, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 11 ==========")
        val solver = Day11Solver(
                {}.javaClass.getResourceAsStream("day11Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}