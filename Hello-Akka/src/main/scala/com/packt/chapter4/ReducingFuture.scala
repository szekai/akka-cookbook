package com.packt.chapter4

import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object ReducingFuture extends App{
  val timeout = Timeout(10 seconds)
  val listOfFuture = (1 to 10).map(Future(_))
  val finalFuture = Future.reduce(listOfFuture)(_ + _)
  println(s"sum of numbers from 1 to 10 is ${Await.result(finalFuture, 10 seconds)}")
}
