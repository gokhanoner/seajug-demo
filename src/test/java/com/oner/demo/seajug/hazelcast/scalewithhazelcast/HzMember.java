package com.oner.demo.seajug.hazelcast.scalewithhazelcast;

import com.hazelcast.core.Hazelcast;

import java.io.FileNotFoundException;

public class HzMember {

	public static void main(String[] args) throws FileNotFoundException {
		Hazelcast.newHazelcastInstance();
	}

}
