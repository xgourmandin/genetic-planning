package genetic

import domain.Chromosome
import domain.Gene
import java.security.SecureRandom
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class TestMutation {

    @Test
    fun testChromosomeMutation() {
        val genes = listOf(Gene(listOf("N01", "N02")), Gene(listOf("N03", "N04")),
            Gene(listOf("N05", "N06")), Gene(listOf("N07", "N08")))
        val chromosome = Chromosome(genes)
        val mutated = chromosome.mutate(FixedRandom())
        assertEquals("N01,N02,N05,N06,N03,N04,N07,N08", mutated.genes.flatMap { it.nurses }.joinToString(","))
    }

    class FixedRandom: SecureRandom() {
        private var count = 1
        override fun nextInt(until: Int): Int = count++
    }
}
