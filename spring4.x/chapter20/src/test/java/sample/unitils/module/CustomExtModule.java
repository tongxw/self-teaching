package sample.unitils.module;

import java.lang.reflect.Method;
import java.util.Properties;
import org.unitils.core.Module;
import org.unitils.core.TestListener;

public class CustomExtModule implements Module {

	public TestListener getTestListener() {
		return new CustomExtListener();
	}

	protected class CustomExtListener extends TestListener {
		@Override
		public void beforeTestSetUp(Object testObject, Method testMethod) {
			System.out.println("custom ext module -> beforeTestSetUp!");
		}

		@Override
		public void afterCreateTestObject(Object testObject) {
			System.out.println("custom ext module -> afterCreateTestObject!");
		}

		@Override
		public void afterTestMethod(Object testObject, Method testMethod,
				Throwable testThrowable) {
			System.out.println("custom ext module -> afterTestMethod!");
		}

		@Override
		public void afterTestTearDown(Object testObject, Method testMethod) {
			System.out.println("custom ext module -> afterTestTearDown!");
		}

		@Override
		public void beforeTestClass(Class<?> testClass) {
			System.out.println("custom ext module -> beforeTestClass!");
		}

		@Override
		public void beforeTestMethod(Object testObject, Method testMethod) {
			System.out.println("custom ext module -> beforeTestMethod!");
		}
	}


	public void afterInit() {

	}


	public void init(Properties configuration) {

	}

}
