package com.ankit.calculator.vertx_starterCalculator;

import com.ankit.calculator.logic.Calculator;
import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
 private static final Logger LOG= LoggerFactory.getLogger(MainVerticle.class);
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
    LOG.info("Start Verticle ---" + MainVerticle.class.getName());

    //vertx.deployVerticle(new Calculator().class);
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
        vertx.deployVerticle(new Calculator(), event->{
          if(event.succeeded()){
            LOG.info("Successfully deployed Calculator Verticle");
            startPromise.complete();
            LOG.info("HTTP server started on port 8888 and deployed successful");
          }
          else{
           LOG.info(String.valueOf(event.cause()));
          }
        });
  }
}
