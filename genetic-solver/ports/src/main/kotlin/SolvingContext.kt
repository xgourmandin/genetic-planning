import genetic.*

class SolvingContext<T>(
  val initializationStrategy: InitializationStrategy<T>, val fitnessStrategy: FitnessStrategy<T>,
  val selectionStrategy: SelectionPairingStrategy, val mutationStrategy: MutationStrategy<T>,
  val crossoverStrategy: CrossoverStrategy<T>, val configuration: Configuration
)
