package genetic

interface InitializationStrategy<T> {

  fun identifier(): String

  fun initialize(): Population<T>
}
