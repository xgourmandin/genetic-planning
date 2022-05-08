package genetic.crossover

import genetic.Chromosome
import genetic.CrossoverStrategy
import java.security.SecureRandom

class SinglePointCrossoverStrategy<T>(private val random: SecureRandom) : CrossoverStrategy<T> {


  override fun crossover(firstParent: Chromosome<T>, secondParent: Chromosome<T>): Chromosome<T> {
    val crossoverPoint = random.nextInt(0, firstParent.genes.size)
    val first = firstParent.genes.subList(0, crossoverPoint)
    val second = secondParent.genes.subList(crossoverPoint, secondParent.genes.size)
    return Chromosome(first + second)
  }

  override fun identifier(): String = "SinglePointCrossover"
}
