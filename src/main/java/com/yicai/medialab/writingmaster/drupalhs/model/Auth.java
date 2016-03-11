package com.yicai.medialab.writingmaster.drupalhs.model;

public class Auth {
	private String sessid;
	private String session_name;
	private String token;
	private User user;

	public Auth() {
	}

	public Auth(String sessid, String session_name, String token) {
		this.sessid = sessid;
		this.session_name = session_name;
		this.token = token;
	}

	public String getSessid() {
		return sessid;
	}

	public void setSessid(String sessid) {
		this.sessid = sessid;
	}

	public String getSession_name() {
		return session_name;
	}

	public void setSession_name(String session_name) {
		this.session_name = session_name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Auth [sessid=" + sessid + ", session_name=" + session_name + ", token=" + token + ", user=" + user
				+ "]";
	}

}
