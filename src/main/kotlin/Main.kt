import configuration.Configuration
import domain.Chromosome
import genetic.Selection
import genetic.crossover
import genetic.fitnessScore
import genetic.mutate
import initialization.Initializer
import java.security.SecureRandom

private val random: SecureRandom = SecureRandom()
private val configuration: Configuration = Configuration()

fun main() {
    val selector = Selection(random)
    var scoredPopulation = Initializer(configuration).initializePopulation().map { Pair(it.fitnessScore(), it) }.sortedByDescending { it.first }


    for (i in 0..configuration.maxGenerations) {
         println("population size: ${scoredPopulation.size} on gen $i")
        duplicatedGenesInPopulation(scoredPopulation)
        scoredPopulation = scoredPopulation
            .map { Pair(selector.selection(scoredPopulation), selector.selection(scoredPopulation)) }
            .map { it.first.crossover(it.second, random) }
            .map { if (random.nextDouble() <= configuration.mutationProbability) it.mutate(random) else it }
            .map { Pair(it.fitnessScore(), it) }
            .sortedByDescending { it.first }
    }

    val bestOne = scoredPopulation.first()
    println(bestOne.first)
    println(bestOne.second)
}

fun duplicatedGenesInPopulation(population: List<Pair<Double, Chromosome>>) {
    val genes = population.map { it.second.genes }.flatten()
    genes.groupingBy { it.toString() }.eachCount().filter { it.value > 1 }.toList().sortedByDescending { it.second }.forEach { println(it) }
}
