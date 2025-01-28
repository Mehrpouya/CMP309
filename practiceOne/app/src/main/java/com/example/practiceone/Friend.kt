package com.example.practiceone

data class Friend
(val firstName: String,val Surname: String, val email: String) {
    val fullName = "$firstName $Surname"
    val initials2 by lazy { firstName.firstOrNull().toString() + Surname.firstOrNull().toString() }
    val initials = listOf(firstName.firstOrNull().toString() , Surname.firstOrNull().toString())
    val contactDetails = listOf(fullName, email)
    var posessions = mutableListOf(String)
}
fun main (args: Array<String>) {
    val myFriend = Friend("Hadi","Mehrpouya","h.mehrpouya@abertay.ac.uk")
    println(myFriend.fullName)
    println(myFriend.initials)
    println(myFriend.contactDetails)
//    myFriend.posessions.add("Salt shaker")

    val myName : String = "Hadi"
    print("Welcome ")
    println("to my app $myName")
    println(myName.length)

    var myAge = 20
    var myAge2: Int = 20
//    const val CONST: Int
    val listNumber = mutableListOf<Int>()
//    CONST = 100
//    listNumber.add(CONST * CONST)
//    listNumber.add(CONST * CONST * CONST)
//    listNumber.add(CONST * CONST * CONST * CONST)
//    println(listNumber)

    val ten = 10_000  //ten thousand
    val greeting = "Hello"
    val firstLetter = 'A'

    var mutableTen = 10
    mutableTen = 20
//    mutableTen = "error" // mutable types variables can only be reassigned to a new value of the same type

//    val immutableInt : Int // Creates the immutable variable immutableInt of type Int
//    println(immutableInt) // Compiler error as you haven't initialised immutableInt
//    immutableInt = 10 // initialises immutableInt with the value 10
}
