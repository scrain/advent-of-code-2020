package net.scrain.aoc

class Day04 {
    static List getRecords(String filename) {
        def data = ""
        def records = []

        new File(filename).readLines().eachWithIndex { String entry, int i ->
            if (entry != "") {
                data += " ${entry}"
            } else {
                records << data
                data = ""
            }
        }
        records << data
        records
    }

    static getPart1Result(filename) {
        def valid = 0
        getRecords(filename).each { record ->
            if (new Passport(record).valid) {
                valid++
            }
            new Passport(record).valid
        }
        valid
    }

    static getPart2Result(filename) {
        def valid = 0
        getRecords(filename).each { record ->
            if (new Passport(record).valid2) {
                valid++
            }
        }
        valid
    }

    static void main(String[] args) {
        println getPart1Result('day04.data.txt')
        println getPart2Result('day04.data.txt')
    }
}

class Passport {
    Map fields = [:]

    Passport(data) {
        data.trim().tokenize(' ').each { value ->
            fields[value.tokenize(':').get(0)] = value.tokenize(':').get(1)
        }
    }

    boolean isValid() {
        fields.keySet().containsAll(['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid'])
    }

    boolean isValid2() {
        if (!isValid()) {
            return false
        }
        if (outOfRange(fields.byr, 1920, 2002) ||
            outOfRange(fields.iyr, 2010, 2020) ||
            outOfRange(fields.eyr, 2020, 2030)) {
            return false
        }
        if (!(fields.hgt.endsWith('cm') || fields.hgt.endsWith('in'))) {
            return false
        }
        int height = fields.hgt.substring(0, fields.hgt.size() - 2).toInteger()
        if (fields.hgt.endsWith('cm') && (height < 150 || height > 193)) {
            return false
        }
        if (fields.hgt.endsWith('in') && (height < 59 || height > 76)) {
            return false
        }
        if (!isColor(fields.hcl)) {
            return false
        }
        if (!['amb', 'blu', 'brn', 'gry', 'grn', 'hzl', 'oth'].contains(fields.ecl)) {
            return false
        }
        if (fields.pid.length() != 9 || !fields.pid.isNumber()) {
            return false
        }
        true
    }

    static boolean outOfRange(s, min, max) {
        s.toInteger() < min || s.toInteger() > max
    }

    static List COLORCHARS = Arrays.asList("0123456789abcdef".toCharArray())

    static boolean isColor(color) {
        if (color.length() != 7 || !color.startsWith("#")) {
            return false
        }
        if (!COLORCHARS.containsAll(color.substring(1).toCharArray())) {
            return false
        }
        true
    }
}