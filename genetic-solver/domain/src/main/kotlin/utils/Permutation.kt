package utils

import java.security.SecureRandom

/**
 * Gives a random permutation of indices of List @param collection, ensuring Pi != i for all i, as the unbiased tournament selection requires. (see https://www.cs.colostate.edu/~genitor/2005/GECCO247.pdf)
 */
fun <T> permutation(collection: List<T>, random: SecureRandom): List<T> {
  val populationPool = collection.withIndex().toMutableList()
  val permutatedPopulation = mutableListOf<T>()
  for (index in collection.indices) {
    var nextIndex: Int
    do {
      nextIndex = random.nextInt(populationPool.size)
    } while (populationPool[nextIndex].index != index)
    permutatedPopulation.add(populationPool[nextIndex].value)
    populationPool.removeAt(nextIndex)
  }
  return permutatedPopulation
}
