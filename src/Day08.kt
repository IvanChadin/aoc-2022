import kotlin.math.max

fun main() {
    val inputName = "Day08"
    val a = readInput(inputName).map { s -> s.map { it.digitToInt() } }

    fun calc1(i: Int, j: Int): Int {
        var res = 1
        for (ii in i + 1 until a.size) {
            if (a[ii][j] >= a[i][j]) {
                res = 0
                break
            }
        }
        if (res == 1) return 1
        res = 1
        for (ii in i - 1 downTo 0) {
            if (a[ii][j] >= a[i][j]) {
                res = 0
                break
            }
        }
        if (res == 1) return 1
        res = 1
        for (jj in j + 1 until a[i].size) {
            if (a[i][jj] >= a[i][j]) {
                res = 0
                break
            }
        }
        if (res == 1) return 1
        res = 1
        for (jj in j - 1 downTo 0) {
            if (a[i][jj] >= a[i][j]) {
                res = 0
                break
            }
        }
        return res
    }

    fun part1(a: List<List<Int>>): Int {
        var ans = 0
        for (i in a.indices) {
            for (j in a[i].indices) {
                ans += calc1(i, j)
            }
        }
        return ans
    }

    fun calc2(i: Int, j: Int): Int {
        var res = 1
        var cur = 0
        for (ii in i + 1 until a.size) {
            cur++
            if (a[ii][j] >= a[i][j]) {
                break
            }
        }
        res *= cur
        cur = 0
        for (ii in i - 1 downTo 0) {
            cur++
            if (a[ii][j] >= a[i][j]) {
                break
            }
        }
        res *= cur
        cur = 0
        for (jj in j + 1 until a[i].size) {
            cur++
            if (a[i][jj] >= a[i][j]) {
                break
            }
        }
        res *= cur
        cur = 0
        for (jj in j - 1 downTo 0) {
            cur++
            if (a[i][jj] >= a[i][j]) {
                break
            }
        }
        res *= cur
        return res
    }

    fun part2(a: List<List<Int>>): Int {
        var maxx = -1
        for (i in a.indices) {
            for (j in a[i].indices) {
                maxx = max(maxx, calc2(i, j))
            }
        }
        return maxx
    }

    check(part1(a) == 21)
    check(part2(a) == 8)
}