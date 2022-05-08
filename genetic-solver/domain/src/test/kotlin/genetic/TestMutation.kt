package genetic

import genetic.mutation.SingleGeneMutation
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertEquals

class TestMutation {

  val mutator = SingleGeneMutation<List<String>>(FixedRandom(), listOf(Gene(listOf("N9", "N10"))))

  @Test
  fun testChromosomeMutation() {
    val genes = listOf(
      Gene(listOf("N01", "N02")), Gene(listOf("N03", "N04")),
      Gene(listOf("N05", "N06")), Gene(listOf("N07", "N08"))
    )
    val chromosome = Chromosome(genes)
    val mutated = mutator.mutate(chromosome)
    assertEquals("N01,N02,N9,N10,N05,N06,N07,N08", mutated.genes.flatMap { it.value }.joinToString(","))
  }

  class FixedRandom : SecureRandom() {
    var count = 1
    override fun nextInt(until: Int): Int = count--
  }
}
