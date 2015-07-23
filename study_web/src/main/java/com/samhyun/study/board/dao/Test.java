package com.samhyun.study.board.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.support.AbstractMarshaller;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;

public class Test extends AbstractMarshaller {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void marshalDomNode(Object graph, Node node)
			throws XmlMappingException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void marshalXmlEventWriter(Object graph,
			XMLEventWriter eventWriter) throws XmlMappingException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void marshalXmlStreamWriter(Object graph,
			XMLStreamWriter streamWriter) throws XmlMappingException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void marshalSaxHandlers(Object graph,
			ContentHandler contentHandler, LexicalHandler lexicalHandler)
			throws XmlMappingException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void marshalOutputStream(Object graph, OutputStream outputStream)
			throws XmlMappingException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void marshalWriter(Object graph, Writer writer)
			throws XmlMappingException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object unmarshalDomNode(Node node) throws XmlMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object unmarshalXmlEventReader(XMLEventReader eventReader)
			throws XmlMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object unmarshalXmlStreamReader(XMLStreamReader streamReader)
			throws XmlMappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object unmarshalSaxReader(XMLReader xmlReader,
			InputSource inputSource) throws XmlMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object unmarshalInputStream(InputStream inputStream)
			throws XmlMappingException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object unmarshalReader(Reader reader) throws XmlMappingException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
