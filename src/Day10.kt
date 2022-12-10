fun main() {
    val inputName = "Day10_test"
    val commands = readInput(inputName).map { s -> s.split(" ") }

    fun part1(commands: List<List<String>>): Int {
        var ans = 0
        var cycle = 1
        var x = 1
        for (cmd in commands) {
            var cnt = 2
            while (cnt > 0) {
                if (cycle in listOf(20, 60, 100, 140, 180, 220)) {
                    ans += x * cycle
                }
                if (cmd[0] == "noop") {
                    cycle++
                    cnt = 0
                }
                if (cmd[0] == "addx") {
                    cycle++
                    if (cnt == 1) {
                        x += cmd[1].toInt()
                    }
                    cnt--
                }
            }
        }
        return ans
    }

    fun part2(commands: List<List<String>>): Array<CharArray> {
        val ans = Array(6) { CharArray(40) }
        var cycle = 1
        var i = 0
        var j = 0
        var x = 1
        for (cmd in commands) {
            var cnt = 2
            while (cnt > 0) {
                if (j in x - 1..x + 1) {
                    ans[i][j] = '#'
                } else {
                    ans[i][j] = '.'
                }
                if (cycle % 40 == 0) {
                    i++
                    j = 0
                } else {
                    j++
                }
                if (cmd[0] == "noop") {
                    cycle++
                    cnt = 0
                }
                if (cmd[0] == "addx") {
                    cycle++
                    if (cnt == 1) {
                        x += cmd[1].toInt()
                    }
                    cnt--
                }
            }
        }
        return ans
    }

    val result = part2(commands)
    for (row in result) {
        for (c in row) {
            print(c)
        }
        println()
    }
}