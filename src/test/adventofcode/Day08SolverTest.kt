package adventofcode

import org.junit.Assert
import org.junit.Test

class Day08SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "nop +0\n" +
                "acc +1\n" +
                "jmp +4\n" +
                "acc +3\n" +
                "jmp -3\n" +
                "acc -99\n" +
                "acc +1\n" +
                "jmp -4\n" +
                "acc +6"

        val solver = Day08Solver(exampleInput.byteInputStream())
        Assert.assertEquals(5, solver.getPartOneSolution())
        Assert.assertEquals(8, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 08 ==========")
        val solver = Day08Solver(
                {}.javaClass.getResourceAsStream("day08Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}