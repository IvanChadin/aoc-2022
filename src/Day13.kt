fun main() {
    val inputName = "Day13_test"


    fun parseLine(s: String): ArrayList<Any> {
        val list = ArrayList<Any>()
        val stack = ArrayDeque<ArrayList<Any>>()
        stack.addLast(list)
        val numBuilder = StringBuilder()
        for (c in s) {
            when (c) {
                '[' -> {
                    val temp = ArrayList<Any>()
                    stack.last().add(temp)
                    stack.addLast(temp)
                }

                ']' -> {
                    if (numBuilder.isNotEmpty()) {
                        val num = numBuilder.toString().toInt()
                        stack.last().add(num)
                        numBuilder.clear()
                    }
                    stack.removeLast()
                }

                ',' -> {
                    if (numBuilder.isNotEmpty()) {
                        val num = numBuilder.toString().toInt()
                        stack.last().add(num)
                        numBuilder.clear()
                    }
                }

                else -> {
                    numBuilder.append(c)
                }
            }
        }
        return list
    }

    fun isCorrectOrder(l1: ArrayList<Any>, l2: ArrayList<Any>): Int {
        var i = 0
        var j = 0
        while (i in l1.indices && j in l2.indices) {
            if (l1[i].javaClass != l2[j].javaClass) {
                if (l1[i] !is ArrayList<*>) {
                    val temp = ArrayList<Any>()
                    temp.add(l1[i])
                    l1[i] = temp
                }
                if (l2[i] !is ArrayList<*>) {
                    val temp = ArrayList<Any>()
                    temp.add(l2[i])
                    l2[i] = temp
                }
            }
            if (l1[i] is ArrayList<*>) {
                val res = isCorrectOrder(l1[i] as ArrayList<Any>, l2[j] as ArrayList<Any>)
                when (res) {
                    -1 -> return -1
                    1 -> return 1
                }
            } else {
                if ((l1[i] as Int) < (l2[i] as Int)) {
                    return 1
                } else if ((l1[i] as Int) > (l2[i] as Int)) {
                    return -1
                }
            }
            i++
            j++
        }
        if (j != l2.size) {
            return 1
        }
        if (i != l1.size) {
            return -1
        }
        return 0
    }

    fun part1(): Int {
        val input = readInput(inputName).filter { it.isNotEmpty() }
        var idx = 0
        var mainIdx = 1
        var ans = 0
        while (idx in input.indices) {
            val l1 = parseLine(input[idx++])
            val l2 = parseLine(input[idx++])
            if (isCorrectOrder(l1, l2) != -1) {
                ans += mainIdx
            }
            mainIdx++
        }
        return ans
    }

    fun part2(): Int {

        val listStr = ArrayList<String>().apply {
            add("[[2]]")
            add("[[6]]")
            addAll(readInput(inputName).filter { it.isNotEmpty() })
        }
        val sortedStr = listStr.sortedWith { a, b -> isCorrectOrder(parseLine(b), parseLine(a)) }

        var ans = 1
        for (i in sortedStr.indices) {
            if (sortedStr[i] == "[[2]]" || sortedStr[i] == "[[6]]") {
                ans *= (i + 1)
            }
        }
        return ans
    }

    val result = part2()
    println(result)
}