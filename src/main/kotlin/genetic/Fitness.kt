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
    var sameShifts = 0
    for ((index, g) in genes.withIndex()) {
        for (e in g.nurses) {
            if (genes[(index + 1) % genes.size].nurses.contains(e)) {
                twoConsecutiveShifts++
            }
            if (g.nurses.filter { it == e }.size > 1) {
                sameShifts++
            }
            g.nurses.forEach { nurseMetrics.getValue(it).totalCycleShifts += 1 }
        }
    }
    val overBookedNurses = nurseMetrics.values.map { if (it.totalCycleShifts > 6) 1 else 0 }.reduce(Int::plus)
    if (printDetails) {
        println("Scoring details")
        println(nurseMetrics)
        println(this)
    }
    return 1 / (1 + (0.8 * twoConsecutiveShifts + 0.8 * sameShifts + 0.8 * overBookedNurses))
}
