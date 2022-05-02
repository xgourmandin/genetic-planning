package genetic

import domain.Chromosome
import java.security.SecureRandom
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun Chromosome.singlePointCrossover(other: Chromosome, random: SecureRandom): Chromosome {
    val crossoverPoint = random.nextInt(0, genes.size)
    val first = genes.subList(0, crossoverPoint)
    val second = other.genes.subList(crossoverPoint, other.genes.size)
    return Chromosome(first + second)
}

fun Chromosome.partiallyMatchedCrossover(other: Chromosome, random: SecureRandom): Chromosome {
    val pointA = random.nextInt(2, genes.size - 1)
    var pointB = random.nextInt(2, genes.size - 1)
    while(pointA == pointB || abs(pointA - pointB) < 4 ) {
        pointB = random.nextInt(2, genes.size - 1)
    }
    val firstPoint = min(pointA, pointB)
    val secondPoint = max(pointA, pointB)

    val firstStepChildGenome = genes.subList(0, firstPoint) + other.genes.subList(firstPoint, secondPoint) +
            genes.subList(secondPoint, genes.size)

    val secondStepChildGenome = firstStepChildGenome.subList(0, firstPoint-1) + firstStepChildGenome[firstPoint] + firstStepChildGenome[firstPoint-1] +
            firstStepChildGenome.subList(firstPoint+1, secondPoint) + firstStepChildGenome[firstStepChildGenome.size-1] +
            firstStepChildGenome.subList(secondPoint+1, firstStepChildGenome.size-1) + firstStepChildGenome[secondPoint]


    return Chromosome(secondStepChildGenome)
}
