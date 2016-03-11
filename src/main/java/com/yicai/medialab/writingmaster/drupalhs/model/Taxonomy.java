package com.yicai.medialab.writingmaster.drupalhs.model;

public class Taxonomy {
	private String vid;
	private String tid;
	private String name;

	public Taxonomy() {

	}

	public Taxonomy(String vid, String tid, String name) {
		this.vid = vid;
		this.tid = tid;
		this.name = name;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Taxonomy [vid=" + vid + ", tid=" + tid + ", name=" + name + "]";
	}

}
