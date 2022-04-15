package genetic

import domain.Chromosome
import java.security.SecureRandom
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

fun Chromosome.mutate(random: SecureRandom): Chromosome {
    val pointA = random.nextInt(genes.size)
    var pointB = random.nextInt(genes.size)
    while(pointA == pointB) {
        pointB = random.nextInt(genes.size)
    }
    val firstPoint = min(pointA, pointB)
    val secondPoint = max(pointA, pointB)
    return Chromosome(genes.subList(0, firstPoint) + genes[secondPoint] + genes.subList(firstPoint + 1, secondPoint)
            + genes[firstPoint] + genes.subList(secondPoint + 1, genes.size))
}
