package genetic

import configuration.StaticConfiguration
import genetic.fitness.NursePlanningFitnessStrategy
import genetic.initialization.NurseInitializationStrategy
import java.security.SecureRandom
import kotlin.test.Test
import kotlin.test.assertTrue

class TestFitness {

    @Test
    fun testRandomFitness() {
        val randomPop = NurseInitializationStrategy(StaticConfiguration(), SecureRandom(), 27).initialize()
      val scorer = NursePlanningFitnessStrategy()
        assertTrue { scorer.fitness(randomPop[0]) < 1.0 }
    }

}
