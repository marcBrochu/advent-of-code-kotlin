package day09

import readInput
import day09.Direction.*
import kotlin.math.sign

data class Point(var x: Int, var y: Int) {

    fun move(direction: Direction, distance: Int = 1) = when (direction) {
        EAST -> x += distance
        WEST -> x -= distance
        NORTH -> y += distance
        SOUTH -> y -= distance
    }

    fun moveOneToPoint(otherPoint: Point) {
        this.x += (otherPoint.x - this.x).sign
        this.y += (otherPoint.y - this.y).sign
    }

    fun getAdjacent(): List<Point> = listOf(
        Point(x - 1, y - 1), Point(x, y - 1), Point(x + 1, y - 1),
        Point(x - 1, y), Point(x + 1, y),
        Point(x - 1, y + 1), Point(x, y + 1), Point(x + 1, y + 1)
    )

}

enum class Direction {
    NORTH, EAST, SOUTH, WEST;
}

fun main() {

    fun part1(input: List<String>): Int {
        val head = Point(0,0)
        val tail = Point(0, 0)
        val visited = mutableSetOf(Point(0,0))

        for (line in input) {
            val direction: Direction = when(line.substringBefore(" ")) {
                "U" -> NORTH
                "L" -> WEST
                "R" -> EAST
                "D" -> SOUTH
                else -> error("")
            }

            repeat(line.substringAfter(" ").toInt()) {
                head.move(direction)
                if (tail !in head.getAdjacent()) {
                    tail.moveOneToPoint(head)
                    visited.add(tail.copy())
                }
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Int {
        val knots = MutableList(10) {Point(0,0)}
        val visited = mutableSetOf(Point(0,0))

        for (line in input) {
            val direction: Direction = when(line.substringBefore(" ")) {
                "U" -> NORTH
                "L" -> WEST
                "R" -> EAST
                "D" -> SOUTH
                else -> error("")
            }

            repeat(line.substringAfter(" ").toInt()) {
                knots[0].move(direction)
                knots.drop(1).indices.forEach { index ->
                    val head = knots[index]
                    val tail = knots[index + 1]
                    if (tail !in head.getAdjacent()) {
                        tail.moveOneToPoint(head)
                        visited.add(knots.last().copy())
                    }
                }
            }
        }
        return visited.size
    }

    val input = readInput("day09/Day09")
    println(part1(input))
    println(part2(input))
}
