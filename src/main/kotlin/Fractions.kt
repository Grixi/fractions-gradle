import kotlin.math.abs

class Fraction(override val numerator: Int, override val denominator: Int) : IFraction {
    init {
        if (denominator == 0) throw ArithmeticException("0")
    }
    override operator fun plus(other: IFraction): IFraction {
        val lcm = lcm(denominator, other.denominator)
        val n1 = lcm / denominator * numerator
        val n2: Int = lcm / other.denominator * other.numerator
        return createNormalised(n1 + n2, lcm)
    }

    override operator fun minus(other: IFraction): IFraction {
        val lcm = lcm(denominator, other.denominator)
        val n1 = lcm / denominator * numerator
        val n2: Int = lcm / other.denominator * other.numerator
        return createNormalised(n1 - n2, lcm)
    }

    override operator fun times(other: IFraction): IFraction {
        val timesNumerator: Int = numerator * other.numerator
        val timesDenominator: Int = denominator * other.denominator
        return createNormalised(timesNumerator, timesDenominator)
    }

    override operator fun div(other: IFraction): IFraction {
        val timesNumerator: Int = numerator * other.denominator
        val timesDenominator: Int = denominator * other.numerator
        return createNormalised(timesNumerator, timesDenominator)
    }




    private fun lcm(n1: Int, n2: Int): Int {
        if (n1 == 0 || n2 == 0) {
            return 0
        }
        val absNumber1 = Math.abs(n1)
        val absNumber2 = Math.abs(n2)
        val absHigherNumber = Math.max(absNumber1, absNumber2)
        val absLowerNumber = Math.min(absNumber1, absNumber2)
        var lcm = absHigherNumber
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber
        }
        return lcm
    }

    companion object {
        private fun createNormalised(numerator: Int, denominator: Int): Fraction {
            var cacheNumerator = numerator
            var cacheDenominator = denominator
            var lcd = getLCD(cacheNumerator, cacheDenominator)

            while (lcd > 1) {
                cacheNumerator /= lcd
                cacheDenominator /= lcd
                lcd = getLCD(cacheNumerator, cacheDenominator)
            }

            return Fraction(cacheNumerator, cacheDenominator)
        }

        private fun getLCD(n1: Int, n2: Int): Int {
            return if (n2 == 0) {
                n1
            } else getLCD(n2, n1 % n2)
        }
    }
}