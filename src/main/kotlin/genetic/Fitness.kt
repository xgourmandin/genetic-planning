package genetic

import domain.Chromosome

fun Chromosome.fitnessScore(): Double {
    var twoConsecutiveShifts = 0
    var sameShifts = 0
    for ((index, g) in genes.withIndex()) {
        for (e in g.employees) {
            if (genes[(index + 1) % genes.size].employees.contains(e)) {
                twoConsecutiveShifts++
            }
            if (g.employees.filter { it == e }.size > 1) {
                sameShifts++
            }
        }
    }
    return 1 / (1+(0.8*twoConsecutiveShifts + 0.8*sameShifts))
}
