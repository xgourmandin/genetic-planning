package domain

import genetic.fitnessScore

typealias Population = List<Chromosome>
typealias Nurse = String

class Chromosome(val genes: List<Gene>) {
    override fun toString(): String {
        return genes.joinToString(separator = " :: ") { it.toString() }
    }
}

class Gene(val nurses: List<Nurse>) {
    override fun toString(): String {
        return nurses.joinToString(separator = ", ") { it }
    }
}

fun Population.bestFitnessScore(printDetails:Boolean = false): Double = maxOf { it.fitnessScore(printDetails) }

fun Population.bestChromosome(): Chromosome? = maxByOrNull { it.fitnessScore() }
fun Population.worstChromosome(): Chromosome? = minByOrNull { it.fitnessScore() }
