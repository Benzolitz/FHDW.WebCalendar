package test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import HTMLHelper.UserHTMLHelper;


public class UserHelperTest
{
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}
	
	@Test
	public void testCheckUserName()
	{
//		assertFalse(UserHTMLHelper.checkUserName("Name_5"));
//		assertFalse(UserHTMLHelper.checkUserName("N/ame_5"));
//		assertFalse(UserHTMLHelper.checkUserName(":54234"));
//		assertFalse(UserHTMLHelper.checkUserName("K/7name:"));
//		assertTrue(UserHTMLHelper.checkUserName("Name8"));
//		assertTrue(UserHTMLHelper.checkUserName("name8"));
//		assertTrue(UserHTMLHelper.checkUserName("name"));
//		assertTrue(UserHTMLHelper.checkUserName("123456"));
	}
	
	@Test
	public void testCheckPassword()
	{
//		assertFalse(UserHTMLHelper.checkUserPassword(""));
//		assertTrue(UserHTMLHelper.checkUserPassword(""));
	}
	
	@Test
	public void testCheckNames()
	{
		assertFalse(UserHTMLHelper.checkName(""));
		assertTrue(UserHTMLHelper.checkName(""));
	}
	
	@Test
	public void testCheckMail()
	{
		assertFalse(UserHTMLHelper.checkUserMail(""));
		assertTrue(UserHTMLHelper.checkUserMail(""));
	}
	
	@Test
	public void testCheckTelephoneNumbers()
	{
		assertFalse(UserHTMLHelper.checkPhonenNumber(""));
		assertTrue(UserHTMLHelper.checkPhonenNumber(""));
	}
		
}
