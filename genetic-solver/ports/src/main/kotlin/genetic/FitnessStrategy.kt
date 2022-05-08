package genetic

interface FitnessStrategy<T> {
  fun identifier(): String
  fun fitness(individual: Chromosome<T>): Double
}
