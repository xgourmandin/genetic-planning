package genetic

import domain.Chromosome
import java.security.SecureRandom

class Selection(private val random: SecureRandom) {

    fun selection(scoredPopulation: Collection<Pair<Double, Chromosome>>): Chromosome {
        var value = scoredPopulation.sumOf { it.first } * random.nextDouble()

        for ((fitness, individual) in scoredPopulation) {
            value -= fitness
            if (value <= 0) return individual
        }

        return scoredPopulation.last().second
    }

}
