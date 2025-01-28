fun main (args: Array<String>) {
    val contact = Contacts("Hadi","Mehrpouya","h.mehrpouya@abertay.ac.uk")
    println(contact.fullName)
    println(contact.initials)
    println(contact.contactDetails)
}
data class Contacts(val firstName: String,val Surname: String, val email: String) {
    val fullName = "$firstName $Surname"
    val initials = firstName.firstOrNull().toString() + Surname.firstOrNull().toString()
    val initials = listOf(firstName.firstOrNull().toString() , Surname.firstOrNull().toString())
    val contactDetails = listOf(fullName, email)
}