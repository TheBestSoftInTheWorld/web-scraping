package com.mycompany.app;

public class URLContent {

	String url;
	String text;
	int type;

	public URLContent(String url, String text, int type) {
		super();
		this.url = url;
		this.text = text;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
