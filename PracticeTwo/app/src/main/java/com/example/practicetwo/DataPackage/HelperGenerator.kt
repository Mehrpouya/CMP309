package com.example.practicetwo.DataPackage.Helpers

fun generateNumericalStrings(number: Int, padding: Int): String {
    require(number >= 0) { "our number must be more than 0" }
    require(padding > 0) { "Padding must be greater than 0" }

    val formatString = "%0${padding}d" // Create a format string based on padding

//    return (start..end).map { formatString.format(it) }
    return formatString.format(number)
}

