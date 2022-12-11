import java.util.*

fun main() {
    val inputName = "Day11_test"
    val input = readInput(inputName)

    class Monkey(val id: Int) {
        val items = LinkedList<Long>()
        var divider: Long = 0
        var trueReceiver: Int = -1
        var falseReceiver: Int = -1
        var calc: ((Long) -> Long)? = null
        var isMul = false
        var ans = 0L
    }

    var cur: Monkey? = null
    val monkeys = ArrayList<Monkey>()
    var mainDivider = 1L
    for (str in input) {
        val strs = str.split(":", ",", " ").filter { it.isNotBlank() }
        if (strs.isEmpty() && cur != null) {
            monkeys.add(cur)
            continue
        }
        when (strs[0]) {
            "Monkey" -> cur = Monkey(strs[1].toInt())
            "Starting" -> (2 until strs.size).forEach { cur?.items?.add(strs[it].toLong()) }
            "Operation" -> {
                if (strs[strs.size - 2] == "+") {
                    if (strs[strs.size - 1] == "old") {
                        cur?.calc = { x: Long -> x + x }
                    } else {
                        cur?.calc = { x: Long -> x + strs[strs.size - 1].toLong() }
                    }
                } else {
                    cur?.isMul = true
                    if (strs[strs.size - 1] == "old") {
                        cur?.calc = { x: Long -> x * x }
                    } else {
                        cur?.calc = { x: Long -> x * strs[strs.size - 1].toLong() }
                    }
                }
            }

            "Test" -> {
                cur?.divider = strs[strs.size - 1].toLong()
                mainDivider *= cur?.divider!!
            }

            "If" -> {
                if (strs[1] == "true") {
                    cur?.trueReceiver = strs[strs.size - 1].toInt()
                } else {
                    cur?.falseReceiver = strs[strs.size - 1].toInt()
                }
            }
        }
    }

    fun part1(): Long {
        repeat(20) {
            for (monkey in monkeys) {
                for (i in monkey.items.indices) {
                    monkey.items[i] = monkey.calc!!.invoke(monkey.items[i])
                    monkey.items[i] /= 3L
                    if (monkey.items[i] % monkey.divider == 0L) {
                        monkeys[monkey.trueReceiver].items.add(monkey.items[i])
                    } else {
                        monkeys[monkey.falseReceiver].items.add(monkey.items[i])
                    }
                }
                monkey.ans += monkey.items.size
                monkey.items.clear()
            }
        }
        var maxa = -1L
        var maxb = -1L
        for (monkey in monkeys) {
            if (monkey.ans > maxa) {
                maxb = maxa
                maxa = monkey.ans
            } else if (monkey.ans > maxb) {
                maxb = monkey.ans
            }
        }
        return maxa * maxb
    }

    fun part2(): Long {
        repeat(10000) {
            for (monkey in monkeys) {
                for (i in monkey.items.indices) {
                    monkey.items[i] = monkey.calc!!.invoke(monkey.items[i])
                    if (monkey.items[i] % monkey.divider == 0L) {
                        monkeys[monkey.trueReceiver].items.add(monkey.items[i] % mainDivider)
                    } else {
                        monkeys[monkey.falseReceiver].items.add(monkey.items[i] % mainDivider)
                    }
                }
                monkey.ans += monkey.items.size
                monkey.items.clear()
            }
        }
        var maxa = -1L
        var maxb = -1L
        for (monkey in monkeys) {
            if (monkey.ans > maxa) {
                maxb = maxa
                maxa = monkey.ans
            } else if (monkey.ans > maxb) {
                maxb = monkey.ans
            }
        }
        return maxa * maxb
    }

    var result = part2()
    println(result)
}