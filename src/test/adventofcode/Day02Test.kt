package adventofcode

import org.junit.Test

class Day02Test {

    @Test
    fun printSolutions() {
        println("========== Day 02 ==========")
        val day02Solver = Day02(
                {}.javaClass.getResourceAsStream("day02Input.txt")
        )
        println("Solution1: " + day02Solver.getFirstSolution())
        println("Solution2: " + day02Solver.getSecondSolution())
    }

}