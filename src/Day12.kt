import java.util.*

fun main() {
    val inputName = "Day12_test"
    val a = readInput(inputName).map { it.toCharArray() }

    data class Pt(val x: Int, val y: Int)


    fun part1(): Int {
        val q: Queue<Pair<Pt, Int>> = LinkedList()
        var end: Pt? = null
        for (x in a.indices) {
            for (y in a[x].indices) {
                if (a[x][y] == 'S') {
                    q.offer(Pair(Pt(x, y), 0))
                    a[x][y] = 'a'
                }
                if (a[x][y] == 'E') {
                    end = Pt(x, y)
                    a[x][y] = 'z'
                }
            }
        }

        val dx = intArrayOf(0, 1, 0, -1)
        val dy = intArrayOf(1, 0, -1, 0)
        while (q.isNotEmpty()) {
            val cur = q.peek().first
            val steps = q.peek().second
            q.poll()
            if (cur == end) {
                return steps
            }
            for (i in 0..3) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (nx in a.indices && ny in a[nx].indices
                    && a[nx][ny] != '#' && a[nx][ny] - a[cur.x][cur.y] <= 1
                ) {
                    q.offer(Pair(Pt(nx, ny), steps + 1))
                }
            }
            a[cur.x][cur.y] = '#'
        }
        return 0
    }

    fun part2(): Int {
        val q: Queue<Pair<Pt, Int>> = LinkedList()
        var end: Pt? = null
        for (x in a.indices) {
            for (y in a[x].indices) {
                if (a[x][y] == 'S' || a[x][y] == 'a') {
                    q.offer(Pair(Pt(x, y), 0))
                    a[x][y] = 'a'
                }
                if (a[x][y] == 'E') {
                    end = Pt(x, y)
                    a[x][y] = 'z'
                }
            }
        }

        val dx = intArrayOf(0, 1, 0, -1)
        val dy = intArrayOf(1, 0, -1, 0)
        while (q.isNotEmpty()) {
            val cur = q.peek().first
            val steps = q.peek().second
            q.poll()
            if (cur == end) {
                return steps
            }
            for (i in 0..3) {
                val nx = cur.x + dx[i]
                val ny = cur.y + dy[i]
                if (nx in a.indices && ny in a[nx].indices
                    && a[nx][ny] != '#' && a[nx][ny] - a[cur.x][cur.y] <= 1
                ) {
                    q.offer(Pair(Pt(nx, ny), steps + 1))
                }
            }
            a[cur.x][cur.y] = '#'
        }
        return 0
    }

    var result = part2()
    println(result)
}