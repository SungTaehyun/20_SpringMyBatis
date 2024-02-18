package com.feb.jdbc.dto;

public class EmailDto {//클래스명 변수에서 선언된 변수를 뭐라고 하나? 인스턴스변수라고도 하지만 다른 말로느?
	 private String from;
	  private String receiver;
	  private String text;
	  private String subject;
	  
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
