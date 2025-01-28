package com.example.practiceone

fun main(){
//    usingFunctionsAsObjects() //uncomment me to see how functions can be passed to other functions
   cleaningStrings() //uncomment me to see how you can use functions as filters for strings
}

fun usingFunctionsAsObjects(){
    val firstValue = 2
    val secondValue = 3
    println("Original sum of values are " + applyAndSum(firstValue,secondValue,::keepOriginalValue))
    println("Double sum of values are " + applyAndSum(firstValue,secondValue,::doubleOriginalValue))
    println("Triple sum of values are " + applyAndSum(firstValue,secondValue,::tripleOriginalValue))
}

//functions can be passed to other functions as objects
fun keepOriginalValue(initialValue:Int)=initialValue
fun doubleOriginalValue(initialValue:Int)=initialValue*initialValue
fun tripleOriginalValue(initialValue:Int)=initialValue*initialValue*initialValue

fun applyAndSum(a: Int, b: Int, transformation: (Int) -> Int): Int {
    return transformation(a) + transformation(b)
}

fun cleaningStrings(){
    val userInput = "bob.smith@abertay.ac.uk"
    println("Original input without dots " + userInput.filter(::removeDots))
    println("Original input without dots " + userInput.filter({c:Char -> c != '.'})) //You can also create your function at runtime and pass it as an object to another function. This is called Lambda function. More on this in coming weeks.
    println("Original input only letters " + userInput.filter(::onlyKeepLetters))
    println("Original input remains unchanged " + userInput)
    println("Original input without A and B " + userInput.filter(::removeA_and_B_Letters))
    println("Original input without A and B " + userInput.filter(::removeA_and_B_Letters_same))
}


fun removeDots(c:Char): Boolean = c != '.'
fun onlyKeepLetters(c:Char): Boolean = c.isLetter()
fun removeA_and_B_Letters(c:Char): Boolean = !(c == 'a' || c == 'b')
fun removeA_and_B_Letters_same(c:Char): Boolean {
    if(c == 'a' || c == 'b'){
        return false;
    }
    return true;
}
