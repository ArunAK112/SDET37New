package assignment;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Test {
	
	@BeforeMethod
	public void test1()
	{
		System.out.println("BM");
	}
	
	@AfterMethod
	public void test2()
	{
		System.out.println("AM");
	}
	
	@BeforeMethod
	public void test3()
	{
		System.out.println("BM");
	}
	
	@BeforeMethod
	public void test4()
	{
		System.out.println("BM");
	}
	
	@AfterMethod
	public void test5()
	{
		System.out.println("AM");
	}
	
	@org.testng.annotations.Test
	public void test6()
	{
		System.out.println("Test");
	}

	@org.testng.annotations.Test
	public void test7()
	{
		System.out.println("Test");
	}
}
