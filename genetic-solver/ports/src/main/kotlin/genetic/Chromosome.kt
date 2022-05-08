package genetic

typealias Population<T> = List<Chromosome<T>>

class Chromosome<T>(val genes: List<Gene<T>>) {
  override fun toString(): String {
    return genes.joinToString(separator = " :: ") { it.toString() }
  }
}

class Gene<T>(val value: T) {
  override fun toString(): String {
    return when(value) {
      is List<*> -> value.joinToString(separator = ",")
      is String -> value
      is Double -> value.toString()
      else -> value.toString()
    }
  }
}
