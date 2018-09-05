package com.oner.demo.seajug.hazelcast.scalewithhazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.oner.demo.seajug.hazelcast.scalewithhazelcast.hz.MyMapListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@SpringBootApplication
@EnableCaching
@EnableHazelcastHttpSession
public class ScaleWithHazelcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaleWithHazelcastApplication.class, args);
    }


    @Bean
    @Profile("hz-embedded")
    HazelcastInstance member(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    @Profile("hz-embedded")
    Config config() {
        Config config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true).addMember("127.0.0.1");
        config.getManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8080/hazelcast-mancenter");

        config.setInstanceName("my-hz-instance")
                .getMapConfig("sponsors")
                                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                                .setEvictionPolicy(EvictionPolicy.LRU)
                                .setTimeToLiveSeconds(2000);

        config.getMapConfig("fib").addEntryListenerConfig(new EntryListenerConfig(new MyMapListener(), true, true));

        //For Spring Session Hazelcast
        config.getMapConfig("spring:session:sessions")
                .addMapAttributeConfig(new MapAttributeConfig()
                        .setExtractor("org.springframework.session.hazelcast.PrincipalNameExtractor").setName("principalName"))
                .addMapIndexConfig(new MapIndexConfig().setAttribute("principalName"));

        return config;
    }

    @Bean
    @Profile("hz-client")
    HazelcastInstance client() {
        return HazelcastClient.newHazelcastClient();
    }
}
