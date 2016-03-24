package com.asynclife.wx.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class JaxbHelper {
	
	public static String toXml(Object source, Class<?> cls) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(cls);
			Marshaller marshaller = jaxbContext.createMarshaller();

			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(CharacterEscapeHandler.class.getName(),
	                new CharacterEscapeHandler() {
	                    @Override
	                    public void escape(char[] ac, int i, int j, boolean flag,
	                            Writer writer) throws IOException {
	                        writer.write(ac, i, j);
	                    }
	                });
			StringWriter sw = new StringWriter();
			marshaller.marshal(source, sw);
			return sw.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(String xml, Class<T> cls) {

		T t = null;
		try {
			JAXBContext jaxb = JAXBContext.newInstance(cls);
			Unmarshaller u = jaxb.createUnmarshaller();

			t = (T) u.unmarshal(new StringReader(xml));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
