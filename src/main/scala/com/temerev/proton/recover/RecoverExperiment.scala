package com.temerev.proton.recover

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import com.temerev.proton.common.Sources

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object RecoverExperiment extends App {
  implicit val system = ActorSystem("recover")
  implicit val mat = ActorMaterializer()

  val printer = Sink.foreach(println)
  val logger = Sink.onComplete {
    case Success(t) => println("Stream completed successfully")
    case Failure(e) => e.printStackTrace()
  }

  Sources.failTicks.alsoTo(logger).recoverWithRetries(-1, {
    case e => e.printStackTrace(); Sources.failTicks.initialDelay(5.seconds)
  }).to(printer).run()
}
