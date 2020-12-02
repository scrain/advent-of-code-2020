package net.scrain.aoc

class Day02 {
    static def getPart1Result(String filename) {
        File file = new File(filename)
        def validCount = 0
        file.readLines().each { line ->
            if (new Password(line).isValidPart1() ) {
                validCount++
            }
        }
        validCount
    }

    static def getPart2Result(String filename) {
        File file = new File(filename)
        def validCount = 0
        file.readLines().each { line ->
            if (new Password(line).isValidPart2() ) {
                validCount++
            }
        }
        validCount
    }

    static void main (String[] args) {
        println getPart1Result('day02.data.txt')
        println getPart2Result('day02.data.txt')
    }
}

class Password {
    def x, y, character, password

    Password(String line) {
        String[] parts = line.tokenize(' :-')

        x = parts[0].toInteger()
        y = parts[1].toInteger()
        character = parts[2]
        password = parts[3]
    }

    boolean isValidPart1() {
        password.count(character) >= x && password.count(character) <= y
    }

    boolean isValidPart2() {
        password.getAt(x-1) == character ^ password.getAt(y-1) == character
    }
}