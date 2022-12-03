package day02

import readInput

enum class Shape(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    DEFAULT(0);

    fun getOutcome(opponentShape: Shape) =
        when {
            opponentShape == ROCK && this == PAPER -> RoundOutcome.WIN
            opponentShape == PAPER && this == SCISSORS -> RoundOutcome.WIN
            opponentShape == SCISSORS && this == ROCK -> RoundOutcome.WIN
            opponentShape == this -> RoundOutcome.DRAW
            else -> RoundOutcome.LOST
        }

    companion object {
        fun create(letter: Char) =
            when(letter) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> DEFAULT
            }

        fun forOutcome(opponentShape: Shape, outcome: RoundOutcome): Shape =
            when {
                outcome == RoundOutcome.WIN && opponentShape == ROCK -> PAPER
                outcome == RoundOutcome.WIN && opponentShape == PAPER -> SCISSORS
                outcome == RoundOutcome.WIN && opponentShape == SCISSORS -> ROCK

                outcome == RoundOutcome.LOST && opponentShape == ROCK -> SCISSORS
                outcome == RoundOutcome.LOST && opponentShape == PAPER -> ROCK
                outcome == RoundOutcome.LOST && opponentShape == SCISSORS -> PAPER

                outcome == RoundOutcome.DRAW -> opponentShape
                else -> opponentShape
            }
    }
}

enum class RoundOutcome(val score: Int) {
    LOST(0),
    DRAW(3),
    WIN(6)
}

class Game(
    val opponentShape: Shape,
    val playerShape: Shape
) {
    fun getPlayerScore() = playerShape.getOutcome(opponentShape).score + playerShape.score

    override fun toString(): String =  "$opponentShape x $playerShape"
}

fun main() {

    fun parseGuide(guide: List<String>): List<Game> =
        guide.map {
            Game(
                opponentShape = Shape.create(it[0]),
                playerShape = Shape.create(it[2])
            )
        }

    fun parseGuideForOutcome(guide: List<String>): List<Game> =
        guide.map {
            val opponentShape = Shape.create(it[0])
            Game(
                opponentShape = opponentShape,
                playerShape = Shape.forOutcome(
                    opponentShape = opponentShape,
                    outcome = when (it[2]) {
                        'Y' -> RoundOutcome.DRAW
                        'Z' -> RoundOutcome.WIN
                        else -> RoundOutcome.LOST
                    })
            )
        }

    fun part1(games: List<Game>): Int =
        games.sumOf { it.getPlayerScore() }

    fun part2(games: List<Game>): Int =
        games.sumOf { it.getPlayerScore() }

    val input = readInput("day02/Day02")
    println(part1(parseGuide(input)))
    println(part2(parseGuideForOutcome(input)))
}
