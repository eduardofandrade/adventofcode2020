package adventofcode

import org.junit.Assert
import org.junit.Test

class Day13SolverTest {

    @Test
    fun validateExampleOne() {
        val exampleInput = "939\n7,13,x,x,59,x,31,19"

        val solver = Day13Solver(exampleInput.byteInputStream())
        Assert.assertEquals(295, solver.getPartOneSolution())
        Assert.assertEquals(1068781, solver.getPartTwoSolution())
    }

    @Test
    fun validateOtherExamples() {
        val exampleInput1 = "0\n17,x,13,19"
        val exampleInput2 = "0\n67,7,59,61"
        val exampleInput3 = "0\n67,x,7,59,61"
        val exampleInput4 = "0\n67,7,x,59,61"
        val exampleInput5 = "0\n1789,37,47,1889"

        Assert.assertEquals(3417, Day13Solver(exampleInput1.byteInputStream()).getPartTwoSolution())
        Assert.assertEquals(754018, Day13Solver(exampleInput2.byteInputStream()).getPartTwoSolution())
        Assert.assertEquals(779210, Day13Solver(exampleInput3.byteInputStream()).getPartTwoSolution())
        Assert.assertEquals(1261476, Day13Solver(exampleInput4.byteInputStream()).getPartTwoSolution())
        Assert.assertEquals(1202161486, Day13Solver(exampleInput5.byteInputStream()).getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 13 ==========")
        val solver = Day13Solver(
                {}.javaClass.getResourceAsStream("day13Input.txt")
        )
        println("Part one solution: ${solver.getPartOneSolution()}")
        println("Part two solution: ${solver.getPartTwoSolution()}")
    }

}