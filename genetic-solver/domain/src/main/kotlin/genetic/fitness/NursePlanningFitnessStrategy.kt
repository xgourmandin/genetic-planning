package genetic.fitness

import genetic.Chromosome
import genetic.FitnessStrategy

typealias Nurse = String

data class NurseMetrics(var totalCycleShifts: Int, var worksSunday: Int, var worksSaturday: Int)

class NursePlanningFitnessStrategy : FitnessStrategy<List<Nurse>> {
  override fun identifier(): String = "NursePlanningFitness"

  override fun fitness(individual: Chromosome<List<Nurse>>): Double {
    val nurseMetrics = individual.genes.flatMap { g ->
      g.value.map {
        it to NurseMetrics(0, 0, 0)
      }
    }.toMap()
    var twoConsecutiveShifts = 0
    for ((index, g) in individual.genes.withIndex()) {
      for (e in g.value) {
        nurseMetrics.getValue(e).totalCycleShifts += 1
        if (individual.genes[(index + 1) % individual.genes.size].value.contains(e)) {
          twoConsecutiveShifts++
        }
        if (index == 10 || index == 11 || index == 24 || index == 25) {
          nurseMetrics.getValue(e).worksSaturday += 1
        }
        if (index == 12 || index == 13 || index == 26 || index == 27) {
          nurseMetrics.getValue(e).worksSunday += 1
        }
      }
    }
    val overBookedNurses = nurseMetrics.values.sumOf { if (it.totalCycleShifts > 7) 1.toInt() else 0 }
    val worksTwoSundays = nurseMetrics.values.sumOf { if (it.worksSunday > 1) 1.toInt() else 0 }
    val worksTwoSaturdays = nurseMetrics.values.sumOf { if (it.worksSaturday > 1) 1.toInt() else 0 }

    return 1 / (1 + (0.8 * twoConsecutiveShifts + 0.8 * overBookedNurses + 0.8 * worksTwoSundays + 0.3 * worksTwoSaturdays))
  }
}
