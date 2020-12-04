package adventofcode

import java.io.InputStream
import java.util.*

class Day04Solver(stream: InputStream) : Solver {

    private val passports: ArrayList<Passport> = arrayListOf()

    init {
        processInput(stream)
    }

    private fun processInput(stream: InputStream) {
        var p = Passport()
        passports.add(p)
        stream.bufferedReader().readLines().stream()
                .forEach { line ->
                    if (line.isBlank()) {
                        p = Passport()
                        passports.add(p)
                    } else {
                        val data = line.split(" ")
                        data.forEach { d ->
                            val value = d.split(":")
                            p.data[value[0]] = value[1]
                        }
                    }
                }
    }


    override fun getFirstSolution(): Long {
        return passports.stream().filter { p -> p.isLooselyValid() }.count()
    }

    override fun getSecondSolution(): Long {
        return passports.stream().filter { p -> p.isStrictlyValid() }.count()
    }

    private class Passport {
        private val mandatoryFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        private val optionalFields = listOf("cid")

        var data: HashMap<String, String> = hashMapOf()

        fun isLooselyValid(): Boolean {
            return data.keys.containsAll(mandatoryFields)
        }

        fun isStrictlyValid(): Boolean {
            if (isLooselyValid()) {
                return hasValidBirthYear() &&
                        hasValidIssueYear() &&
                        hasValidExpirationYear() &&
                        hasValidHeight() &&
                        hasValidHairColor() &&
                        hasValidEyeColor() &&
                        hasValidPassportId()
            }
            return false
        }

        private fun hasValidBirthYear(): Boolean {
            return is4DigitNumberWithinRange(data["byr"], 1920, 2002)
        }

        private fun hasValidIssueYear(): Boolean {
            return is4DigitNumberWithinRange(data["iyr"], 2010, 2020)
        }

        private fun hasValidExpirationYear(): Boolean {
            return is4DigitNumberWithinRange(data["eyr"], 2020, 2030)
        }

        private fun hasValidHeight(): Boolean {
            val hgt: String = data["hgt"]!!
            if (hgt.endsWith("cm")) {
                return isNumberWithinRange(hgt.replace("cm", ""), 150, 193)
            } else if (hgt.endsWith("in")) {
                return isNumberWithinRange(hgt.replace("in", ""), 59, 76)
            }
            return false
        }

        private fun hasValidHairColor(): Boolean {
            return data["hcl"]!!.matches("#[0-9a-f]{6}".toRegex())
        }

        private fun hasValidEyeColor(): Boolean {
            return arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(data["ecl"])
        }

        private fun hasValidPassportId(): Boolean {
            return data["pid"]!!.matches("[0-9]{9}".toRegex())
        }

        private fun is4DigitNumberWithinRange(eyr: String?, lower: Int, upper: Int): Boolean {
            return eyr!!.matches("[0-9]{4}".toRegex()) && isNumberWithinRange(eyr, lower, upper)
        }

        private fun isNumberWithinRange(eyr: String?, lower: Int, upper: Int): Boolean {
            return eyr!!.toInt() in lower..upper
        }
    }

}