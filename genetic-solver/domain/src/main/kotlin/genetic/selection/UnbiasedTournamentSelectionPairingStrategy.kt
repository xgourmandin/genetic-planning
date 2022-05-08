package genetic.selection

import genetic.Chromosome
import genetic.SelectionPairingStrategy
import utils.permutation
import java.security.SecureRandom

class UnbiasedTournamentSelectionPairingStrategy(private val random: SecureRandom) : SelectionPairingStrategy {
  override fun identifier(): String = "UnbiasedTournament"

  override fun <T> select(population: List<Pair<Double, Chromosome<T>>>): List<Pair<Double, Chromosome<T>>> {
    val permutation = permutation(population, random)
    return population.zip(permutation).map { if (it.first.first > it.second.first) it.first else it.second }
  }
}
