import kotlin.math.max
import kotlin.math.sign

fun main() {
    val inputName = "Day14_test"

    fun part1(): Int {
        val a = Array(1000) { BooleanArray(1000) { false } }
        var maxRow = -1
        for (s in readInput(inputName)) {
            val points = s.split(" -> ").map {
                it.split(",")
                    .map { str -> str.toInt() }
            }
            var row = -1
            var col = -1
            for (pt in points) {
                if (row == -1) {
                    col = pt[0]
                    row = pt[1]
                    maxRow = max(maxRow, row)
                    a[row][col] = true
                } else {
                    do {
                        row += sign(pt[1] - row * 1.0).toInt()
                        col += sign(pt[0] - col * 1.0).toInt()
                        a[row][col] = true
                        maxRow = max(maxRow, row)
                    } while (row != pt[1] || col != pt[0])
                }
            }
        }
        var ans = 0
        val dRow = intArrayOf(1, 1, 1)
        val dCol = intArrayOf(0, -1, 1)
        while (true) {
            var row = 0
            var col = 500
            var ok = true
            while (ok) {
                ok = false
                for (i in 0..2) {
                    val nRow = row + dRow[i]
                    val nCol = col + dCol[i]
                    if (nRow in a.indices && nCol in a[nRow].indices && !a[nRow][nCol]) {
                        row = nRow
                        col = nCol
                        ok = true
                        break
                    }
                }
                if (row > maxRow) {
                    break
                }
            }
            if (row > maxRow) {
                break
            }
            a[row][col] = true
            ans++
        }

        return ans
    }

    fun part2(): Int {
        val a = Array(1000) { BooleanArray(1000) { false } }
        var maxRow = -1
        for (s in readInput(inputName)) {
            val points = s.split(" -> ").map {
                it.split(",")
                    .map { str -> str.toInt() }
            }
            var row = -1
            var col = -1
            for (pt in points) {
                if (row == -1) {
                    col = pt[0]
                    row = pt[1]
                    maxRow = max(maxRow, row)
                    a[row][col] = true
                } else {
                    do {
                        row += sign(pt[1] - row * 1.0).toInt()
                        col += sign(pt[0] - col * 1.0).toInt()
                        a[row][col] = true
                        maxRow = max(maxRow, row)
                    } while (row != pt[1] || col != pt[0])
                }
            }
        }
        for (c in a[maxRow].indices) {
            a[maxRow + 2][c] = true
        }

        var ans = 0
        val dRow = intArrayOf(1, 1, 1)
        val dCol = intArrayOf(0, -1, 1)
        while (!a[0][500]) {
            var row = 0
            var col = 500
            var ok = true
            while (ok) {
                ok = false
                for (i in 0..2) {
                    val nRow = row + dRow[i]
                    val nCol = col + dCol[i]
                    if (nRow in a.indices && nCol in a[nRow].indices && !a[nRow][nCol]) {
                        row = nRow
                        col = nCol
                        ok = true
                        break
                    }
                }
            }
            a[row][col] = true
            ans++
        }
        return ans
    }

    val result = part2()
    println(result)
}