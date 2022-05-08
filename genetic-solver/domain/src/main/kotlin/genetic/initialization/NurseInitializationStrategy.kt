package genetic.initialization

import genetic.*
import genetic.fitness.Nurse
import java.security.SecureRandom

class NurseInitializationStrategy(private val config: Configuration, private val random: SecureRandom, val nursesTotal: Int) :
  InitializationStrategy<List<Nurse>> {

  private var genesPool: List<Gene<List<Nurse>>>

  init {
    genesPool = createGenesPool()
  }

  private fun createGenesPool(): List<Gene<List<Nurse>>> {
    val genes = mutableListOf<Gene<List<Nurse>>>()
    for (n1 in 1..nursesTotal) {
      for (n2 in n1 + 1..nursesTotal) {
        for (n3 in n2 + 1..nursesTotal) {
          for (n4 in n3 + 1..nursesTotal) {
            if (n1 != n2 && n1 != n3 && n1 != n4 && n2 != n3 && n2 != n4 && n3 != n4) {
              genes.add(Gene(listOf("N${n1}", "N${n2}", "N${n3}", "N${n4}")))
            }
          }
        }
      }
    }
    return genes
  }

  override fun identifier(): String = "NurseInitialization"

  override fun initialize(): Population<List<Nurse>> =
    (1..config.populationSize()).map {
      val genes = (0 until config.chromosomeLength()).map { genesPool[random.nextInt(genesPool.size)] }
      Chromosome(genes)
    }

  fun getGenesPool(): List<Gene<List<Nurse>>> = genesPool
}
