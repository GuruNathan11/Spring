package com.MHC.Project.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dropdown")
public class down {
	
	private String id;
	private String dropdown;
	private List[] list;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDropdown() {
		return dropdown;
	}
	public void setDropdown(String dropdown) {
		this.dropdown = dropdown;
	}
	public List[] getList() {
		return list;
	}
	public void setList(List[] list) {
		this.list = list;
	}



	public static class List {
	    private String id;
	    private String value;
	    private String type;
	    
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public String getValue() {
	        return value;
	    }
	    public void setValue(String value) {
	        this.value = value;
	    }
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}	 
	}
}