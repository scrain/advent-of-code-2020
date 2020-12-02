package net.scrain.aoc

class Day01 {
    static def getProductOfExpensesWithSumTotal(List<String> expenses, depth, targetTotal, currentProduct = 1, currentExpenseTotal = 0) {
        if (depth == 0) {
            return currentExpenseTotal == targetTotal ? currentProduct : null
        }
        def result
        expenses.each { expense ->
            def newExpenseTotal = currentExpenseTotal + expense.toInteger()
            def newProduct = currentProduct * expense.toInteger()
            result = result ?: getProductOfExpensesWithSumTotal(expenses, depth - 1, targetTotal, newProduct, newExpenseTotal)
        }
        result
    }

    static def getPart1Result(String filename) {
        getProductOfExpensesWithSumTotal(new File(filename).readLines(), 2, 2020, 1, 0)
    }

    static def getPart2Result(String filename) {
        getProductOfExpensesWithSumTotal(new File(filename).readLines(), 3, 2020, 1, 0)
    }

    static void main(String[] args) {
        println getPart1Result('day01.data.txt')
        println getPart2Result('day01.data.txt')
    }
}
