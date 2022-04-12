package genetic

import configuration.Configuration
import domain.Population
import java.security.SecureRandom

class Selection {

    private val random = SecureRandom()

    fun selection(population: Population, config: Configuration): Population {
        val selectionPool = createSelectionPool(population)
        return (0 until config.populationSize).map { selectionPool[random.nextInt(selectionPool.size)] }
    }

    private fun createSelectionPool(population: Population): Population =
        population.sortedBy { it.fitnessScore() }.flatMap {
            val duplicatedCount = mapToRange(0.0..1.0, 1.0..100.0, it.fitnessScore())
            buildList { for (i in 0 until duplicatedCount.toInt()) add(it) }
        }.shuffled(random)


    private fun mapToRange(
        from: ClosedFloatingPointRange<Double>,
        to: ClosedFloatingPointRange<Double>,
        value: Double
    ): Double {
        return ((to.endInclusive - to.start) * (value - from.start) / (from.endInclusive - from.start)) + to.start
    }
}
