package genetic

interface MutationStrategy<T> {

  fun identifier(): String

  fun mutate(chromosome: Chromosome<T>): Chromosome<T>
}
