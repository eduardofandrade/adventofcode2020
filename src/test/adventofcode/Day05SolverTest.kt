package adventofcode

import org.junit.Assert
import org.junit.Test

class Day05SolverTest {

    @Test
    fun validateExampleForPartOne() {
        Assert.assertEquals(357, Day05Solver("FBFBBFFRLR".byteInputStream()).getPartOneSolution())
        Assert.assertEquals(567, Day05Solver("BFFFBBFRRR".byteInputStream()).getPartOneSolution())
        Assert.assertEquals(119, Day05Solver("FFFBBBFRRR".byteInputStream()).getPartOneSolution())
        Assert.assertEquals(820, Day05Solver("BBFFBBFRLL".byteInputStream()).getPartOneSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 05 ==========")
        val solver = Day05Solver(
                {}.javaClass.getResourceAsStream("day05Input.txt")
        )
        println("Part one solution: " + solver.getPartOneSolution())
        println("Part two solution: " + solver.getPartTwoSolution())
    }

}