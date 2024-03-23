package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Index-Form")
public class IndexForm {

	private String id;
	private String title;
	private Content[] content;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Content[] getContent() {
		return content;
	}
	public void setContent(Content[] content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
