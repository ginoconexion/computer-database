package com.excilys.computerdatabase.gatling.process

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by Cédric Cousseran on 29/03/16.
  * Process to add a computer.
  */
object Add {
  val config = ConfigFactory.load()

  val random = new util.Random
  val feederName = Iterator.continually(Map("addComputerName" -> (random.nextInt.toString() + random.nextInt.toString() + random.nextInt.toString()))
  )
  val feederAdd = csv("data/addComputer.csv").random

  val add = exec(http("Add page")
    .get(config.getString("application.urls.addPage"))
    .resources(http("Add js")
      .get(config.getString("application.urls.static.js.add"))))
    .pause(random.nextInt(7) + 3)
    .feed(feederName)
    .feed(feederAdd)
    .exec(http("Add post")
      .post(config.getString("application.urls.addPost").get)
      .formParam(config.getString("application.urls.form.add.name").get, "${addComputerName}")
      .formParam(config.getString("application.urls.form.add.introduced").get, "${addComputerIntroduced}")
      .formParam(config.getString("application.urls.form.add.discontinued").get, "${addComputerDiscontinued}")
      .formParam(config.getString("application.urls.form.add.companyId").get, "${addComputerCompany}"))
    .pause(random.nextInt(7) + 3)
}
