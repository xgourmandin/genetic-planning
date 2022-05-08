package genetic.mutation

import genetic.Chromosome
import genetic.Gene
import genetic.MutationStrategy
import java.security.SecureRandom

class SingleGeneMutation<T>(private val random: SecureRandom, private val genesPool: List<Gene<T>>) :
  MutationStrategy<T> {
  override fun identifier(): String = "SingleGeneMutation"

  override fun mutate(chromosome: Chromosome<T>): Chromosome<T> {
    val mutationPoint = random.nextInt(chromosome.genes.size)
    val replacementGene = genesPool[random.nextInt(genesPool.size)]
    val newGenes = chromosome.genes.subList(0, mutationPoint) + replacementGene + chromosome.genes.subList(
      mutationPoint + 1,
      chromosome.genes.size
    )
    return Chromosome(newGenes)
  }
}
