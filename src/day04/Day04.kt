package day04

import readInput

data class Range(val min:Int, val max: Int) {

    fun fullyContains(otherRange: Range): Boolean =
        this.min <= otherRange.min && this.max >= otherRange.max

    fun overlap(otherRange: Range): Boolean =
        (this.min >= otherRange.min && this.min <= otherRange.max)
                || (this.max >= otherRange.min && this.max <= otherRange.max)
                || fullyContains(otherRange)
}

fun main() {
    fun parseInput(input: List<String>) = input.map {
        val firstSection = it.substringBefore(",")
        val secondSection = it.substringAfter(",")
        Pair(
            Range(
                firstSection.substringBefore("-").toInt(),
                firstSection.substringAfter("-").toInt()
            ),
            Range(
                secondSection.substringBefore("-").toInt(),
                secondSection.substringAfter("-").toInt()
            )
        )
    }

    fun part1(input: List<String>): Int {
        return parseInput(input).count { pair ->
            pair.first.fullyContains(pair.second) || pair.second.fullyContains(pair.first)
        }
    }

    fun part2(input: List<String>): Int {
        return parseInput(input).count { pair ->
            pair.first.overlap(pair.second)
        }
    }

    val input = readInput("day04/Day04")
    println(part1(input))
    println(part2(input))
}
