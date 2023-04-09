package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens

        //THis swallows the error and handles it
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(
                        num -> System.out.println(num),
                        err -> System.out.println("Error Happened" + err)
                );

        //THis doesnt swallows the error and can be used to effect some side effects or push something on the analytics
        ReactiveSources.intNumbersFluxWithException()
                .doOnError(e -> System.out.println(e.getMessage()))
                .subscribe(
                        num -> System.out.println(num)
                );

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((e, i) -> System.out.println(e.getMessage()))
                .subscribe(
                        num -> System.out.println(num)
                );

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(e -> Flux.just(-1, -2))
                .subscribe(
                        num -> System.out.println(num)
                );

        System.out.println("Press a key to end");
        System.in.read();
    }

}
