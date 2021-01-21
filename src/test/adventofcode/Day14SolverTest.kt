package adventofcode

import org.junit.Assert
import org.junit.Test

class Day14SolverTest {

    @Test
    fun validatePartOneExample() {
        val exampleInput = "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0"

        Assert.assertEquals(165, Day14Solver(exampleInput.byteInputStream()).getPartOneSolution())
    }

    @Test
    fun validatePartTwoExample() {
        val exampleInput = "mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1"

        Assert.assertEquals(208, Day14Solver(exampleInput.byteInputStream()).getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 14 ==========")
        val solver = Day14Solver(
                {}.javaClass.getResourceAsStream("day14Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}