import configuration.Configuration
import domain.Chromosome
import domain.bestChromosome
import domain.bestFitnessScore
import domain.worstChromosome
import genetic.*
import java.security.SecureRandom
import java.util.stream.Collectors

private val random: SecureRandom = SecureRandom()
private val configuration: Configuration = Configuration()

fun main() {
    val selector = Selection(random)
    val initializer = Initializer(configuration)
    val genesPool = initializer.genesPool
    var scoredPopulation = initializer.initializePopulation().map { Pair(it.fitnessScore(), it) }
        .sortedByDescending { it.first }


    for (i in 0..configuration.maxGenerations) {
        //println("population size: ${scoredPopulation.size} on gen $i")
        if (i % 20 == 0) {
            println("=================== generation $i ====================")
            val bestMember = scoredPopulation.map { it.second }.bestChromosome()
            println("Best member : ${bestMember?.fitnessScore()}")
        }
        val bestMember = scoredPopulation.map { it.second }.bestChromosome()
        if (bestMember?.fitnessScore() == 1.0) break
        val selectedPopulation = selector.unbiasedTournamentSelection(scoredPopulation)
        val couples = selectedPopulation.zip(selector.permutation(selectedPopulation)).parallelStream()
            .map { it.first.second to it.second.second }
        val afterCrossoverAndMutationPopulation = couples
            .map { it.first.singlePointCrossover(it.second, random) }
            .map { if (random.nextDouble() <= configuration.mutationProbability) it.mutate(random, genesPool) else it }
            .collect(Collectors.toList())

        //elitism
        val indexToReplace = afterCrossoverAndMutationPopulation.indexOf(afterCrossoverAndMutationPopulation.worstChromosome())
        afterCrossoverAndMutationPopulation[indexToReplace] = bestMember

         scoredPopulation = afterCrossoverAndMutationPopulation.map { Pair(it.fitnessScore(), it) }
    }

    println("********************** Best found solution **********************")
    val bestOne = scoredPopulation.map { it.second }.bestChromosome()
    println(bestOne)
}
