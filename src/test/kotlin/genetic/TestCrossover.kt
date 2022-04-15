package genetic

import domain.Chromosome
import domain.Gene
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCrossover {

    @Test
    fun testCrossover() {
        val genes1 = listOf(Gene(listOf("N01", "N02")), Gene(listOf("N03", "N04")))
        val c1 = Chromosome(genes1)
        val genes2 = listOf(Gene(listOf("N05", "N06")), Gene(listOf("N07", "N08")))
        val c2 = Chromosome(genes2)
        val child = c1.crossover(c2, FixedRandom())
        assertEquals("N01,N02,N07,N08", child.genes.flatMap { it.nurses }.joinToString(","))
    }

    class FixedRandom: SecureRandom() {
        override fun nextInt(until: Int): Int = 1
    }
}
