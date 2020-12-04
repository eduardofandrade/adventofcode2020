package adventofcode

import org.junit.Assert
import org.junit.Test

class Day02SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "1-3 a: abcde\n" +
                "1-3 b: cdefg\n" +
                "2-9 c: ccccccccc\n"

        val solver = Day02Solver(exampleInput.byteInputStream())
        Assert.assertEquals(2, solver.getFirstSolution())
        Assert.assertEquals(1, solver.getSecondSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 02 ==========")
        val solver = Day02Solver(
                {}.javaClass.getResourceAsStream("day02Input.txt")
        )
        println("Solution1: " + solver.getFirstSolution())
        println("Solution2: " + solver.getSecondSolution())
    }

}