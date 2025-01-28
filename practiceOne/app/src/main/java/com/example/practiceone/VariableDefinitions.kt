package com.example.practiceone

class VariableDefinitions {
    val pi = 3.1415              // Double
    val e = 2.71828f             // Float because it is tagged with f
    val fraction: Float = 1.51f  // Float because the type is specified
//    var name: String = null      // This won't work as the type is specified as non-nullable
    var nullableName: String? = null // This is fine as the type is nullable
    //Use of Elvis operator
    var copyOfNullableName = nullableName?.toString() ?: "Unknown" //copies nullableName if it is not null, otherwise set the string to "Unknown"
    fun functionName(integerInput: Int, stringInput: String): String {
        val result = "$integerInput $stringInput" // variable insertion
        return result
    }

    fun printNTimes(howMany: Int?) {//nullable input for our function!
        repeat(howMany?:0){ // check howMany is not null, if it is don't repeat.
            println("printing")
        }
    }
}
fun main() {
    ListDefinitions()
}
fun ListDefinitions(){
    var ListOfStudents = listOf<String>("Hadi","Alessandro","Luke") //PAy attention to case sensative functions.
    var ConvertedToMutableList = ListOfStudents.toMutableList()//converting immutable list to mutable list
    var MutableListOfStudents = mutableListOf<String>("Hadi","Alessandro","Luke")
    MutableListOfStudents.add("John")
    // MutableList has the same properties and methods as List: size, get(index), isEmpty(), indexOf(element), contains(element).
    /*In addition it also have
     - add(element) is a method that adds an extra element to your list.
     - set(index, element) replaces the element at the specified position with the specified element.
     - mutableList[index] = element
     - addAll(elements)
    */
    val shoppingList = mutableListOf("Tea", "Eggs", "Milk")
    shoppingList.add("Chips")
    shoppingList[0] = "Builder's tea" // changing first element
    shoppingList.set(0,"Builder's tea") // same as above
    shoppingList.addAll(listOf("Cheese", "Coke")) //adds both elements to our list
    println(shoppingList) // output: [Builder's tea, "Eggs", "Milk", "Cheese", "Coke"]
//    shoppingList.removeLast() //won't work as this function exists in API 35 and above.
    shoppingList.removeAt(shoppingList.size - 1) //removes last element
    shoppingList.remove("Coke")//removes Coke which is last element.
    println(shoppingList) // output: [Builder's tea, "Eggs", "Milk", "Cheese"]
    PrintIndividualItems(shoppingList)
    shoppingList.clear()//removes all elements
}
fun PrintIndividualItems(listToPrint: List<String>){
    for(item in listToPrint){
        println(item)
    }
}
