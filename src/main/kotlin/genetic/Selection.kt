package genetic

import domain.Chromosome
import utils.permutation
import java.security.SecureRandom

class Selection(private val random: SecureRandom) {

    fun rouletteSelection(scoredPopulation: Collection<Pair<Double, Chromosome>>): Chromosome {
        var value = scoredPopulation.sumOf { it.first } * random.nextDouble()

        for ((fitness, individual) in scoredPopulation) {
            value -= fitness
            if (value <= 0) return individual
        }

        return scoredPopulation.last().second
    }

    fun unbiasedTournamentSelection(scoredPopulation: List<Pair<Double, Chromosome>>): List<Pair<Double, Chromosome>> {
        val permutation = permutation(scoredPopulation, random)
        return scoredPopulation.zip(permutation).map { if (it.first.first > it.second.first) it.first else it.second }
    }

}

