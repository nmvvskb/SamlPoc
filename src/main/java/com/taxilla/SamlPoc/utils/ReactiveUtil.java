package com.taxilla.SamlPoc.utils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;

public class ReactiveUtil {

    public static <T> Mono<T> processAsync(Callable supplier, Class<T> t) {
        return (Mono<T>) Mono.fromCallable(supplier).subscribeOn(Schedulers.boundedElastic());
    }

    public static <T> Mono<T> processAsync(Callable<T> supplier) {
        return Mono.fromCallable(supplier).subscribeOn(Schedulers.boundedElastic());
    }

}
