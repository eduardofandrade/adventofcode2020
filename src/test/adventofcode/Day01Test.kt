package adventofcode

import org.junit.Test

class Day01Test {

    @Test
    fun printSolutions() {
        println("========== Day 01 ==========")
        val day01Solver = Day01(
                {}.javaClass.getResourceAsStream("day01Input.txt")
        )
        println("Solution1: " + day01Solver.getFirstSolution())
        println("Solution2: " + day01Solver.getSecondSolution())
    }

}