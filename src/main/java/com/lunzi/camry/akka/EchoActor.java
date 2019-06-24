package com.lunzi.camry.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;

/**
 * echo actor
 * Created by lunzi on 2019/6/22 3:36 PM
 */
@Slf4j
public class EchoActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .matchAny(o -> log.info("Received unknown message"))
                .build();
    }

    public static void main(String[] args) {
        ActorSystem actorSystem=ActorSystem.create("app");
        ActorRef actorRef=actorSystem.actorOf(Props.create(EchoActor.class),"echoActor");

    }
}
