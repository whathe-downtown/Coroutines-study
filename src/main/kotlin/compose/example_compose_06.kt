package compose


import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

suspend  fun concurrentSum(): Int = coroutineScope {
    val  one = async { doSomethingUsefulOne5() }
    val  two = async { doSomethingUsefulTwo5() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne5(): Int{
    println("dosometihngone")
    delay(1000L)
    return  13
}

suspend fun doSomethingUsefulTwo5(): Int {
    println("dosometihngtwo")
    delay(1000L)
    return 29

}
// 오호 동시에 실행해서 속도가 2024 => 1022 반으로 줄었다.
// async{ 함수 }  => println( ${one.await() + two.await()}
// one 이라는 Defered 객체를 : Job 으로 반환해서  값을 기다렸다가 반환
// asysnc (start= Coroutines.LAZY) 느린 객체 할당
