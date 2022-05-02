package configuration

class Configuration {

    val maxGenerations: Int = 4000
    val workShiftsPerDay: Int = 2
    val employeePerWorkShift: Int = 4
    val totalEmployees: Int = 27
    val planningDurationInDays: Int = 14

    val chromosomeSize: Int = planningDurationInDays * workShiftsPerDay

    val populationSize: Int = 1000
    val mutationProbability: Double = 0.33




}
