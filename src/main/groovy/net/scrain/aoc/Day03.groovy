package net.scrain.aoc

class Day03 {
    private static long traverseSlope(String filename, right, down) {
        List lines = new File(filename).readLines()
        int x = 0
        long trees = 0
        lines.eachWithIndex { String line, int idx ->
            if (idx % down == 0) {
                if (line.getAt(x) == '#') {
                    trees++
                }
                x += right
                if (x >= line.length()) {
                    x -= line.length()
                }
            }
        }
        trees
    }

    static getPart1Result(String filename) {
        traverseSlope(filename, 3, 1)
    }

    static getPart2Result(String filename) {
        traverseSlope(filename, 1, 1) *
        traverseSlope(filename, 3, 1) *
        traverseSlope(filename, 5, 1) *
        traverseSlope(filename, 7, 1) *
        traverseSlope(filename, 1, 2)
    }

    static void main(String[] args) {
        println getPart1Result('day03.data.txt')
        println getPart2Result('day03.data.txt')
    }
}
