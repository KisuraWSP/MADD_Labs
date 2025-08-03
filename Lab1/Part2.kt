// Part 2
// Q1
abstract class Employee(){
    fun pprint(){
        println("I am Employee")
    }
}

class TemporaryStaff(val name : String ,val department : String) : Employee() {
    fun printTempDetails() {
        println("Name: ${name}, Department: ${department}")
    }
}

class PermanentStaff(val name : String, val age : Int, val department: String, val status: String) : Employee() {
    fun printDetails() {
        println("Name: ${name}, Age: ${age}, Department: ${department}, Status: ${status}")
    }
}

// Q2
interface Shape {
    fun setup()
    fun draw()
}

class Circle() : Shape {
    override fun setup() {
        println("I am a Circle. Setting it up!")
    }

    override fun draw() {
        println("Drawing a Circle")
    }
}

class Square() : Shape {
    override fun setup() {
        println("I am a Circle. Setting it up!")
    }

    override fun draw() {
        println("Drawing a Circle")
    }
}

fun main() {
    // Q1
    val t1 = TemporaryStaff("halla", "IT")
    t1.printTempDetails()
    t1.pprint()

    val p1 = PermanentStaff("hello", 20, "SE", "CEO")
    p1.printDetails()
    p1.pprint()


    // Q2
    val c = Circle()
    c.setup()
    c.draw()

    val s = Square()
    s.setup()
    s.draw()
}
