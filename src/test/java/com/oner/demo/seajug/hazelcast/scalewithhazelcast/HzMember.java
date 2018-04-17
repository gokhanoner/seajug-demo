package com.oner.demo.seajug.hazelcast.scalewithhazelcast;

import com.hazelcast.core.Hazelcast;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class HzMember {

	public static void main(String[] args) {
		Hazelcast.newHazelcastInstance();
	}

}
