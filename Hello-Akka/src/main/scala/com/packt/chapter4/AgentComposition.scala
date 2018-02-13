package com.packt.chapter4
import scala.concurrent.ExecutionContext.Implicits.global
import akka.agent.Agent

object AgentComposition extends App{
  val agent1 = Agent("Hello")
  val agent2 = Agent("World")
  val finalAgent = for {
    value1 <- agent1
    value2 <- agent2
  } yield value1 + value2
  println(finalAgent.get)

}
