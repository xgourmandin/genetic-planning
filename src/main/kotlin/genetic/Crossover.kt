package genetic

import domain.Chromosome
import kotlin.random.Random

fun Chromosome.crossover(other: Chromosome, random: Random): Pair<Chromosome, Chromosome> {
    val crossoverPoint = (0..genes.size).random(random)
    return Pair(Chromosome(genes.subList(0, crossoverPoint).plus(other.genes.subList(crossoverPoint, other.genes.size))),
    Chromosome(genes.subList(crossoverPoint, genes.size).plus(other.genes.subList(0, crossoverPoint))))

}
