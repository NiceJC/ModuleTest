package com.bxfox.moduletest

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KProperty

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

    //非空断言 ！！(如果空 会抛异常)
    //非空断言用于把任何值转换为非空类型，如果对 null 值做非空断言，则会抛出异常
    fun checkNotNull(){
        var name: String? = "leavesC"
        check11(name) //7

        name = null
        check11(name) //kotlinNullPointerException
    }
    fun check11(name: String?) {
        println(name!!.length)
    }

    //可空类型的扩展
    //允许接收者为 null 的调用，并在该函数中处理 null
    //contract 上下文的关系传递
    fun checkNullExtend(){
        val name:String?=null;
        print(name.isNullOrEmpty())
    }

    //类型转换
    //编译器跟踪不可变值的 is 检查以及显式转换，并在需要时自动插入安全的转换
    //当类型满足is的时候，自动转为对应的类型
    fun parserTypeIs(value: Any) {
        when (value) {
            is String -> println("value is String , length : ${value.length}")
            is Int -> println("value is Int , toLong : ${value.toLong()}")
            !is Long -> println("value !is Long")
            else -> println("unknown")
        }
    }

    //as相当于强转，当as的类型不对 会抛出ClassCastException
    fun parserTypeAs(value: Any) {
        val tempValue = value as String
        println("value is String , length is ${tempValue.length}")
    }
    //也可使用as？在转换失败时返回null 而不抛出异常
    //因为转换结果是可空的 所以接受类型要加？
    fun parserTypeAs2(value: Any) {
        val tempValue:String? = value as? String
        println("value is String , length is ${tempValue ?.length}")
    }

    /**
     * 集合
     * 分为只读集合与可变集合
     * 集合元素      只读       可变
     * List         listOf     mutableListOf、arrayListOf
     * Set          setOf      mutableSetOf、hashSetOf、linkedSetOf、sortedSetOf
     * Map          mapOf      mutableMapOf、hashMapOf、linkedMapOf、sortedMapOf
     *
     */

    //可空性
    //两个维度，1：包含的元素可空 2：本身可空
    var intList: List<Int?>? = listOf(10, 20, 30, 40, null)

    /**
     * 扩展函数和扩展属性
     * 扩展函数表现得就像是属于这个类本身的一样，
     * 可以使用 this 关键字并直接调用其所有 public 方法
     * 和在类内部定义的方法不同的是，扩展函数不能访问私有的或是受保护的成员
     */
    //为 String 类声明一个扩展函数 lastChar() ，用于返回字符串的最后一个字符
    //get方法是 String 类的内部方法，length 是 String 类的内部成员变量，在此处可以直接调用
    fun String.lastChar() = get(length - 1)
    fun testLastChar(){
        val text="fete"
        print(text.lastChar())
    }

    //应用：为可空接收者定义扩展
    //方便处理空值
    fun String?.check(){
        if(this==null){
        }else{
        }
    }

    //Lambda 表达式
    //Lambda 表达式本质上就是可以传递给其它函数的一小段代码
    //1、一个 Lambda 表达式始终用花括号包围，通过箭头把实参列表和函数体分开
    //2、如果 Lambda 声明了函数类型，那么就可以省略函数体的类型声明
    //3、如果 Lambda 声明了参数类型，且返回值支持类型推导，那么就可以省略函数类型声明
    //以下三个plus方法 完全等价
    fun testLambda(){
        val plus1:(Int,Int)->Int={x:Int,y:Int->x+y}
        val plus2:(Int,Int)->Int={x,y->x+y}
        val plus3 = { x: Int-> x *2 }
        val a=5
        println( a.let{plus3})
    }
    class Person(age:Int){
        constructor():this(0)
        val age=age
    }
    fun testlambda2(){
        val people= listOf(Person(),Person(2))
        println(people.maxByOrNull {it.age })
    }



    //标准库中的扩展函数( Standard 文件下)
    /**
     *
     * 函数名   参数     返回值    扩展函数
     * let     it       闭包返回   是
     * apply   this     this      是
     * with    this     闭包       否
     * run     this     闭包       是
     *
     */

    //let返回值是闭包 最后一行函数的结果
    //使用it 指代对象
    val let=ExTest().let {
        it.name="wjc"
        name.length
    }
    fun testLet(){
        print(let)  //10
    }

    //apply 返回值是myName本身
    val apply=ExTest().apply {
        this.name=this.name.lowercase() //可以用this
        name.length //也可直接调用
    }
    fun testApply(){
        print(apply.name)  //wujignchao
    }

    //with不是扩展函数，使用方式略有不同
    //与apply不同的是 返回值是闭包
    fun testWith(){
        with(ExTest()){
            name=name.lowercase()
            name.length
        }.let {
            print(it) //10
        }
    }
    fun testRun(){
        ExTest().run {
            name=name.lowercase()
            name.length
        }.let {
            print(this)//10
        }
    }
    /**
     * 数组操作符
     */
    //any
    fun testCollectFun(){
        val list = listOf(1, 3, 5, 7, 9)

        //总数操作符
        //至少有一个元素符合给出的判断条件，则返回 true
        println(list.any { it > 13 })  //false
        println(list.any { it > 7 })   //true

        //如果全部的元素符合给出的判断条件，则返回 true
        println(list.all { it > 13 })  //false
        println(list.all { it > 0 })   //true

        //过滤操作符
        //过滤出所有符合给定函数条件的元素
        println(list.filter { it < 4 }) //[1, 3]

        //映射操作符
        //遍历所有的元素，为每一个创建一个集合，最后把所有的集合放在一个集合中
        println(list.flatMap { listOf(it, it + 1) }) //[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        println(list.flatMap { listOf(2*it )}) //[2, 6, 10, 14, 18]

        //返回一个每一个元素根据给定的函数转换所组成的List。
        println(list.map {it}) //[1, 3, 5, 7, 9]
        println(list.map {it+3}) //[4, 6, 8, 10, 12]
        //去掉null的元素
        println(list.mapNotNull {it+3}) //[4, 6, 8, 10, 12]


    }

    fun zhongshui(){
        //中缀调用
        val maps = mapOf(1 to "leavesC", 2 to "ye", 3 to "czy")

    }

    public inline fun Bitmap.applyCanvas(block: Canvas.() -> Unit): Bitmap {
        val c = Canvas(this)
        c.block()
        return this
    }


}
 class ExTest{
     var name="WuJignChao"
     var age=18
 }

