package usecases

import SolvingContext
import genetic.Chromosome
import genetic.FitnessStrategy
import genetic.Population
import usecase.LaunchGeneticSolvingPort
import utils.permutation
import java.security.SecureRandom
import java.util.stream.Collectors

class LaunchGeneticSolvingUseCase: LaunchGeneticSolvingPort {

  override fun <T> execute(context: SolvingContext<T>): Chromosome<T> {
    val initializer = context.initializationStrategy
    val config = context.configuration
    val fitnessFunction = context.fitnessStrategy
    val selector = context.selectionStrategy
    val crossover = context.crossoverStrategy
    val mutator = context.mutationStrategy
    val random = SecureRandom()
    // Population initialization
    var scoredPopulation = initializer.initialize()
      .map { Pair(fitnessFunction.fitness(it), it) }
      .sortedByDescending { it.first }

    for (i in 0..config.generationLimit()) {
      val bestMember = scoredPopulation.bestChromosome()
      if (bestMember?.first == 1.0) break
      // Selection
      val selectedPopulation = selector.select(scoredPopulation)

      // Crossover (with probability of 100%)
      val afterCrossoverAndMutationPopulation =
        selectedPopulation.zip(permutation(selectedPopulation, random)).parallelStream()
          .map { it.first.second to it.second.second }
          .map { crossover.crossover(it.first, it.second) }
          // Mutation
          .map {
            if (random.nextDouble() <= config.mutationRate()) mutator.mutate(it) else it
          }
          .collect(Collectors.toList())

      //elitism
      if (config.elitism()) {
        afterCrossoverAndMutationPopulation.worstChromosome(fitnessFunction)?.let { worst ->
          bestMember?.let { best ->
            val indexToReplace =
              afterCrossoverAndMutationPopulation.indexOf(worst)
            afterCrossoverAndMutationPopulation[indexToReplace] = best.second
          }
        }

      }

      scoredPopulation = afterCrossoverAndMutationPopulation.map { Pair(fitnessFunction.fitness(it), it) }
    }

    return scoredPopulation.bestChromosome()?.second!!
  }

  private fun <T> List<Pair<Double, Chromosome<T>>>.bestChromosome(): Pair<Double, Chromosome<T>>? =
    maxByOrNull { it.first }

  private fun <T> Population<T>.worstChromosome(fitnessFunction: FitnessStrategy<T>): Chromosome<T>? =
    this.minByOrNull { fitnessFunction.fitness(it) }

}
