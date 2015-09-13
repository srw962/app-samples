package com.asynclife.xml.jaxb;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class JAXBTest {
	
	@Test
	public void testBean2XML() throws Exception {
		JAXBContext ctx = JAXBContext.newInstance(User.class);
		Marshaller marshaller = ctx.createMarshaller();
		
		User user = new User();
		user.setId(1L);
		user.setName("abc");
		user.setSex("man");
		user.setAge(12);
		
		marshaller.marshal(user, System.out);
	}
	
	@Test
	public void testXML2Bean() throws Exception {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user><age>12</age><id>1</id><name>abc</name><sex>man</sex></user>";
		JAXBContext ctx = JAXBContext.newInstance(User.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		Object obj = unmarshaller.unmarshal(new StringReader(xml));
		User user = (User) obj;
		System.out.println(user.getName()+","+user.getAge());
		
	}
}
