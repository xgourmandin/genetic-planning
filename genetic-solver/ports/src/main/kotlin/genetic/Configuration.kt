package genetic

interface Configuration {
  fun populationSize(): Int
  fun chromosomeLength(): Int
  fun generationLimit(): Int
  fun mutationRate(): Double
  fun crossoverRate(): Double
  fun elitism(): Boolean
}
