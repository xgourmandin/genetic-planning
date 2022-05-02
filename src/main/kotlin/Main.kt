import configuration.Configuration
import domain.Chromosome
import domain.bestChromosome
import domain.worstChromosome
import genetic.*
import utils.permutation
import java.security.SecureRandom
import java.util.stream.Collectors

private val random: SecureRandom = SecureRandom()
private val configuration: Configuration = Configuration()

/**
 * The entire process, including choice of algorithm is based on https://www.patatconference.org/patat2008/proceedings/Dean-WA3c.pdf
 */
fun main() {

    // Population initialization
    val initializer = Initializer(configuration)
    val genesPool = initializer.genesPool
    var scoredPopulation = initializer.initializePopulation().map { Pair(it.fitnessScore(), it) }
        .sortedByDescending { it.first }

    val selector = Selection(random)

    for (i in 0..configuration.maxGenerations) {
        progressHandler(i, scoredPopulation)
        val bestMember = scoredPopulation.map { it.second }.bestChromosome()
        if (bestMember?.fitnessScore() == 1.0) break
        // Selection
        val selectedPopulation = selector.unbiasedTournamentSelection(scoredPopulation)

        // Crossover (with probability of 100%)
        val afterCrossoverAndMutationPopulation = selectedPopulation.zip(permutation(selectedPopulation, random)).parallelStream()
            .map { it.first.second to it.second.second }
            .map { it.first.singlePointCrossover(it.second, random) }
        // Mutation
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

private fun progressHandler(
    i: Int,
    scoredPopulation: List<Pair<Double, Chromosome>>
) {
    if (i % 20 == 0) {
        println("=================== generation $i ====================")
        val bestMember = scoredPopulation.map { it.second }.bestChromosome()
        println("Best member : ${bestMember?.fitnessScore()}")
    }
}
