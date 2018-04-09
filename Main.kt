import java.util.*

class ACMPattern(private var height:Int, private var width:Int){

    init {
        if(height<=0){
            height=16
        }
        if(width<=0){
            width=8
        }
    }

    //for A
    private fun printA(row:Int){
        when(row){
            1->{
                for (col in 1..width){
                    print('A')
                }
            }
            height/2->{
                for (col in 1..width){
                    print('A')
                }
            }
            else->{
                for (col in 1..width){
                    when(col){
                        1->{
                            print('A')
                        }
                        width->{
                            print('A')
                        }else->{
                            print(' ')
                        }
                    }
                }
            }
        }
    }

    fun printPattern(){
        for (row in 1..height){
            printA(row)
            printSpace()
            printC(row)
            printSpace()
            printM(row)
            println()
        }
    }

    //for M
    private fun printM(row: Int) {
        when(row){
            1->{
                for (col in 1..width){
                    print('M')
                }
            }
            else->{
                for (col in 1..width){
                    when(col){
                        1->{
                            print('M')
                        }
                        width->{
                            print('M')
                        }
                        width/2->{
                            print('M')
                        }
                        else->{
                        print(' ')
                    }
                    }
                }
            }
        }
    }

    //for C
    private fun printC(row: Int) {
        when(row){
            1->{
                for (col in 1..width){
                    print('C')
                }
            }
            height->{
                for (col in 1..width){
                    print('C')
                }
            }
            else->{
                for (col in 1..width){
                    when(col){
                        1->{
                            print('C')
                        }
                        else->{
                            print(' ')
                        }
                    }
                }
            }
        }
    }

    //for spaces
    private fun printSpace() {
        print(' ')
        print(' ')
    }

}
fun checkPalindrome(input:String,startIndex:Int=0,endIndex:Int=input.length-1):Boolean{
    if(startIndex>=endIndex){
        return true
    }
    if(input[startIndex]==input[endIndex]){
        return checkPalindrome(input,startIndex+1,endIndex-1)
    }else{
        return false
    }
}
class ComplexNumber(private var real:Int,private var img:Int){
    init {
        real=Math.abs(real)
        img=Math.abs(img)
    }
    constructor(value: Int) : this(value,value)
    constructor():this(0,0)
    fun add(complexNumber: ComplexNumber):ComplexNumber{
        return ComplexNumber(real+complexNumber.real,img+complexNumber.img)
    }
    fun multiply(complexNumber: ComplexNumber):ComplexNumber{
        return ComplexNumber(real*complexNumber.real-img*complexNumber.img,real*complexNumber.img+img*complexNumber.real)
    }
    fun printComplexNumber(){
        println("$real+i$img")
    }

}
fun main(args:Array<String>){


}