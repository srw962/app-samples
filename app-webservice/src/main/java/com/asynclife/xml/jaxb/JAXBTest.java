package com.asynclife.xml.jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.mycompany.schemas.LoanAcctReq;

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
	
	@Test
	public void testESB() throws Exception {
		LoanAcctReq req = new LoanAcctReq();
		req.setTxCode("001");
		req.setDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		req.setTime(new SimpleDateFormat("HHmmss").format(new Date()));
		req.setName("张三");
		req.setAge(20);
		
		
		StringWriter sw = new StringWriter(1024);
		
		JAXBContext ctx = JAXBContext.newInstance(LoanAcctReq.class);
		Marshaller marshaller = ctx.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "GB18030");
		marshaller.marshal(req, sw);
		sw.flush();
		
		String xml = sw.toString();
		xml = xml.replaceFirst(" standalone=\"yes\"", "");
		xml = xml.replaceAll("LoanAcctReq", "MSG");
		
		System.out.println(xml);
	}
}
