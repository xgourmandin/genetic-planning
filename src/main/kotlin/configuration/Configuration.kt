package configuration

class Configuration {

    val chromoseSize: Int = planningDurationInDays() * workshiftsPerDay()
    val populationSize: Int = 15

    val crossoverProbability: Double = 0.6
    val mutationProbability: Double = 0.3

    fun workshiftsPerDay() = 2
    fun employeePerWorkshift() = 4
    fun totalEmployees() = 27
    fun planningDurationInDays() = 14



}
