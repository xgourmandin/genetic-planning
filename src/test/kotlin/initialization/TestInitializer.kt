package initialization

import configuration.Configuration
import genetic.Initializer
import kotlin.test.Test
import kotlin.test.assertEquals

class TestInitializer {

    @Test
    fun testChromosomeInitialization() {
        val config = Configuration()
        val initializer = Initializer(config)
        val population = initializer.initializePopulation()
        assertEquals(population.size, config.populationSize)
        val firstChromosome = population[0]
        assertEquals(firstChromosome.genes.size, config.planningDurationInDays* config.workShiftsPerDay)
        val firstGene = firstChromosome.genes[0]
        assertEquals(firstGene.nurses.size, config.employeePerWorkShift)
    }

    @Test
    fun testGenesPoolInitialization() {
        val config = Configuration()
        val initializer = Initializer(config)
        val genesPool = initializer.genesPool
        assertEquals((factorial(config.totalEmployees) / (factorial(4) * factorial(config.totalEmployees - 4))).toInt(), genesPool.size)
        val firstGene = genesPool[0]
        assertEquals(firstGene.nurses.size, config.employeePerWorkShift)
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
