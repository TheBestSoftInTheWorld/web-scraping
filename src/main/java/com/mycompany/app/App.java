package com.mycompany.app;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Hello world!
 *
 */
class App {
	final static String searchUrl = "http://a123456789z.com/";;

	public static void main(String[] args) {

		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try {

			HtmlPage page = client.getPage(searchUrl);

			System.out.println("title: " + page.getTitleText());
			DomNodeList<DomElement> ss = page.getElementsByTagName("a");

			URLContent u = new URLContent();
			List<URLContent> listOfURLContent = u.generateList(searchUrl, ss);

			generateXML(listOfURLContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void generateXML(List<URLContent> listOfURLContent) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("URL");
			doc.appendChild(rootElement);

			Iterator itr = listOfURLContent.iterator();

			int a = 0;
			int b = 0;
			int c = 0;
			while (itr.hasNext()) {

				URLContent element = (URLContent) itr.next();

				if (element.type == 1) {
					a++;

					Element innerURL = doc.createElement("innerURL");
					rootElement.appendChild(innerURL);

					Attr attr = doc.createAttribute("id");
					attr.setValue(a + "");
					innerURL.setAttributeNode(attr);

					Element url = doc.createElement("url");
					url.appendChild(doc.createTextNode(element.url));
					innerURL.appendChild(url);

					Element text = doc.createElement("text");
					text.appendChild(doc.createTextNode(element.text));
					innerURL.appendChild(text);

				} else if (element.type == 2) {

					b++;

					Element outerURL = doc.createElement("outerURL");
					rootElement.appendChild(outerURL);

					Attr attr = doc.createAttribute("id");
					attr.setValue(b + "");
					outerURL.setAttributeNode(attr);

					Element url = doc.createElement("url");
					url.appendChild(doc.createTextNode(element.url));
					outerURL.appendChild(url);

					Element text = doc.createElement("text");
					text.appendChild(doc.createTextNode(element.text));
					outerURL.appendChild(text);

				} else if (element.type == 3) {

					c++;

					Element inner = doc.createElement("inner");
					rootElement.appendChild(inner);

					Attr attr = doc.createAttribute("id");
					attr.setValue(c + "");
					inner.setAttributeNode(attr);

					Element url = doc.createElement("url");
					url.appendChild(doc.createTextNode(element.url));
					inner.appendChild(url);

					Element text = doc.createElement("text");
					text.appendChild(doc.createTextNode(element.text));
					inner.appendChild(text);

				}

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			// StreamResult result = new StreamResult(new File("C:/XML/file.xml"));
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

}