/**
 * 修饰符(类默认是 final且 public 的)
 * Kotlin 中的类和方法默认都是 final 的，即不可继承的
 * 如果想允许创建一个类的子类，需要使用 open 修饰符来标识这个类，
 * 此外，也需要为每一个希望被重写的属性和方法添加 open 修饰符
 */
 open class MyView( name:String){
    open fun click():Boolean {
        return true
    }
}
 class MySonView:MyView("fe"){
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

class ccc1{
    constructor(age: Int)
}

class ccc2(){
    constructor(age: Int):this()
}

//constructor(val x: Int, val y: Int) 这部分为主构造函数
//constructor 在构造函数没有注解或可见性修饰符时可省略
class Point1   (val x: Int, val y: Int)
class Point2 private constructor (val x: Int, val y: Int)
//初始化的代码可以放到以 init 关键字作为前缀的初始化块(可以多个)
//构造函数的参数如果用 val/var 进行修饰，则相当于在类内部声明了一个同名的全局属性。
// 如果不加 val/var 进行修饰，则构造函数只能在 init 函数块和全局属性初始化时进行引用
class Point3  ( x: Int,  y: Int){
    init {
    }
    init {
    }

}
//次构造函数
//如果类有一个主构造函数，
// 每个次构造函数都需要直接委托给主构造函数或者委托给另一个次构造函数以此进行间接委托，
// 用 this 关键字来进行指定即可
class Point4(val x:Int,val y:Int){
//执行顺序：主构造函数--init代码块--次构造的函数体
//Point4(10L):sort1--sort2--sort3--sort4
    init {
        println("sort1")
    }
    init {
        println("sort2")
    }
    constructor(z :Int):this (z+1,z+2){
        println("sort3")
    }
    constructor(w :Long):this (w.toInt()){
        println("sort4")
    }
}
class  User{

    val  name:String="jc"
   var age:Int=19

}
//访问器默认实现逻辑很简单：
// 创建一个存储值的字段，以及返回属性值（val var）的 getter 和更新属性值（var）的 setter
//我们也可以自定义访问器
class Point9(val x: Int, val y: Int) {

    val isEquals1: Boolean
        get() {
            return x == y
        }

    val isEquals2
        get() = x == y

    var isEquals3 = false
        get() = x > y
        set(value) {
            field = !value
        }

}
//延迟初始化
//一般地，非空类型的属性必须在构造函数中初始化
//但像使用了 Dagger2 这种依赖注入框架的项目来说就十分的不方便了
//所以可以用 lateinit 修饰符来标记该属性，用于告诉编译器该属性会在稍后的时间被初始化
class Point5(val x: Int, val y: Int)
class Example() {

    //用 lateinit 修饰的属性或变量必须为非空类型，并且不能是原生类型
    lateinit var point: Point5
    var newPoint: Point5

    init {
        newPoint = Point5(10, 20)
    }
    fun printPoint(){
//        print(newPoint.x) //10
        //如果访问了一个未经过初始化的 lateinit 变量
//        print(point.x) //UninitializedPropertyAccessException
    }

}
//抽象类  默认open
 abstract class BaseClass{
    abstract fun fun1()
}
class NewClass :BaseClass(){
    override fun fun1() {
        TODO("Not yet implemented")
    }

}
//数据类  默认为主构造函数中声明的所有属性生成了如下几个方法
//getter、setter（需要是 var）
//componentN()。按主构造函数的属性声明顺序进行对应
//copy()
//toString()
//hashCode()
//equals()
//因此 数据类有以下要求
//主构造函数需要包含一个参数
//主构造函数的所有参数需要标记为 val 或 var
//数据类不能是抽象、开放、密封或者内部的
//重点：toString()、equals()、hashCode()、copy() 等方法只考虑主构造函数中声明的属性
data class Point6(val x: Int, val y: Int)

//密封类
//用 Sealed 修饰的类的直接子类只允许被定义在 Sealed 类所在的文件中
//对于 View 类，其子类只能定义在与之同一个文件里，
// Sealed 修饰符修饰的类也隐含表示该类为 open 类，因此无需再显式地添加 open 修饰符
sealed class View1 {
    open fun click() {
    }
}
class Button1 : View1() {
    override fun click() {
        super.click()
    }
}
class TextView1 : View1() {
}
class Test{
    // Sealed 类的子类对于编译器来说是可控的
    //所以如果在 when 表达式中处理了所有 Sealed 类的子类，
    // 那就不需要再提供 else 默认分支,编译器也不会报错
    fun check(view :View1):Boolean{
        when(view){
            is Button1->{
                return true
            }
            is TextView1->{
                return false
            }
        }
    }
}

//枚举类
//可以声明一些参数,也可以实现接口
enum class Day(val index: Int) {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6)
}
//匿名内部类
//使用对象表达式来创建匿名内部类实例
interface OnClickListener {
    fun onClick()
}

