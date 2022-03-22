package com.bxfox.moduletest

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

class KTTest {
    //可变引用
    var   t=2

    //val不可变引用 对应的是 Java 中的 final 变量
    //尽可能使用 val，不可变对象及纯函数来设计程序。这样可以尽量避免副作用带来的影响
    val t2 :Int=3;

    //二进制
    val value1 = 0b00101
    //十六进制
    val value2 = 0x123

    fun testInt( ){

        val t22 :Int = 1200;

        //数字类型不会自动转型，必须要进行明确的类型转换
//        val doubleIndex: Double = t2.toDouble()

        var intValue_2: Int = t22
        var intValue_3: Int = t22
        //== 比较的是数值相等性，因此结果是 true
        println(intValue_2 == intValue_3)
        //=== 比较的是引用是否相等，因此结果是 true
        println(intValue_2 === intValue_3)

    }

    //字符串是不可变的，
    // 可以使用索引运算符访问[] 来访问包含的单个字符，
    // 也可以用 for 循环来迭代字符串，
    // 此外也可以用 + 来连接字符串
    fun testString(){
        val str = "leavesC"
        print(str[1])
        for (c in str){
            print(c)
        }

        val str1 = str + " hello"
        println("str value is $str")
        println("(t2 + 100) value is ${t2 + 100}")   //(intValue + 100) value is 200

        val price = "100.99"
        println("$ $price")  //$100.99
    }


    fun testList(){
        //包含给定元素的字符串数组
        val array1 = arrayOf("leavesC", "叶", "https://github.com/leavesC")

        array1[0] = "leavesC"
        println(array1[1])
        println(array1.size)

        //初始元素均为 null ，大小为 10 的字符数组
        val array2 = arrayOfNulls<String>(10)

        //创建从 “a” 到 “z” 的字符串数组
        val array3 = Array(26){
            it.toString()+"a"
        }



        //kotlin 为每一种基本数据类型都提供了若干相应的类并做了特殊的优化。
        // 例如，有 IntArray、ByteArray、BooleanArray 等类型，
        // 这些类型都会被编译成普通的 Java 基本数据类型数组，

        //指定数组大小，包含的元素将是对应基本数据类型的默认值(int 的默认值是 0)
        val intArray = IntArray(5)
        //指定数组大小以及用于初始化每个元素的 lambda
        val doubleArray = DoubleArray(5) { Random().nextDouble() }
        //接收变长参数的值来创建存储这些值的数组
        val charArray = charArrayOf('H', 'e', 'l', 'l', 'o')

    }
    //Any 类型是 kotlin 所有非空类型的超类型
    fun testAny(){
        val any: Any = 100
        println(any.javaClass) //class java.lang.Integer

        //使变量可以存储包括 null 在内的所有可能的值，则需要使用 Any?
        val any1: Any? = null
   }

    // Unit 类型类似于 Java 中的 void，可以用于函数没有返回值时的情况
    fun check(): Unit {
    }
    //等同于
    //如果返回值为 Unit，则可以省略该声明
    fun check2() {

    }

    //Nothing 类型没有任何值，只有被当做函数返回值使用,
    // 或者被当做泛型函数返回值的类型参数使用时才会有意义
    //可以用 Nothing 来表示一个函数不会被正常终止，从而帮助编译器对代码进行诊断
   //编译器知道返回值为 Nothing 类型的函数从不正常终止，所以编译器会把 name1 的类型推断为非空，因为 name1 在为 null 时的分支处理会始终抛出异常
    data class User(val name: String?)

    fun fail(message: String): Nothing {
        throw IllegalStateException(message)
    }
    fun testNothing(){
        val user = User("leavesC")
        val name = user.name ?: fail("no name")
        println(name) //leavesC

        val user1 = User(null)
        val name1 = user1.name ?: fail("no name")
        println(name1.length) //IllegalStateException
    }

    /**
     * 函数
     */
    //fun 用于表示声明一个函数，getNameLastChar 是函数名
    //str表示该函数传入参数，Char 表示函数的返回值类型是字符
    val name="wujingchao"
    fun getNameLastChar(str:String ): Char {
        return name.get(name.length - 1)
    }
    //等同于
    fun getNameLastChar2()=name.get(name.length - 1)

    //函数没有返回值，则可以声明为 Unit ，也可以省略 Unit
    //以下三种写法等价
    fun test1(str: String, int: Int): Unit {
        println(str.length + int)
    }

