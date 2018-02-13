package com.packt.chapter5

import akka.actor.{Actor, ActorSystem, Props}
import scala.concurrent.duration._

class RandomIntAdder extends Actor{
  val r = scala.util.Random

  override def receive: Receive = {
    case "tick" => val randomInta = r.nextInt(10)
      val randomIntb = r.nextInt(10)
      println(s"Sum of $randomInta and $randomIntb is ${randomInta + randomIntb}")
  }
}
object ScheduleActorApp extends App{
  val system = ActorSystem("Hello-Akka")
  import system.dispatcher
  val actor = system.actorOf(Props[RandomIntAdder])
  system.scheduler.scheduleOnce(10 seconds, actor, "tick")
  system.scheduler.schedule(11 seconds, 2 seconds, actor, "tick")

}
