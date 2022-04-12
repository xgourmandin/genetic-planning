package configuration

class Configuration {

    val workShiftsPerDay: Int = 2
    val employeePerWorkShift: Int = 4
    val totalEmployees: Int = 27
    val planningDurationInDays: Int = 14

    val chromosomeSize: Int = planningDurationInDays * workShiftsPerDay

    val populationSize: Int = 15
    val crossoverProbability: Double = 0.6
    val mutationProbability: Double = 0.3




}
