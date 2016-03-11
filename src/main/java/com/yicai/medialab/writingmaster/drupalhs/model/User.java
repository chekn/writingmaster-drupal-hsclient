package com.yicai.medialab.writingmaster.drupalhs.model;

public class User {
	private String uid;
	private String name;
	private String mail;
	private User user;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", mail=" + mail + ", user=" + user + "]";
	}

}
