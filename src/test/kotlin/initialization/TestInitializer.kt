package initialization

import configuration.Configuration
import kotlin.test.Test
import kotlin.test.assertEquals

class TestInitializer {

    @Test
    fun testChromosomeInitialization() {
        val initializer = Initializer(Configuration());
        val population = initializer.initializePopulation()
        assertEquals(population.size, initializer.config.populationSize)
        val firstChromosome = population[0]
        assertEquals(firstChromosome.genes.size, initializer.config.planningDurationInDays() * initializer.config.workshiftsPerDay())
        val firstGene = firstChromosome.genes[0]
        assertEquals(firstGene.employees.size, initializer.config.employeePerWorkshift())
    }
}
