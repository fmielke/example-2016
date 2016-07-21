package de.htwberlin.mae.model;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorInformation implements Serializable {
	//https://tools.ietf.org/html/draft-nottingham-http-problem-06#section-3
	
	/**
	 * 
	 */
	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	private String timestamp;
	private HttpStatus status; //status code
	private String type; //error type URI
	private String title; //short title
	private String instance; //exception
	private String detail; // detailed information
	
	//private List<String> errors; //exception
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	public ErrorInformation(String timestamp, String type, String title, HttpStatus status, String detail, String instance) {
		super();
		this.type = type;
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.instance = instance;
		this.timestamp = timestamp;
	}
}
