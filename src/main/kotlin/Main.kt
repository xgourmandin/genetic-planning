import configuration.Configuration
import domain.Population
import domain.bestFitnessScore
import domain.getBestChromosome
import genetic.Selection
import genetic.crossover
import genetic.mutate
import initialization.Initializer
import kotlin.random.Random

private val random: Random = Random.Default
private val configuration: Configuration = Configuration()

fun main(args: Array<String>) {
    var population = Initializer(configuration).initializePopulation()
    val selector = Selection()
    for (gen in 1..1000) {
        println("Best score in population : ${population.bestFitnessScore()}")
        if (population.bestFitnessScore() == 1.0) {
            println("Target reached in $gen generations")
            break
        }
        val newPopulation = selector.selection(population, configuration)
        val breadedPopulation: Population = newPopulation.withIndex().flatMap { (index, p) ->
            if (random.nextDouble() < configuration.crossoverProbability) {
                val partnerIndex = random.nextInt(newPopulation.size)
                val partner =
                    if (partnerIndex != index) newPopulation[partnerIndex] else newPopulation[(partnerIndex + 1) % newPopulation.size]
                val crossover = p.crossover(partner, random)
                return@flatMap listOf(crossover.first, crossover.second)
            } else {
                return@flatMap listOf(p)
            }
        }
        population = breadedPopulation.map {
            if (random.nextDouble() < configuration.mutationProbability) {
                return@map it.mutate(random)
            } else {
                return@map it
            }
        }
    }

    population.getBestChromosome()?.let { println("Best chromosome : $it") }
}
