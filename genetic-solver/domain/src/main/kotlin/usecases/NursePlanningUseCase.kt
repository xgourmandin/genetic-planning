package usecases

import SolvingContext
import genetic.Chromosome
import genetic.Configuration
import genetic.crossover.SinglePointCrossoverStrategy
import genetic.fitness.Nurse
import genetic.fitness.NursePlanningFitnessStrategy
import genetic.initialization.NurseInitializationStrategy
import genetic.mutation.SingleGeneMutation
import genetic.selection.UnbiasedTournamentSelectionPairingStrategy
import usecase.LaunchGeneticSolvingPort
import usecase.NursePlanningPort
import usecase.PlanningResult
import java.security.SecureRandom

class NursePlanningUseCase(private val geneticSolver: LaunchGeneticSolvingPort) : NursePlanningPort {

  override fun getNursePlanning(configuration: Configuration): PlanningResult {
    val random = SecureRandom()
    val initializationStrategy = NurseInitializationStrategy(configuration, random, 27)
    val context = SolvingContext(
      initializationStrategy, NursePlanningFitnessStrategy(), UnbiasedTournamentSelectionPairingStrategy(random),
      SingleGeneMutation(random, initializationStrategy.getGenesPool()),
    SinglePointCrossoverStrategy(random), configuration)
    val bestSolution = geneticSolver.execute(context)
    return convertToPlanningResult(bestSolution)
  }

  private fun convertToPlanningResult(chromosome: Chromosome<List<Nurse>>): PlanningResult =
    PlanningResult(chromosome.genes.map { it.value }.map { it })

}
