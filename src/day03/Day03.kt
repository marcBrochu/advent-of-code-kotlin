package day03

import readInput

fun calculateItemPriority(item: Char): Int {
    return if(item.isUpperCase()) {
        item - 'A' + 27
    } else if (item.isLowerCase()) {
        item - 'a' + 1
    } else {
        0
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        fun findDuplicateInRucksack(rucksack: String): Char {
            val half: Int =
                if (rucksack.length % 2 == 0) rucksack.length / 2 else rucksack.length / 2 + 1
            val first: String = rucksack.substring(0, half)
            val second: String = rucksack.substring(half)

            val duplicateSet = first.toSet().intersect(second.toSet())
            if (duplicateSet.any()) {
                return duplicateSet.elementAt(0)
            }
            return ' '
        }

        return input.sumOf { rucksacks ->
            calculateItemPriority(findDuplicateInRucksack(rucksacks))
        }
    }

    fun part2(input: List<String>): Int {
        fun findDuplicateInRucksackGroup(rucksacks: List<String>): Char {
            var current: Set<Char>? = null
            for (rucksack in rucksacks) {
                current = current?.intersect(rucksack.toSet()) ?: rucksack.toSet()
            }
            if (current != null && current.any()) {
                return current.elementAt(0)
            }
            return ' '
        }

        var groupSum = 0
        input.chunked(3).map { group ->
            groupSum += calculateItemPriority(findDuplicateInRucksackGroup(group))
        }
        return groupSum
    }

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}
