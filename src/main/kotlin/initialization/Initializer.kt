package initialization

import domain.Chromosome
import configuration.Configuration
import domain.Employee
import domain.Gene
import domain.Population
import java.security.SecureRandom

val random = SecureRandom()

class Initializer(val config: Configuration) {
    fun initializePopulation(): Population = (1..config.populationSize).map {
        val employeesPool = randomEmployeePool()
        val genes = (0 until config.chromosomeSize).map { j ->
            Gene(employeesPool.subList(j*config.employeePerWorkShift, j*config.employeePerWorkShift + 4 ))
        }
        Chromosome(genes)
    }

    private fun randomEmployeePool(): List<Employee> {
        val employeesCode = (0 until config.totalEmployees).map { i ->
            "N${i+1}"
        }
        val poolSize = config.planningDurationInDays * config.workShiftsPerDay * config.employeePerWorkShift
        val employees: List<Employee> = (0..poolSize).map { employeesCode[random.nextInt(config.totalEmployees)] }
        return employees.shuffled(random)
    }


}
