package configuration

import genetic.Configuration

class StaticConfiguration : Configuration {

  val totalEmployees: Int = 27

  override fun populationSize(): Int = 1000

  override fun chromosomeLength(): Int = 28

  override fun generationLimit(): Int = 1000

  override fun mutationRate(): Double = 0.33

  override fun crossoverRate(): Double = 1.0

  override fun elitism() = true

}
