package com.example.jetpackcomposeapplication

open class Person constructor(public val age: Int) {
    open fun talk() {
        val message: String
        message = "I am a simple person aged $age."
        println(message)
    }
}

class Teacher constructor(age: Int) : Person(age) {
    override fun talk() {
        val message: String
        message = "I am a teacher aged $age."
        println(message)
    }

    fun teach() {
        println("I teach.")
    }
}

fun main(args: Array<String>) {
    val simplePerson: Person = Person(20)
    simplePerson.talk()

    var teacher: Teacher = Teacher(30)
    teacher.talk()
    teacher.teach()

    teacher = Teacher(40)
    teacher.talk()
}