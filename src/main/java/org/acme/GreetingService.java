package org.acme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.quarkus.redis.client.RedisClient;
import io.quarkus.redis.client.reactive.ReactiveRedisClient;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.Response;

@Singleton
class GreetingService {

    @Inject
    RedisClient redisClient;

    @Inject
    ReactiveRedisClient reactiveRedisClient;

    Uni<List<String>> getReactive() {
        return reactiveRedisClient.keys("*")
            .map(response -> { 
                List<String> result = new ArrayList<>();
                for ( Response r : response) {
                    result.add(r.toString());
                }
                return result;
            });
    }

    String get(String key) {
        return redisClient.get(key).toString();
    }

    void set(String key, String value) {
        redisClient.set((Arrays.asList(key, value)));
    }
   
}
