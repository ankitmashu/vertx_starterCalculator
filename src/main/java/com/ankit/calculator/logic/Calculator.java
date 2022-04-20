package com.ankit.calculator.logic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Calculator extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start();

    Router router = Router.router(vertx);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8888)
      .onSuccess(ok -> {
        System.out.println("Started Properly");
        startPromise.complete();
      })
      .onFailure(startPromise::fail);

    //HttpSerVer httpserver=vertx.createHttpServer();

    /*router.get("/add").handler(BodyHandler.create()).handler(this::add);
    router.get("/sub").handler(BodyHandler.create()).handler(this::sub);
    //router.get("/mul").handler(BodyHandler.create()).handler(this::mul);
    router.get("/div").handler(BodyHandler.create()).handler(this::div);*/
    router.get("/add/:num1/:num2").handler(routingContext -> {
      String n1 = routingContext.request().getParam("num1");
      String n2 = routingContext.request().getParam("num2");
      Double d1 = Double.valueOf(n1);
      Double d2 = Double.valueOf(n2);
      HttpServerResponse response = routingContext.response();
      response.setChunked(true);
      response.write(String.valueOf(d1 + d2));
      response.end();
    });
    router.get("/sub/:num1/:num2").handler(routingContext -> {
      String n1 = routingContext.request().getParam("num1");
      String n2 = routingContext.request().getParam("num2");
      Double d1 = Double.valueOf(n1);
      Double d2 = Double.valueOf(n2);
      HttpServerResponse response = routingContext.response();
      response.setChunked(true);
      response.write(String.valueOf(d1 -d2));
      response.end();
    });
    router.get("/div/:num1/:num2").handler(routingContext -> {
      String n1 = routingContext.request().getParam("num1");
      String n2 = routingContext.request().getParam("num2");
      Double d1 = Double.valueOf(n1);
      Double d2 = Double.valueOf(n2);
      HttpServerResponse response = routingContext.response();
      response.setChunked(true);
      if(d2==0.0)
      {
        response.write("Division not possible because no cannnot be divided by zero");
        response.end();
      }
      else {
      response.write(String.valueOf(d1 / d2));
      response.end();}
    });

    router.get("/mul/:num1/:num2").handler(routingContext -> {
      String n1 = routingContext.request().getParam("num1");
      String n2 = routingContext.request().getParam("num2");
      Double d1 = Double.valueOf(n1);
      Double d2 = Double.valueOf(n2);
      HttpServerResponse response = routingContext.response();
      response.setChunked(true);
      response.write(String.valueOf(d1 * d2));
      response.end();
    });
  }

}

