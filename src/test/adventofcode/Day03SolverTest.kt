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
                ".#..#...#.#"

        val solver = Day03Solver(exampleInput.byteInputStream())
        Assert.assertEquals(7, solver.getPartOneSolution())
        Assert.assertEquals(336, solver.getPartTwoSolution())
    }

    @Test
    fun printSolutions() {
        println("========== Day 03 ==========")
        val solver = Day03Solver(
                {}.javaClass.getResourceAsStream("day03Input.txt")
        )
        println("Part one solution: " + solver.getPartOneSolution())
        println("Part two solution: " + solver.getPartTwoSolution())
    }

}