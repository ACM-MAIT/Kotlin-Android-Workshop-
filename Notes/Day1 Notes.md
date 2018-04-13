# Workshop: Day 1

## How does the whole programming thing work?

![](https://www.onlinesolutionsgroup.de/blog/wp-content/uploads/Compiler.png)
### Compilers
- Programmers understand human readable code 
```c++
int i=i+1
```
- Computer understand machine level code
```
1000001
```
- We cannot understand machine language, and computers cannot understand high-level language.
- So, we need a translator/interpreter known as **Compiler** (magic box)
- Compiler takes source code as input and spits an executable file that can be run on computer

## What is Platform Independence in JAVA?

![](https://4a7efb2d53317100f611-1d7064c4f7b6de25658a4199efb34975.ssl.cf1.rackcdn.com/patch-or-perish-java-ftc-tells-oracle-showcase_image-1-p-2014.jpg)

### In CPP
- Assume we have a .cpp file , we compile  it and get a file a.out from compiler.
- We cannot run this file on two differnet processors.
- As, all processors though understand binary, they may interpret it differently
- So, compilers are processor dependent and we cannot a.out (executable file) run on two or more different processors.
- Source code can be run on both processors but code sharing is not always possible.

### In JAVA
- In .java file , compiler generates .class file and which can be run on different processors.
- The catch is that this .class file does not run directly on the processor but runs on **Java Virtual Machine (JVM)** and this JVM generates processor specific files that are then executed. JVM is platform dependent.
- So, one can easily share intermediatary code (Byte Code) without actually giving out the source code.

## Hello Kotlin
![](https://cdn-images-1.medium.com/max/2000/1*sTl0Lb8Lra9TzS184Qr7Dg.jpeg)

## Some Veggies

## Hello World

### Hello Java
```java
public class HelloJava {
    public static void main(String args[]){
        System.out.println("Hello Java");
    }
}
```

### Hello Kotlin

``` java
class HelloKotlin {
    fun main(args : Array<String>) {
        println("Hello, Kotlin!")
    }
}
```
- main() is a start block from where the compiler starts executing stuff
- In kotlin you can use main without a class


## Nullables Vs Non-Nullables

![](https://3.bp.blogspot.com/-h1zaRiGpneA/VvPqBqDlXmI/AAAAAAAAFQ8/mN3DmJ2LZjg28iFn-5MEb6thWsm1cYFQQ/s1600/best%2Bpractices%2Bto%2Bavoid%2BNullPointerException%2Bin%2BJava.jpg)

### Non-nullable
```
	var sampleString:String
    sampleString=null
```
### Nullable
```
	var sampleString:String?
    sampleString=null
```
- If a variable is nullable reference, then null checks are required to access its properties and methods

### Checking for null
- If-else

```
	var sampleString:String?="abc"
    if(sampleString!=null) {
        println(sampleString.length)
    }else{
        print("null")
    }
```

- Safe Calls
Returns value if exists or null

```
	var sampleString:String?="ablc"
    println(sampleString?.length)
```
- Elvis Operator
Returns value if exits or user defined value

```
	var sampleString:String?=null
    println(sampleString?.length?:"0")
```
- !! Operator
Throws NPE if value does not exist

```
	var sampleString:String?=null
    println(sampleString!!.length)
```

## Understanding Variables

### Val
- Assign-once (read-only)
- immutable variable
```kotlin
	val a=1
    println(a)
    //val b:Int=1 , val must be initialized
    val b:Int  
    //error: variable 'b' must be initialized
    println(b)
```

### Var
- Mutable variables
```kotlin
	var a = 1
    println(a)
    a += 1
    println(a)
```

### Lateinit var
- Initialize later , works well for global properties
- Cannot be used with primitive data types
- Used only to store non-nullable variables

We will see an example in studio with its use

## Control Flow Statements

### If Expression
Simple decision making statement i.e. returns a value
Traditional usage
```kotlin
	val scanner=Scanner(System.`in`)
    val a=scanner.nextInt()
    val b=scanner.nextInt()
    var min:Int
    if(b>a){
        min=a
    }else{
        min=b
    }
	println(min)
```
Kotlin usage
```kotlin
	val scanner=Scanner(System.`in`)
    val a=scanner.nextInt()
    val b=scanner.nextInt()
    var min:Int
    min = if(b>a){
        a
    }else{
        b
    }
	println(min)
```
### When
Replacement of switch operator in java
#### Switch-Case Statement
```kotlin
	public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int a=scanner.nextInt();
        switch (a){
            case 1:
                System.out.println("a is 1");
                break;
            case 2:
                System.out.println("a is 2");
                break;
            default:
                System.out.println("a is "+a);
        }
    }
```
#### When Statement
```kotlin
	val scanner=Scanner(System.`in`)
    val a=scanner.nextInt()
    when(a){
        1->{
            println("a is 1")
        }
        2->{
            println("a is 2")
        }
        else->{
            println("a is "+a) 
        }
    }
```

##### Versatility

```kotlin
	fun Int.isOdd():Boolean{
    	return (this%2==1)
	}
    val scanner=Scanner(System.`in`)
    val a=scanner.nextInt()
    val arr:IntArray= intArrayOf(1,3,5,7,9)
    val b:Any=a
    when(b){
        is Int->{
            println("a is Int")
        }
    }
    when(a){
        in 1..10->{
            println("a is in"+10)
        }
    }
    when(a){
        in arr->{
            println("a is arr")
        }
    }
	when{
        a.isOdd()->{
            println("a is odd")
        }
    }
```

## Loops

### For Loop and Ranges
Sequence of instruction s that is continually repeated until a certain condition is reached.
```kotlin
	val scanner=Scanner(System.`in`)
    val arr=Array(5,{i ->0  })
	//Ranges both values inclusive
    for (i in 0..4){
        arr[i]=scanner.nextInt()
    }
	//foreach loop
    for (i in arr){
        print(i)
    }
	//downTo and step
	for(i in 6 downTo 0 step 2){
        print(i)
    }
```

### Arrays
Collections of similar datatypes
```kotlin
	var arr=IntArray(5)
    for (i in arr){
        print("$i ")
    }
    println()
    arr= IntArray(5,{ i ->i  })
    for(i in 4 downTo 0 step 1){
        print("${arr[i]} ")
    }
    println()
    val arr1=Array(5,{ i -> i*2 })
    for (i in arr1){
        print("$i ")
    }
    val arr2=Array<String>(5,{ i -> "$i" })
    println()
    for (i in arr2){
        print("$i ")
    }
```
## Functions
Group of statements that together perform a task
```kotlin
fun print(x:String):Unit{
    println(x)
}
fun square(x:Int):Int{
    return x*x
}
fun Int.isOdd():Boolean{
    return (this%2==1)
}
fun add(a:Int,b:Int){
    println("Sum is ${a+b}")
}
fun sub(a:Int,b:Int=3){
    println("Diff is ${a-b}")
}

fun main(args: Array<String>) {
    //simple function with Unit
	print("abc");
	//simple function with 
    val x=square(5);
    println(x)
	//extension functions
    println(4.isOdd())
	//named parameters
    add(a=4,b = 3)
	//default arguments
    sub(1)
	//lambdas
	val sum={x:Int,y:Int->x+y}
    println(sum(3,4))
}
```

## Classes and Objects
User defined type or data structure declared with keyword class that has data and functions (also called methods) as its members whose access is governed by the three access specifiers private, protected or public .

### Complex Number Class

#### Constructor
Function used to make instances (objects) of a class

- Paramaterized Constructor

```
	class ComplexNumber(private var real:Int,private var img:Int)
```
- init Block

```
	init {
        real=Math.abs(real)
        img=Math.abs(img)
    }
```
- Secondary Constructors

```
	constructor(value: Int) : this(value,value)
    constructor():this(0,0)
```
- Member Functions

```
	fun add(complexNumber: ComplexNumber):ComplexNumber{
        return ComplexNumber(real+complexNumber.real,img+complexNumber.img)
    }
    fun multiply(complexNumber: ComplexNumber):ComplexNumber{
        return ComplexNumber(real*complexNumber.real-img*complexNumber.img,real*complexNumber.img+img*complexNumber.real)
    }
    fun printComplexNumber(){
        println("$real+i$img")
    }
```
## Lets Code

#### 1. Print the following pattern

![capture](https://user-images.githubusercontent.com/30664477/38470928-74e1d7c0-3b87-11e8-8ccc-3162829355fc.PNG)

#### 2. Check if a string is palindrom recursively
