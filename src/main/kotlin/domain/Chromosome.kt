package domain

import genetic.fitnessScore

typealias Population = List<Chromosome>
typealias Employee = String

class Chromosome(val genes: List<Gene>) {
    override fun toString(): String {
        return genes.joinToString(separator = " :: ") { it.toString() }
    }
}

class Gene(val employees: List<Employee>) {
    override fun toString(): String {
        return employees.joinToString(separator = ", ") { it }
    }
}

fun Population.bestFitnessScore(): Double = maxOf { it.fitnessScore() }

fun Population.bestChromosome(): Chromosome? = maxByOrNull { it.fitnessScore() }
