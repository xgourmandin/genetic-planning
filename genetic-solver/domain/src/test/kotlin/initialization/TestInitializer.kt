package initialization

import configuration.StaticConfiguration
import genetic.initialization.NurseInitializationStrategy
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertEquals

class TestInitializer {

    @Test
    fun testChromosomeInitialization() {
        val config = StaticConfiguration()
        val initializer = NurseInitializationStrategy(config, SecureRandom(), 27)
        val population = initializer.initialize()
        assertEquals(population.size, config.populationSize())
        val firstChromosome = population[0]
        assertEquals(firstChromosome.genes.size, config.chromosomeLength())
        val firstGene = firstChromosome.genes[0]
        assertEquals(firstGene.value.size, 4)
    }

    @Test
    fun testGenesPoolInitialization() {
        val config = StaticConfiguration()
        val initializer = NurseInitializationStrategy(config, SecureRandom(), 27)
        val genesPool = initializer.getGenesPool()
        assertEquals((factorial(config.totalEmployees) / (factorial(4) * factorial(config.totalEmployees - 4))).toInt(), genesPool.size)
        val firstGene = genesPool[0]
        assertEquals(firstGene.value.size, 4)
    }

    private fun factorial(n: Int): Double {
        var factorial = 1.0
        for (i in 1..n) {
            // factorial = factorial * i;
            factorial *= i.toDouble()
        }
        return factorial
    }
}
