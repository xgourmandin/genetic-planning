package genetic


import genetic.crossover.SinglePointCrossoverStrategy
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertEquals

class TestSinglePointCrossover {

  val crossover = SinglePointCrossoverStrategy<List<String>>(FixedRandom())
    @Test
    fun testCrossover() {
        val genes1 = listOf(Gene(listOf("N01", "N02")), Gene(listOf("N03", "N04")))
        val c1 = Chromosome(genes1)
        val genes2 = listOf(Gene(listOf("N05", "N06")), Gene(listOf("N07", "N08")))
        val c2 = Chromosome(genes2)
        val child =  crossover.crossover(c1, c2)
        assertEquals("N01,N02,N07,N08", child.genes.flatMap { it.value }.joinToString(","))
    }

    class FixedRandom: SecureRandom() {
        override fun nextInt(from: Int, until: Int): Int = 1
    }
}
