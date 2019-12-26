package com.bigdata2019.mysite.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String password;
	private String contents;
	private String time;
	
	public GuestbookVo() {
	}
	
	public GuestbookVo(String name, String password, String contents) {
		this.name = name;
		this.password = password;
		this.contents = contents;
	}
	
	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String gettime() {
		return time;
	}
	public void settime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", contents=" + contents
				+ ", time=" + time + "]";
	}}