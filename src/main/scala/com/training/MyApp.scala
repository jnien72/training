package com.training

import org.http4s.HttpService
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.dsl._

object MyApp {
  def main(arg:Array[String]):Unit= {
    val service = HttpService {
      case GET -> Root / "hello" / name => {
        if(name.equals("johnny")){
          Ok(s"hello ${name}")
        }else{
          BadRequest("don't talk to me")
        }
      }
      case req@POST -> Root / "echo" => {
        Ok(req.body)
      }
    }
    BlazeBuilder.bindHttp(8080, "0.0.0.0")
      .mountService(service, "/").run.awaitShutdown
  }
}
