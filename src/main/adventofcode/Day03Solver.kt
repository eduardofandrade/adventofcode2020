package adventofcode

import java.io.InputStream
import java.util.*

class Day03Solver(stream: InputStream) : Solver {

    private var numRows: Int = 0
    private var numCols: Int = 0
    private val trees: ArrayList<String> = arrayListOf()

    init {
        processInput(stream)
    }

    private fun processInput(stream: InputStream) {
        val lines = stream.bufferedReader().readLines()

        var numLines = 0
        for (line in lines) {
            for ((index, value) in line.withIndex()) {
                if (value == '#') {
                    trees.add(numLines.toString() + "_" + index.toString())
                }
            }
            if (numCols == 0) numCols = line.length
            numLines++
        }
        numRows = numLines
    }

    private fun travel(s: State, right: Int, down: Int) {
        if (s.x == numRows - 1) return
        s.y = (s.y + right) % numCols
        s.x = s.x + down
        if (trees.contains((s.x).toString() + "_" + s.y)) s.numTreesFound++
        return travel(s, right, down)
    }

    private fun getNumTreesForSlope(right: Int, down: Int): Long {
        var s = State()
        travel(s, right, down)
        return s.numTreesFound
    }

    override fun getFirstSolution(): Long {
        return getNumTreesForSlope(3, 1)
    }

    override fun getSecondSolution(): Long {
        return getNumTreesForSlope(1, 1) *
                getNumTreesForSlope(3, 1) *
                getNumTreesForSlope(5, 1) *
                getNumTreesForSlope(7, 1) *
                getNumTreesForSlope(1, 2)
    }

    private class State {
        var x: Int = 0
        var y: Int = 0
        var numTreesFound: Long = 0
    }

}