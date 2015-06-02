package test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import HTMLHelper.UserHelper;


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
		assertFalse(UserHelper.checkName(""));
		assertTrue(UserHelper.checkName(""));
	}
	
	@Test
	public void testCheckMail()
	{
		assertFalse(UserHelper.checkUserMail(""));
		assertTrue(UserHelper.checkUserMail(""));
	}
	
	@Test
	public void testCheckTelephoneNumbers()
	{
		assertFalse(UserHelper.checkPhonenNumber(""));
		assertTrue(UserHelper.checkPhonenNumber(""));
	}
		
}
