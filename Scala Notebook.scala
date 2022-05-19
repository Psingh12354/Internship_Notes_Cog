// Databricks notebook source
// Value are immutable
val hello : String = "Hola"

// COMMAND ----------

// Variable are mutable
var hello : String = "hello"
var hello1 = hello + " World"

// COMMAND ----------

// Data type
val numberone : Int = 1
val truth: Boolean = true
val letterA: Char = 'a'
val pi: Double = 3.1415926
val piSInglePrecision: Float = 3.1415f
val bigNumber: Long = 1232434534
val smallNumber: Byte = 127

// COMMAND ----------

println("Here is the number : "+numberone)
// or
// $ represent var or val %means floating .3f 3 digit after float
println(f"Here is the number : $piSInglePrecision%.3f")
// precision at left
println(f"Here is the number : $numberone%05d")
//
println(s"I can use s prefix to use variable like $numberone $letterA ${1+2}")

// COMMAND ----------

// regex or regular expression
val string: String = "To life, the universe is 42."
val pattern = """.*([\d]+).*""".r
var pattern(answerString) = string
val answer = answerString.toInt
println(answer)

// COMMAND ----------

// boolean
val isGreater = 1>2

// COMMAND ----------

// flow control
if (1>3) println("True") else println("False")
// or
if (1>3){
  println("True")
}
else{println("False")}

// COMMAND ----------

// Switch case or matching
val number = 2
number match{
  case 1 => println("One")
  case _ => println("Default")
}

// COMMAND ----------

// for loop
for(x <- 1 to 4){
  val squared = x*x
  println(squared)
}

// COMMAND ----------

// while loop
var x = 10
while (x>=0){
  println(x)
  x=x-1
}

// COMMAND ----------

// do while
x = 0
do {println(x); x+=1}while(x<10)


// COMMAND ----------

// Expression
{val x = 10; x+20}
//or
println({val x = 10; x+20})

// COMMAND ----------

// functions
def squareIt(x : Int) : Int = {
  x*x
}
println(squareIt(2))
// Transform
def transformInt(x : Int, f:Int=> Int): Int = {
  f(x)
}
val result = transformInt(2,squareIt)

// COMMAND ----------

// Lambda or anonymous function
transformInt(3,x => x*x*x)

// COMMAND ----------

// data structure
// tuples
// list
val tuple = ("Pickard","Enerprise")
println(tuple)
// refer to the individual field with one-based index in tuple
println(tuple._1)
val bunch = ("Priyansu",123)


// list

val list = List("Hello","world")
println(list)
// list has 0-based index
println(list(1))

// COMMAND ----------

println(list.head)
println(list.tail)
// list via loop
for (i <- list){println(i)}
val backwardShips = list.map( (ship: String) => {ship.reverse})
for (ship <- backwardShips) {println(ship)}

// COMMAND ----------

// reduce
val numberList = List(1,2,3,4,5)
val sum = numberList.reduce((x : Int, y: Int) => x+y)
// filter() removes stuff
val iHateFives = numberList.filter( (x: Int) => x != 5)
// without declareing name x! = 3 we just define _ which is placeholder
val iHateThrees = numberList.filter(_ != 3)

// COMMAND ----------

// Concatenate lists
val moreNumbers = List(6,7,8)
val lotsOfNumbers = numberList ++ moreNumbers

val reversed = numberList.reverse
val sorted = reversed.sorted
val lotsOfDuplicates = numberList ++ numberList
val distinctValues = lotsOfDuplicates.distinct
val maxValue = numberList.max
val total = numberList.sum
val hasThree = iHateThrees.contains(3)

// MAPS
val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
println(shipMap("Janeway"))
println(shipMap.contains("Archer"))
val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
println(archersShip)
