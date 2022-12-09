import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val inputName = "Day09_test"
    val commands = readInput(inputName).map { s -> s.split(" ") }

    data class Pt(var x: Int, var y: Int)

    fun part1(commands: List<List<String>>): Int {
        val visited = HashSet<Pt>()
        val head = Pt(0, 0)
        val tail = Pt(0, 0)
        for (cmd in commands) {
            repeat(cmd[1].toInt()) {
                when (cmd[0]) {
                    "R" -> head.x++
                    "U" -> head.y++
                    "L" -> head.x--
                    "D" -> head.y--
                }
                if (abs(head.x - tail.x) + abs(head.y - tail.y) == 3) {
                    tail.x += sign((head.x - tail.x).toDouble()).toInt()
                    tail.y += sign((head.y - tail.y).toDouble()).toInt()
                } else {
                    if (abs(head.x - tail.x) == 2) {
                        tail.x += sign((head.x - tail.x).toDouble()).toInt()
                    }
                    if (abs(head.y - tail.y) == 2) {
                        tail.y += sign((head.y - tail.y).toDouble()).toInt()
                    }
                }
                visited.add(tail.copy())
            }
        }
        return visited.size
    }

    fun part2(commands: List<List<String>>): Int {
        val visited = HashSet<Pt>()
        val knots = HashMap<Int, Pt>()
        for (i in 0..9) {
            knots[i] = Pt(0, 0)
        }
        for (cmd in commands) {
            repeat(cmd[1].toInt()) {
                when (cmd[0]) {
                    "R" -> knots[0]!!.x++
                    "U" -> knots[0]!!.y++
                    "L" -> knots[0]!!.x--
                    "D" -> knots[0]!!.y--
                }
                for (i in 1..9) {
                    val head = knots[i - 1]!!
                    val tail = knots[i]!!
                    if (abs(head.x - tail.x) + abs(head.y - tail.y) == 3) {
                        tail.x += sign((head.x - tail.x).toDouble()).toInt()
                        tail.y += sign((head.y - tail.y).toDouble()).toInt()
                    } else {
                        if (abs(head.x - tail.x) == 2) {
                            tail.x += sign((head.x - tail.x).toDouble()).toInt()
                        }
                        if (abs(head.y - tail.y) == 2) {
                            tail.y += sign((head.y - tail.y).toDouble()).toInt()
                        }
                    }
                }
                visited.add(knots[9]!!.copy())
            }
        }
        return visited.size
    }

    val result = part2(commands)
    println(result)
    check(result == 36)
    //check(part2(a) == 8)
}