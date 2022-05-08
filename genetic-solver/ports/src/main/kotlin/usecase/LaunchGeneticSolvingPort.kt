package usecase

import SolvingContext
import genetic.Chromosome

interface LaunchGeneticSolvingPort {
  fun <T> execute(context: SolvingContext<T>): Chromosome<T>
}
