package genetic.crossover

import genetic.Chromosome
import genetic.CrossoverStrategy
import java.security.SecureRandom
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class PartiallyMatchedCrossoverStrategy<T> private constructor(private val random: SecureRandom) :
  CrossoverStrategy<T> {

  override fun identifier(): String = "PartiallyMatchedCrossover"

  override fun crossover(firstParent: Chromosome<T>, secondParent: Chromosome<T>): Chromosome<T> {
    val pointA = random.nextInt(2, firstParent.genes.size - 1)
    var pointB = random.nextInt(2, firstParent.genes.size - 1)
    while (pointA == pointB || abs(pointA - pointB) < 4) {
      pointB = random.nextInt(2, firstParent.genes.size - 1)
    }
    val firstPoint = min(pointA, pointB)
    val secondPoint = max(pointA, pointB)

    val firstStepChildGenome =
      firstParent.genes.subList(0, firstPoint) + secondParent.genes.subList(firstPoint, secondPoint) +
        firstParent.genes.subList(secondPoint, firstParent.genes.size)

    val secondStepChildGenome = firstStepChildGenome.subList(
      0,
      firstPoint - 1
    ) + firstStepChildGenome[firstPoint] + firstStepChildGenome[firstPoint - 1] +
      firstStepChildGenome.subList(
        firstPoint + 1,
        secondPoint
      ) + firstStepChildGenome[firstStepChildGenome.size - 1] +
      firstStepChildGenome.subList(
        secondPoint + 1,
        firstStepChildGenome.size - 1
      ) + firstStepChildGenome[secondPoint]


    return Chromosome(secondStepChildGenome)
  }
}
