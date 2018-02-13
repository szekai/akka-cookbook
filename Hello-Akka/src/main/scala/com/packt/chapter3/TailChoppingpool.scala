package com.packt.chapter3

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.routing.{TailChoppingPool}
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

class TailChoppingpoolActor extends Actor{
  override def receive: Receive = {
    case msg:String => sender ! "I say hello back to you"
    case _ => println(s" I don't understand the message")
  }
}

object TailChoppingpoolApp extends App{
  implicit val timeout = Timeout(10 seconds)

  val actorSystem = ActorSystem("Hello-Akka")
  val router = actorSystem.actorOf(TailChoppingPool(5, within = 10 seconds, interval = 20 millis).props(Props[TailChoppingpoolActor]))

  println(Await.result((router ? "hello").mapTo[String], 10 seconds))

}
