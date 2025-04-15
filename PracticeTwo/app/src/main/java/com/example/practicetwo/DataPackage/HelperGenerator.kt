package com.example.practicetwo.DataPackage.Helpers

fun generateNumericalStrings(start: Int, end: Int, padding: Int): List<String> {
    require(start >= 0) { "Start value must be non-negative" }
    require(end >= start) { "End value must be greater than or equal to start value" }
    require(padding > 0) { "Padding must be greater than 0" }

    val formatString = "%0${padding}d" // Create a format string based on padding

    return (start..end).map { formatString.format(it) }
}