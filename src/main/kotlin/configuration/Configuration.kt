package configuration

class Configuration {

    val maxGenerations: Int = 2000
    val workShiftsPerDay: Int = 2
    val employeePerWorkShift: Int = 4
    val totalEmployees: Int = 27
    val planningDurationInDays: Int = 7

    val chromosomeSize: Int = planningDurationInDays * workShiftsPerDay

    val populationSize: Int = 30
    val mutationProbability: Double = 0.1




}
