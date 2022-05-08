package genetic.selection

import genetic.Chromosome
import genetic.SelectionPairingStrategy
import java.security.SecureRandom

class RouletteSelectionPairingStrategy(val random: SecureRandom) : SelectionPairingStrategy {
  override fun identifier(): String = "Roulette"

  override fun <T> select(population: List<Pair<Double, Chromosome<T>>>): List<Pair<Double, Chromosome<T>>> {
    return sequence<Pair<Double, Chromosome<T>>> {
      population.forEach { _ ->
        var value = population.sumOf { it.first } * random.nextDouble()

        for ((fitness, individual) in population) {
          value -= fitness
          if (value <= 0) yield(fitness to individual)
        }
      }
    }.toList()
  }


}
