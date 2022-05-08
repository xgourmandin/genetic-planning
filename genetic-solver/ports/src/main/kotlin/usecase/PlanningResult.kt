package usecase

/**
 * Represents a planning result by a 2 dimension array.
 * First dimension is the work shift, second dimension is the employee code (String) assigned to this shift.
 */
data class PlanningResult(val planning: List<List<String>>)