    fun test2(str: String, int: Int) {
        println(str.length + int)
    }

    fun test3(str: String, int: Int) = println(str.length + int)


    fun compute(index:Int,value:String){
        println(value.length + index)

    }
    //命名参数，即在调用某函数的时候，可以将函数参数名一起标明
    //增强代码的可读性,并且可以随意打乱参数的顺序
    fun doCompute(){
        compute(index = 1,value = "sf")
        compute(value = "sf",index = 1)
        compute( 11,"sf2")
    }
    //可以在声明函数的时候指定参数的默认值，从而避免创建重载的函数
    fun compute2(index:Int,value:String="abcd"){
        println(value.length + index)
    }

    //可变参数可以让我们把任意个数的参数打包到数组中传给函数
      fun addString(vararg str:String ){
        str.forEach { print(it) }
    }
    fun testAddString(){
        addString("123","455","678")

        //直接以数组为参数
        // kotlin 要求显式地解包数组，以便每个数组元素在函数中能够作为单独的参数来调用，
        // 这个功能被称为展开运算符，使用方式就是在数组参数前加一个 *
        val names = arrayOf("123", "455", "678")
        addString( *names)
    }

    //在函数中嵌套函数，被嵌套的函数称为局部函数
    fun testInnerFun(name:String,country:String){
        fun checkEmpty(string:String){
            if(string.isEmpty()){
                println("String is Empty")
            }
        }
        checkEmpty(name)
        checkEmpty(country)
    }
    /**
     * 表达式和条件循环
     */

    //list?.size 会在 list 为null 的时候print直接打印出null ，不会抛出 NullPointerException 。
   //list!!.size 会在 list 为null 的时候直抛出 NullPointerException,等同于java
   //size?:0 等同于java的 size==null? 0: size
    val list :List<String>?= null
    fun getListSize(){
        print(list?.size)

        if (list?.size?:0 > 0) {
            Log.d("TAG", "-->> 列表数不是0")
        }
    }
    //=== 表示比较对象地址，== 表示比较两个值大小。
    //1000 换成 -128到127会有不同结果（常量池）
    fun testEqual(){
        val a : Int = 1000

        val a1 : Int = a
        val a2 : Int = a

        val a3 : Int? = a
        val a4 : Int? = a
        println(a1 == a2) //true
        println(a3 == a4) //true
        println(a1 === a2) //true 因为不加？的a1 a2 在jvm都是基本类型。编译后其实是 ==
        println(a3 === a4) //false 因为加？的a3 a4 在jvm都是对象类型 ，地址不同
    }

    //->
    //参数 combine 具有函数类型 (R, T) -> R，因此 fold 接受一个函数作为参数， 该函数接受类型分别为 R 与 T 的两个参数并返回一个 R 类型的值。
    // 在 for-循环内部调用该函数，然后将其返回值赋值给 accumulator。
    fun <T,R,B> Collection<T>.fold(
            initial: R,
            combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }


    //与 Java 不同，kotlin 中的 if 是可以作为表达式存在的，其可以拥有返回值
    //如果将 if 作为表达式而不是语句（例如：返回它的值或者把它赋给变量），该表达式需要有 else 分支
    fun getLength(str: String?): Int {
        val maxValue = if (20 > 10) {
            //最后的表达式作为该块的返回值
            //如果最后一条没有返回值，那么返回值类型就是 Unit ，就与java一样了
            println("maxValue is 20") //class kotlin.Unit
//            20 //int
        } else {
            println("maxValue is 10")
//            10
        }
        print(maxValue.javaClass)
        //相当于java的三元运算符
        return if (str.isNullOrBlank()) 0 else str.length

    }


    //when 既可以被当做表达式使用也可以被当做语句使用
    //when 将参数和所有的分支条件顺序比较直到某个分支满足条件，然后它会运行右边的表达式。
    //如果 when 被当做表达式来使用，符合条件的分支的值就是整个表达式的值
    //与 Java 的 switch/case 不同之处是 When 表达式的参数可以是任何类型，并且分支也可以是一个条件
    val value = 2
    fun testWhen(){

        when (value) {
            in 4..9 -> println("in 4..9") //区间判断
            3 -> println("value is 3")    //相等性判断
            2, 6 -> println("value is 2 or 6")    //多值相等性判断
            is Int -> println("is Int")   //类型判断
            else -> println("else")       //如果以上条件都不满足，则执行 else
        }

        //此外，When 也可以不带参数
        when {
            1 > 5 -> println("1 > 5")
            3 > 1 -> println("3 > 1")
        }
    }

