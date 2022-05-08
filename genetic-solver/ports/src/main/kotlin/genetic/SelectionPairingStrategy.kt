package genetic

interface SelectionPairingStrategy {
  fun identifier(): String
  fun <T> select(population: List<Pair<Double, Chromosome<T>>>): List<Pair<Double, Chromosome<T>>>
}
