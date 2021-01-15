package adventofcode

import adventofcode.Day12Solver.Action.*
import java.io.InputStream
import java.util.stream.Collectors
import kotlin.math.abs

class Day12Solver(stream: InputStream) : Solver {

    private val processedInput: List<ShipMove>

    init {
        processedInput = processInput(stream)
    }

    private fun processInput(stream: InputStream): List<ShipMove> {
        return stream.bufferedReader().readLines().stream()
                .map { m ->
                    val moveLetter = m.substring(0..0)
                    val moveUnits = m.substring(1).toInt()
                    ShipMove(Action.valueOf(moveLetter), moveUnits)
                }.collect(Collectors.toList())
    }

    override fun getPartOneSolution(): Long {
        return getSolution(Ship())
    }

    override fun getPartTwoSolution(): Long {
        return getSolution(ShipWithWayPoint())
    }

    private fun getSolution(ship: Ship): Long {
        processedInput.forEach { m ->
            ship.applyMove(m)
        }
        return abs(ship.eastWest) + abs(ship.northSouth)
    }

    private open class Ship(var eastWest: Long = 0,
                            var northSouth: Long = 0) {
        private var direction: Direction = Direction.EAST

        open fun applyMove(shipMove: ShipMove) {
            when (shipMove.action) {
                F -> applyMove(ShipMove(shipMove.action.fromDirection(direction), shipMove.units))
                L -> direction = direction.rotate(-shipMove.units)
                R -> direction = direction.rotate(shipMove.units)
                N -> northSouth += shipMove.units
                S -> northSouth -= shipMove.units
                E -> eastWest += shipMove.units
                W -> eastWest -= shipMove.units
            }
        }
    }

    private class ShipWithWayPoint : Ship() {
        var waypoint: Waypoint = Waypoint()

        override fun applyMove(shipMove: ShipMove) {
            when (shipMove.action) {
                F -> {
                    super.applyMove(ShipMove(E, waypoint.eastWest.toInt() * shipMove.units))
                    super.applyMove(ShipMove(N, waypoint.northSouth.toInt() * shipMove.units))
                }
                L -> waypoint = waypoint.rotate(-shipMove.units)
                R -> waypoint = waypoint.rotate(shipMove.units)
                N -> waypoint.northSouth += shipMove.units
                S -> waypoint.northSouth -= shipMove.units
                E -> waypoint.eastWest += shipMove.units
                W -> waypoint.eastWest -= shipMove.units
            }
        }
    }

    private class Waypoint(var eastWest: Long = 10,
                           var northSouth: Long = 1) {

        fun rotate(degrees: Int): Waypoint {
            return when {
                degrees == 0 -> this
                degrees > 0 -> Waypoint(northSouth, -eastWest).rotate(degrees - 90)
                else -> Waypoint(-this.northSouth, eastWest).rotate(degrees + 90)
            }
        }
    }

    private enum class Action {
        N, S, E, W, L, R, F;

        fun fromDirection(direction: Direction): Action {
            return when (direction) {
                Direction.NORTH -> N
                Direction.SOUTH -> S
                Direction.EAST -> E
                Direction.WEST -> W
            }
        }
    }

    private class ShipMove(var action: Action, var units: Int)

    private enum class Direction {
        NORTH, SOUTH, EAST, WEST;

        fun rotate(degrees: Int): Direction {
            val pos = if (degrees > 0) {
                listOf(NORTH, EAST, SOUTH, WEST)
            } else {
                listOf(NORTH, WEST, SOUTH, EAST)
            }
            return pos[(pos.indexOf(this) + (abs(degrees) / 90)) % 4]
        }
    }

}