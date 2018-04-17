package com.oner.demo.seajug.hazelcast.scalewithhazelcast.hz;

import javax.cache.configuration.Factory;

public class MyCacheListenerFactory implements Factory<MyCacheListener> {

    @Override
    public MyCacheListener create() {
        return new MyCacheListener();
    }
}