    fun testFor() {
       // 和 Java 中的 for 循环最为类似的形式是
        val list = listOf(1, 4, 10, 34, 10)
        for (value in list) {
            println(value)
        }
       // 通过索引来遍历
        val items = listOf("H", "e", "l", "l", "o")
        //通过索引来遍历List
        for (index in items.indices) {
            println("${index}对应的值是：${items[index]}")
        }
        //也可以在每次循环中获取当前索引和相应的值
        for ((index, value) in list.withIndex()) {
            println("index : $index , value :$value")
        }
        //也可以自定义循环区间
        for (index in 2..10) {
            println(index)
        }

    }
    fun testBreak() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
       loop@ for (it in list) {
            if (it == 8) {
                return
            }
            if (it == 23) {
                break@loop
            }
            println("value is $it")
        }
        println("function end")
    }

    val index=4;
    fun testRanges(){

        //以下三种写法是等价的
        if (index >= 0 && index <= 10) {
        }

        if (index in 0..10) {
        }

        if (index in 0.rangeTo(10)) {
        }

        //也可以
        for (index in 10 downTo 0) {
            println(index)
        }
        //指定步长
        for (index in 8 downTo 1 step 2) {
            println(index)
        }
        //开区间
        for (index in 0 until 4){
            println(index)
        }

        val rangeTo = 1.rangeTo(3)
        for (i in rangeTo) {
            println(i) //1  2  3
        }
        //反转区间
        for (i in rangeTo.reversed()) {
            println(i) //3  2  1
        }

    }

    /**
     *  空安全
     *  在 kotlin 中，类型系统将一个引用分为可以容纳 null （可空引用）
     *  或者不能容纳 null（非空引用）两种类型。
     */
    fun testNull(){
        //String 类型的常规变量不能指向 null
        val name1: String = "leavesC"
        //编译错误
//        name1 = null
        //如果希望一个变量可以储存 null 引用，需要显式地在类型名称后面加上问号
        var name2: String? = "leavesC"
        name2=null
        print(name1.length?:0+1)
    }
    fun check(name: String?): Boolean {
        //error，编译器不允许不对 name 做 null 检查就直接调用其属性
//        return name?.isNotEmpty()

        if(name!=null){
            return name.isNotEmpty();
        }else{
            return false
        }
    }
    //安全调用运算符：?. 允许把一次 null 检查和一次方法调用合并为一个操作
    // ，如果变量值非空，则方法或属性会被调用，否则直接返回 null
    //以下两个方法等价
    fun check1(name: String?):String? {
        if (name != null) {
            return (name.toUpperCase())
        } else {
            return (null)
        }
    }
    fun check2(name: String?):String? {
        return(name?.toUpperCase())
    }

    //Elvis 运算符：?:
    //以下两个方法等价
    //Elvis 运算符接收两个运算数，如果第一个运算数不为 null ，运算结果就是其运算结果值，
    // 如果第一个运算数为 null ，运算结果就是第二个运算数
    fun check3(name: String?) :String {

        if (name != null) {
            return(name)
        } else {
            return("default")
        }
    }

    fun check4(name: String?):String {
        return(name ?: "default")
    }

    //安全转换运算符：as?
    //如果值不适合该类型则返回 null
    fun check5(any:Any?){
        val result=any as? String
        println(result ?: println("is not String"))
    }


}

/**
 * 修饰符
 * Kotlin 中的类和方法默认都是 final 的，即不可继承的
 * 如果想允许创建一个类的子类，需要使用 open 修饰符来标识这个类，
 * 此外，也需要为每一个希望被重写的属性和方法添加 open 修饰符
 */
open class MyView( name:String){
    open fun click():Boolean {
        return true
    }
}
private class MySonView:MyView("fe"){
     val a:Int=2

    override final fun click(): Boolean {

        return super.click()
    }
}

/**
 *
修饰符	         类成员	           顶层声明
public（默认）	 所有地方可见	所有地方可见
internal    	 模块中可见	      模块中可见
protected	     子类中可见
private	         类中可见	      文件中可见
 */








fun main(args: Array<String>){
    KTTest().testNull()


}