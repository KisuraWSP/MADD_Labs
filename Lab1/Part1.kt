// Part 1
// Q1
fun whoAmI(args: Any) : Any {
    print(args)
    return args
}

// Q2
fun areaOfCircle(r : Double) : Double {
    return 3.14 * r * r
}

fun circumferenceOfACircle(r : Double) : Double {
    return 2 * 3.14 * r
}

// Q3
fun greetings(name : String, age : Int) {
    println("hello ${name} i am ${age}")    
    if (age < 18) {
        println("You are not eligible for the driving license. The age limit is 18")
    } else {
        println("You are eligible for the license")
    }
}


// Q4 
fun storeNames() {
    var i = 0
    val namesArray = mutableListOf<String>()
    while(i < 5) {   
        val store: String = readln()
        namesArray.add(store)
        i++
    }
    
    for(i in 0..(namesArray.size - 1)) {
        println("Name ${i+1}: ${namesArray[i]}")
    }
}

fun main() {
    // Q1
    println("================Q1===========")
    whoAmI("person")
    println("hallo")
    println(69420)
    println(3.14)
    println('c')
    println(true)
    
    // Q2
    println("================Q2===========")
    val c1 = areaOfCircle(10.0)
    val c2 = circumferenceOfACircle(10.0)
    println(c1)
    println(c2)
    
    
    //Q3
    println("================Q3===========")
    greetings("John", 20)
    
    //Q4
    println("================Q4===========")
    storeNames()
}
