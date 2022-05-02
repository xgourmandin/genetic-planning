package genetic

import configuration.Configuration
import kotlin.test.Test
import kotlin.test.assertTrue

class TestFitness {

    @Test
    fun testRandomFitness() {
        val randomPop = Initializer(Configuration()).initializePopulation()
        assertTrue { randomPop[0].fitnessScore() < 1.0 }
    }

}
