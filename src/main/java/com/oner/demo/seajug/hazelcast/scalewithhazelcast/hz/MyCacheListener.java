package com.oner.demo.seajug.hazelcast.scalewithhazelcast.hz;

import lombok.extern.slf4j.Slf4j;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

@Slf4j
public class MyCacheListener<K, V> implements CacheEntryCreatedListener<K, V> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> iterable) throws CacheEntryListenerException {
        iterable.iterator().forEachRemaining(cacheEntryEvent -> {
            log.info("Cache entry added {} => {}", cacheEntryEvent.getKey(), cacheEntryEvent.getValue());
        });
    }
}
