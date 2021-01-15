package adventofcode

import java.io.InputStream
import java.util.*


class Day13Solver(stream: InputStream) : Solver {

    private var timestamp: Int
    private var buses: ArrayList<Pair<Long, Long>> = arrayListOf()

    init {
        val lines = stream.bufferedReader().readLines()
        timestamp = lines[0].toInt()
        buses = lines[1].split(",").withIndex()
                .filter { busID -> busID.value != "x" }
                .map { busID -> Pair(busID.value.toLong(), busID.index.toLong()) }
                .toCollection(buses)
    }

    override fun getPartOneSolution(): Long {
        for (t in timestamp..Integer.MAX_VALUE) {
            for (b in buses) {
                if (t % b.first == 0L) {
                    return ((t - timestamp) * b.first)
                }
            }
        }
        return 0
    }

    override fun getPartTwoSolution(): Long {
        // cheated! some clever solution... from: https://todd.ginsberg.com/post/advent-of-code/2020/day13/#d13p2
        var stepSize = buses.first().first
        var time = 0L
        buses.drop(1).forEach { (busId, busOffset) ->
            while ((time + busOffset) % busId != 0L) {
                time += stepSize
            }
            stepSize *= busId
        }
        return time
    }

}