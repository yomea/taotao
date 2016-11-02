package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	private Logger logger = LoggerFactory.getLogger(Test.class);

	@org.junit.Test
	public void test() {
		logger.debug("hello");
		try {
			int a = 1 / 0;
		} catch (Exception e) {
			logger.debug("算数错误", e);
		}
		logger.info("这只是个测试{}", "^_^");
	}

}
