package com.smart.aspectj.fun;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
/**
 * 说明：需要测试某个切点函数时，取消相应的注解就可以了。
 * @author 陈雄华
 *
 */
@Aspect
public class TestAspect implements Ordered{
	// 8.5.1
//	@AfterReturning("@annotation(com.smart.anno.NeedTest)")
//	public void needTestFun() {
//		System.out.println("afterReturning: needTestFun() executed!");
//	}

	// 8.5.2
//    @Before("execution(public * *(..))")
//	public void allPublicFun(){
//	    System.out.println("before: allPublicFun() executed!");
//	}
//
//    @AfterReturning("execution(* *To(..))")
//    public void allToFun(){
//    	System.out.println("after returning: allToFun() executed!");
//    }

//    @Before("execution(* com.smart.Waiter.*(..))")
//    public void allWaiterFun(){
//    	System.out.println("before: allWaiterFun() executed!");
//    }
//    @Before("execution(* com.smart.Waiter+.*(..))")
//    public void allChildClassFun(){
//    	System.out.println("before: allChildClassFun() executed!");
//    }

//	@Before("execution(* joke(String,int)))")
//	@Before("execution(* joke(String,*)))")
//	@Before("execution(* joke(String,..)))")
//	@Before("args(Object,*)")
//    public void jokeFun(){
//    	System.out.println("before: jokeFun() executed!");
//    }

	// 8.5.3
//	@Before("args(com.smart.Waiter)")
//	public void argWaiterFun() {
//		System.out.println("before: args is Waiter");
//	}
//    @AfterReturning("@annotation(com.smart.anno.NeedTest)")
//    public void atAnnotaionTest(){
//    	System.out.println("atAnnotaionTest() executed!");
//    }
//    @AfterReturning("args(String)")
//    public void argsTest(){
//    	System.out.println("argsTest() executed!");
//    }   
//    @AfterReturning("@args(Monitorable)")
//    public void atArgsTest(){
//    	System.out.println("atArgsTest() executed!");
//    }

	// 8.5.4
//    @Before("within(com.smart.NaiveWaiter)")
//	@Before("within(com.smart.*)")
//	@Before("target(com.smart.Waiter)")
//	@Before("this(com.smart.Waiter)")
//    public void withinTest(){
//    	System.out.println("before: withinTest() executed!");
//    }
//    @Before("@within(com.smart.aspectj.fun.Monitorable)")
//	public void atWithinTest() {
//		System.out.println("atWithinTest() executed!");
//	}

	// "this" vs "target" 引介增强中有区别
	@AfterReturning("this(com.smart.Seller)")
	public void thisTest(){
		System.out.println("after returning: thisTest() executed!");
	}
	@AfterReturning("target(com.smart.Seller)")
	public void targetTest(){
		System.out.println("after returning: targetTest() executed!");
	}
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
}
