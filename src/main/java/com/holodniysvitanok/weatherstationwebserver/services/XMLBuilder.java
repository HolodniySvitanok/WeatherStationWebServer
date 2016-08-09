package com.holodniysvitanok.weatherstationwebserver.services;

import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.holodniysvitanok.weatherstationwebserver.services.chart.Pointers;

public class XMLBuilder {
	
	
	public static void sendXML(Pointers pointers, OutputStream outstream) {
		try {
			JAXBContext jaxbContex = JAXBContext.newInstance(Pointers.class);
			Marshaller marshaller = jaxbContex.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(pointers, outstream);
		} catch (JAXBException ex) {
			ex.printStackTrace();
			System.out.println("проблемка");
		}
	}

}
