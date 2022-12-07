package day06

import readInput

fun main() {

    fun findStartOfPacket(input: String, windowSize: Int): Int =
        input.windowed(windowSize).indexOfFirst {
            it.toSet().size == windowSize
        } + windowSize

    fun part1(input: String, windowSize: Int): Int = findStartOfPacket(input, windowSize)
    fun part2(input: String, windowSize: Int): Int = findStartOfPacket(input, windowSize)

    val input = readInput("day06/Day06")
    println(part1(input[0], 4))
    println(part2(input[0], 14))
}
