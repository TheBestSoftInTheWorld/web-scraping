package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;

class URLContent {

	String url;
	String text;
	int type;

	public URLContent() {

	}

	URLContent(String url, String text, int type) {
		super();
		this.url = url;
		this.text = text;
		this.type = type;
	}

	String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}

	String getText() {
		return text;
	}

	void setText(String text) {
		this.text = text;
	}

	int getType() {
		return type;
	}

	void setType(int type) {
		this.type = type;
	}

	List<URLContent> generateList(String searchUrl, DomNodeList<DomElement> list) {

		List<URLContent> url = new ArrayList<>();

		list.forEach((temp) -> {
			String href = temp.getAttribute("href");
			String text = temp.asText();
			if (href.startsWith("http") || href.startsWith("www")) {

				if (href.startsWith(searchUrl)) {

					url.add(new URLContent(href, text, 1));

				} else {
					url.add(new URLContent(href, text, 2));
				}

			} else if (text.isEmpty() || text == null) {

			} else {
				url.add(new URLContent(href, text, 3));
			}
		});

		return url;
	}

}
