package genetic

import configuration.Configuration
import domain.Chromosome
import domain.Gene
import domain.Population
import java.security.SecureRandom

val random = SecureRandom()

class Initializer(config: Configuration) {

    val genesPool: List<Gene>
    private val config: Configuration

    init {
        this.config = config
        genesPool = createGenesPool(config)
    }

    private fun createGenesPool(config: Configuration): List<Gene> {
        val genes = mutableListOf<Gene>()
        for (n1 in 1..config.totalEmployees) {
            for (n2 in n1+1..config.totalEmployees) {
                for (n3 in n2+1..config.totalEmployees) {
                    for (n4 in n3+1..config.totalEmployees) {
                        if (n1 != n2 && n1 != n3 && n1 != n4 && n2 != n3 && n2 != n4 && n3 != n4) {
                            genes.add(Gene(listOf("N${n1}", "N${n2}", "N${n3}", "N${n4}")))
                        }
                    }
                }
            }
        }
        return genes
    }


    fun initializePopulation(): Population = (1..config.populationSize).map {
        val genes = (0 until config.chromosomeSize).map { genesPool[random.nextInt(genesPool.size)] }
        Chromosome(genes)
    }

}
