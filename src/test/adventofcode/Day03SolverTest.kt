package adventofcode

import org.junit.Assert
import org.junit.Test

class Day03SolverTest {

    @Test
    fun validateExample() {
        val exampleInput = "..##.......\n" +
                "#...#...#..\n" +
                ".#....#..#.\n" +
                "..#.#...#.#\n" +
                ".#...##..#.\n" +
                "..#.##.....\n" +
                ".#.#.#....#\n" +
                ".#........#\n" +
                "#.##...#...\n" +
                "#...##....#\n" +
                ".#..#...#.#\n" +
                ""

        val solver = Day03Solver(exampleInput.byteInputStream())
        Assert.assertEquals(7, solver.getFirstSolution())
        Assert.assertEquals(336, solver.getSecondSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 03 ==========")
        val solver = Day03Solver(
                {}.javaClass.getResourceAsStream("day03Input.txt")
        )
        println("Solution1: " + solver.getFirstSolution())
        println("Solution2: " + solver.getSecondSolution())
    }

}