package com.packt.chapter3

import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.{BroadcastPool}

class BroadcastPoolActor extends Actor{
  override def receive={
    case msg: String => println(s" I am ${self.path.name}")
    case _ => println(s" I don't understand the message")
  }
}
object BroadcastpoolApp extends App {
  val actorSystem = ActorSystem("Hello-Akka")
  val router = actorSystem.actorOf(BroadcastPool(5).props(Props[BroadcastPoolActor]))

  for(i <- 1 to 6 ){
    router ! s"Hello $i"
  }
}
