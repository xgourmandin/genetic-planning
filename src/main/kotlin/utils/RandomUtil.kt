package utils

fun random(min: Int, max: Int): Int {
    return (Math.random() * (max - min + 1)).toInt() + min
}
