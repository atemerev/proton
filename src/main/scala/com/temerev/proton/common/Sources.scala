package com.temerev.proton.common

import java.io.IOException

import akka.NotUsed
import akka.actor.Cancellable
import akka.stream.scaladsl.{Flow, Keep, Source}
import scala.concurrent.duration._

object Sources {
  case object Tick

  val ticks: Source[Tick.type, Cancellable] = Source.tick(0.millis, 100.millis, Tick)

  val failTicks: Source[Tick.type, NotUsed] = ticks.map { t =>
    val rand = math.random()
    if (rand < 0.95) t else throw new IOException("Well, shit")
  }.viaMat(Flow.fromFunction(identity))(Keep.right)

}
