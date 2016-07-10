/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.bestpay.bpbp.signin.base;
import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 指定测试用例的运行器 这里是指定了Junit4
 *
 * @author gaoxiang
 * @version Id: BaseSpringTest.java, v 0.1 2016/1/18 15:24 gaoxiang Exp $$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-extra.xml")
public abstract class BaseSpringTest extends TestCase {
}
