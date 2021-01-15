package adventofcode

import java.io.InputStream
import java.util.stream.Collectors

class Day11Solver(stream: InputStream) : Solver {

    private val processedInput: List<ArrayList<Char>>
    private var numberOfRows = 0
    private var numberOfColumns = 0

    init {
        processedInput = processInput(stream)
        numberOfRows = processedInput.size
        numberOfColumns = processedInput[numberOfRows - 1].size
    }

    private fun processInput(stream: InputStream): List<ArrayList<Char>> {
        return stream.bufferedReader().readLines().stream()
                .map { line ->
                    val columns = arrayListOf<Char>()
                    line.toCharArray().map { c -> columns.add(c) }
                    columns
                }
                .collect(Collectors.toList())
    }

    override fun getPartOneSolution(): Long {
        var oldState = processedInput
        var newState: ArrayList<ArrayList<Char>> = arrayListOf()

        while (updateStateWithPartOneRules(oldState, newState)) {
            oldState = newState
            newState = arrayListOf()
        }

        return newState.map { rows -> rows.toCharArray().filter { c -> c == '#' }.size }.sum().toLong()
    }

    override fun getPartTwoSolution(): Long {
        var oldState = processedInput
        var newState: ArrayList<ArrayList<Char>> = arrayListOf()

        while (updateStateWithPartTwoRules(oldState, newState)) {
            oldState = newState
            newState = arrayListOf()
        }

        return newState.map { rows -> rows.toCharArray().filter { c -> c == '#' }.size }.sum().toLong()
    }

    private fun updateStateWithPartOneRules(oldState: List<ArrayList<Char>>, newState: ArrayList<ArrayList<Char>>): Boolean {
        for (i in oldState.indices) {
            newState.add(arrayListOf())
            for (j in oldState[i].indices) {
                if (oldState[i][j] == 'L' && getAdjacentSeats(oldState, i, j).none { s -> s == '#' }) {
                    newState[i].add('#')
                } else if (oldState[i][j] == '#' && getAdjacentSeats(oldState, i, j).filter { s -> s == '#' }.size >= 4) {
                    newState[i].add('L')
                } else {
                    newState[i].add(oldState[i][j])
                }
            }
        }
        return !stateEquals(oldState, newState)
    }

    private fun getAdjacentSeats(state: List<ArrayList<Char>>, row: Int, column: Int): List<Char> {
        val adjacentSeats = arrayListOf<Char>()
        for (i in row - 1..row + 1) {
            if (i >= 0 && i <= numberOfRows - 1) {
                for (j in column - 1..column + 1) {
                    if (j >= 0 && j <= numberOfColumns - 1) {
                        if (i == row && j == column) continue
                        adjacentSeats.add(state[i][j])
                    }
                }
            }
        }
        return adjacentSeats
    }

    private fun updateStateWithPartTwoRules(oldState: List<ArrayList<Char>>, newState: ArrayList<ArrayList<Char>>): Boolean {
        for (i in oldState.indices) {
            newState.add(arrayListOf())
            for (j in oldState[i].indices) {
                if (oldState[i][j] == 'L' && getVisibleSeats(oldState, i, j).none { s -> s == '#' }) {
                    newState[i].add('#')
                } else if (oldState[i][j] == '#' && getVisibleSeats(oldState, i, j).filter { s -> s == '#' }.size >= 5) {
                    newState[i].add('L')
                } else {
                    newState[i].add(oldState[i][j])
                }
            }
        }
        return !stateEquals(oldState, newState)
    }

    private fun getVisibleSeats(state: List<ArrayList<Char>>, row: Int, column: Int): List<Char> {
        val coords = listOf(
                Pair(-1, -1), Pair(0, -1), Pair(1, -1),
                Pair(-1, 0), Pair(1, 0), Pair(-1, 1),
                Pair(0, 1), Pair(1, 1)
        )
        return coords.map { p -> getVisibleSeat(state, row, column, p.first, p.second) }.filter { s -> s != ' ' }
    }

    private fun getVisibleSeat(state: List<ArrayList<Char>>, row: Int, column: Int, xDirection: Int, yDirection: Int): Char {
        val xCoord = column + xDirection
        val yCoord = row + yDirection
        if (xCoord >= 0 && yCoord >= 0 && xCoord <= numberOfColumns - 1 && yCoord <= numberOfRows - 1) {
            val seat = state[yCoord][xCoord]
            if (seat != '.') {
                return seat
            }
            return getVisibleSeat(state, yCoord, xCoord, xDirection, yDirection)
        }
        return ' '
    }

    private fun stateEquals(oldState: List<ArrayList<Char>>, newState: List<ArrayList<Char>>): Boolean {
        for (i in oldState.indices) {
            for (j in oldState[i].indices) {
                if (oldState[i][j] != newState[i][j]) return false
            }
        }
        return true
    }

}