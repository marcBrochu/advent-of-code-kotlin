package day08

import readInput
import java.lang.Integer.max

fun main() {

    fun part1(input: List<String>): Int {
        val height = input.size
        val width = input.first().length
        val grid = input.map { it.map { it.digitToInt() } }

        var numVisible = 0

        for (h in 0 until height) {
            for (w in 0 until width) {
                val curr = grid[h][w]
                if (w == 0 || h == 0 || w == width - 1 || h == height - 1) {
                    numVisible++
                } else {
                    val columnValues = grid.map { it[w] }
                    val leftVisible = grid[h].take(w).all { it < curr }
                    val rightVisible = grid[h].drop(w + 1).all { it < curr }
                    val topVisible = columnValues.take(h).all { it < curr }
                    val bottomVisible = columnValues.drop(h + 1).all { it < curr }
                    if (leftVisible || rightVisible || topVisible || bottomVisible) {
                        numVisible++
                    }
                }
            }
        }
        return numVisible
    }

    fun part2(input: List<String>): Int {
        val height = input.size
        val width = input.first().length
        val grid = input.map { it.map { it.digitToInt() } }

        var maxScenicScore = 0

        for (h in 0 until height) {
            for (w in 0 until width) {
                if (w == 0 || h == 0 || w == width - 1 || h == height - 1) {
                    continue
                }
                val curr = grid[h][w]
                val columnValues = grid.map { it[w] }
                var leftScenicScore = grid[h].take(w).reversed().takeWhile { it < curr }.count()
                var rightScenicScore = grid[h].drop(w + 1).takeWhile { it < curr }.count()
                var topScenicScore = columnValues.take(h).reversed().takeWhile { it < curr }.count()
                var bottomScenicScore = columnValues.drop(h + 1).takeWhile { it < curr }.count()


                if (leftScenicScore == 0 || leftScenicScore < grid[h].take(w).size) {
                    leftScenicScore++
                }
                if (rightScenicScore == 0 || rightScenicScore < grid[h].drop(w + 1).size) {
                    rightScenicScore++
                }
                if (topScenicScore == 0 || topScenicScore < columnValues.take(h).size) {
                    topScenicScore++
                }
                if (bottomScenicScore == 0 || bottomScenicScore < columnValues.drop(h + 1).size) {
                    bottomScenicScore++
                }

                val currentScenicScore = leftScenicScore * rightScenicScore * topScenicScore * bottomScenicScore
                maxScenicScore = max(currentScenicScore, maxScenicScore)
            }
        }
        return maxScenicScore
    }

    val input = readInput("day08/Day08")
    println(part1(input))
    println(part2(input))
}
