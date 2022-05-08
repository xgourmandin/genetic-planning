package usecase

import genetic.Configuration

interface NursePlanningPort {
  fun getNursePlanning(configuration: Configuration): PlanningResult
}
