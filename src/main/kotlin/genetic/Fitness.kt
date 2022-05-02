package genetic

import domain.Chromosome

data class NurseMetrics(var totalCycleShifts: Int)

fun Chromosome.fitnessScore(printDetails: Boolean = false): Double {
    val nurseMetrics = this.genes.flatMap { g ->
        g.nurses.map {
            it to NurseMetrics(0)
        }
    }.toMap()
    var twoConsecutiveShifts = 0
    for ((index, g) in genes.withIndex()) {
        for (e in g.nurses) {
            if (genes[(index + 1) % genes.size].nurses.contains(e)) {
                twoConsecutiveShifts++
            }
        }
        g.nurses.forEach { nurseMetrics.getValue(it).totalCycleShifts += 1 }
    }
    val overBookedNurses = nurseMetrics.values.map { if (it.totalCycleShifts > 7) 1 else 0 }.reduce(Int::plus)
    if (printDetails) {
        println("Scoring details")
        println(nurseMetrics)
        println(this)
    }
    return 1 / (1 + (0.8 * twoConsecutiveShifts + 0.8 * overBookedNurses))
}
