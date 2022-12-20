import java.util.*
import kotlin.math.abs

fun main() {
    val inputName = "Day20_test"


    fun part1(): Long {
        val list = readInput(inputName).mapIndexed { i, s -> Pair(i, s.toLong()) }
        val indexes = ArrayList(list.indices.toList())

        for (i in list.indices) {
            var j = indexes[i]
            var steps = abs(list[j].second) % (list.size - 1)
            val step = if (list[j].second > 0) 1 else -1
            while (steps > 0) {
                steps--
                val next = (j + step + list.size) % list.size
                Collections.swap(list, j, next)
                indexes[list[j].first] = j
                indexes[list[next].first] = next
                j = next
            }
        }
        var ans = 0L
        for (i in list.indices) {
            if (list[i].second == 0L) {
                ans += list[(i + 1000) % list.size].second +
                        list[(i + 2000) % list.size].second +
                        list[(i + 3000) % list.size].second
            }
        }
        return ans
    }

    fun part2(): Long {
        val list = readInput(inputName).mapIndexed { i, s -> Pair(i, s.toLong() * 811589153) }
        val indexes = ArrayList(list.indices.toList())
        repeat(10) {
            for (i in list.indices) {
                var j = indexes[i]
                var steps = abs(list[j].second) % (list.size - 1)
                val step = if (list[j].second > 0) 1 else -1
                while (steps > 0) {
                    steps--
                    val next = (j + step + list.size) % list.size
                    Collections.swap(list, j, next)
                    indexes[list[j].first] = j
                    indexes[list[next].first] = next
                    j = next
                }
            }
        }
        var ans = 0L
        for (i in list.indices) {
            if (list[i].second == 0L) {
                ans += list[(i + 1000) % list.size].second +
                        list[(i + 2000) % list.size].second +
                        list[(i + 3000) % list.size].second
            }
        }
        return ans
    }

    val result = part2()
    println(result)
}