import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputName = "Day15_test"

    data class Pt(val x: Int, val y: Int)

    fun distance(a: Pt, b: Pt) = abs(b.x - a.x) + abs(b.y - a.y)

    fun part1(): Int {
        val ansY = 2000000
        var l = Int.MAX_VALUE
        var r = Int.MIN_VALUE
        val dist = HashMap<Pt, Int>()
        val occupied = HashSet<Pt>()
        readInput(inputName).map { s ->
            s.split(" ", "=", ":", ",")
                .filter { it.isNotBlank() && (it[0].isDigit() || it[0] == '-') }
                .map { it.toInt() }
                .toIntArray()
        }.forEach {
            val s = Pt(it[0], it[1])
            val b = Pt(it[2], it[3])
            val d = distance(s, b)
            dist[s] = d
            occupied.add(s)
            occupied.add(b)
            l = min(l, s.x - d)
            r = max(r, s.x + d)
        }

        var ans = 0
        for (x in l..r) {
            val cur = Pt(x, ansY)
            if (occupied.contains(cur)) {
                continue
            }
            for (station in dist.keys) {
                if (distance(cur, station) <= dist[station]!!) {
                    ans++
                    break
                }
            }
        }
        return ans
    }

    fun part2(): Long {
        val dist = HashMap<Pt, Int>()
        val occupied = HashSet<Pt>()
        readInput(inputName).map { s ->
            s.split(" ", "=", ":", ",")
                .filter { it.isNotBlank() && (it[0].isDigit() || it[0] == '-') }
                .map { it.toInt() }
                .toIntArray()
        }.forEach {
            val s = Pt(it[0], it[1])
            val b = Pt(it[2], it[3])
            val d = distance(s, b)
            dist[s] = d
            occupied.add(s)
            occupied.add(b)
        }
        val maxXY = 4_000_000
        for (a in dist.keys) {
            val d = dist[a]!! + 1
            for (k in 0..d) {
                for (xsign in arrayOf(-1, 1)) {
                    for (ysign in arrayOf(-1, 1)) {
                        val cur = Pt(a.x + xsign * k, a.y + ysign * (d - k))
                        if (cur.x !in 0..maxXY || cur.y !in 0..maxXY) {
                            continue
                        }
                        var isAns = true
                        for (b in dist.keys) {
                            if (a == b) {
                                continue
                            }
                            if (distance(cur, b) <= dist[b]!!) {
                                isAns = false
                                break
                            }
                        }
                        if (isAns) {
                            return cur.x.toLong() * 4_000_000L + cur.y.toLong()
                        }
                    }
                }
            }
        }
        return 0
    }

    val result = part2()
    println(result)
}