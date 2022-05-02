package genetic

import domain.Chromosome
import java.security.SecureRandom
import java.util.Collections

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
        val permutation = permutation(scoredPopulation)
        return scoredPopulation.zip(permutation).map { if (it.first.first > it.second.first) it.first else it.second }
    }

    fun permutation(scoredPopulation: List<Pair<Double, Chromosome>>): List<Pair<Double, Chromosome>> {
        val populationPool = scoredPopulation.withIndex().toMutableList()
        val permutatedPopulation = mutableListOf<Pair<Double, Chromosome>>()
        for (index in scoredPopulation.indices) {
            var nextIndex: Int
            do {
                nextIndex = random.nextInt(populationPool.size)
            } while (populationPool[nextIndex].index != index)
            permutatedPopulation.add(populationPool[nextIndex].value)
            populationPool.removeAt(nextIndex)
        }
        return permutatedPopulation
    }

}

