package genetic

import domain.Chromosome
import java.security.SecureRandom
import kotlin.random.Random

fun Chromosome.crossover(other: Chromosome, random: SecureRandom): Chromosome =
    genes.zip(other.genes).map { (g1, g2) ->
        if (random.nextBoolean()) g1 else g2
    }.let {
        Chromosome(it)
    }