class View2 {
    fun setClickListener(clickListener: OnClickListener) {

    }
}

fun test() {
    val view = View2()
    view.setClickListener(object :OnClickListener{
        override fun onClick() {
        }

    })
}

//嵌套类与内部类
class Outer{
    val bar=1
    //正常声明 为嵌套类
    //嵌套类不会包含对外部类的隐式引用（相当于是静态内部类）
    class Nested{
        //报错
//        val barInner=bar
    }

    //加inner 变为内部类
    //内部类会隐式持有对外部类的引用
    inner class Nested2{
        val barInner=bar
    }

}

//代理  by 接口代理
interface  Delegate{
    fun do1()
}
class DelegateImp(delegate: Delegate):Delegate by delegate{
    override fun do1() {
        println("")
    }

}

//属性代理
class M{
    lateinit var b:String
    val s:String by MyDelegate {"hello" } //hello
    val t:String by lazy {"hello" } //hello
    val l:Lazy<String> = lazy {
        println("lazying")
        "hello" }
    val a:String by l
    fun initb(){
        b="ewef"
        println(b)
    }

}

class MyDelegate<T>(val init:()->T){
    operator fun getValue(thisRef:Any?,property:KProperty<*>):T{
        return init()
    }
}



object tt {

    @JvmStatic
    fun main(args: Array<String>){
        println(  M().s)
        println(  M().t)
        println(  M().l)
        println(  M().a)
        M().initb()

    }
}
