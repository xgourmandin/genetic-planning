package genetic

interface CrossoverStrategy<T> {

  fun identifier(): String

  fun crossover(firstParent: Chromosome<T>, secondParent: Chromosome<T>): Chromosome<T>
}
