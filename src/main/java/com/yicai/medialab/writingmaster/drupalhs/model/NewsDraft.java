package com.yicai.medialab.writingmaster.drupalhs.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class NewsDraft {
	public static final String TYPE = "news_draft";
	private String title;
	private String content;
	private String format;
	private String corpus_tid;
	private List<String> source_items_url;// 这得是nid
	// private List<String> source_item_title_list;
	private String news_terminal;
	private String news_template_url;
	private String news_template;
	private String stock_code;
	private Date pub_date;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getSource_items_url() {
		return source_items_url;
	}

	public void setSource_items_url(List<String> source_items_url) {
		this.source_items_url = source_items_url;
	}

	public String getNews_terminal() {
		return news_terminal;
	}

	public void setNews_terminal(String news_terminal) {
		this.news_terminal = news_terminal;
	}

	public String getNews_template_url() {
		return news_template_url;
	}

	public void setNews_template_url(String news_template_url) {
		this.news_template_url = news_template_url;
	}

	public String getNews_template() {
		return news_template;
	}

	public void setNews_template(String news_template) {
		this.news_template = news_template;
	}

	public Date getPub_date() {
		return pub_date;
	}

	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCorpus_tid() {
		return corpus_tid;
	}

	public void setCorpus_tid(String corpus_tid) {
		this.corpus_tid = corpus_tid;
	}

	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}

	public Map<String, Object> convertToEntityMap(Properties templateProps, Properties terminalProps) {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("type", TYPE);
		map.put("title", this.title);
		map.put("field_pub_date", Collections.singletonMap("und",
				Arrays.asList(Collections.singletonMap("value", this.pub_date.getTime() / 1000))));
		HashMap<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("value", this.content);
		contentMap.put("format", this.format);
		map.put("body", Collections.singletonMap("und", Arrays.asList(contentMap)));
		map.put("field_news_template", Collections.singletonMap("und",
				Arrays.asList(Collections.singletonMap("tid", templateProps.get(this.news_template)))));
		map.put("field_news_terminal", Collections.singletonMap("und",
				Arrays.asList(Collections.singletonMap("tid", terminalProps.get(this.news_terminal)))));
		map.put("field_corpus",
				Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("tid", this.corpus_tid))));
		map.put("field_news_template_url", Collections.singletonMap("und",
				Arrays.asList(Collections.singletonMap("value", this.news_template_url))));
		List<Map<String, Object>> field_source_items_url = new ArrayList<Map<String, Object>>();
		if (source_items_url != null) {
			for (String url : source_items_url) {
				field_source_items_url.add(Collections.singletonMap("value", url));
			}
		}
		map.put("field_source_items_url", Collections.singletonMap("und", field_source_items_url));
		if (stock_code != null) {
			map.put("field_stock_code",
					Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("value", this.stock_code))));
		}
		return map;
	}

	@Override
	public String toString() {
		return "NewsDraft [title=" + title + ", content=" + content + ", format=" + format + ", corpus_tid="
				+ corpus_tid + ", source_items_url=" + source_items_url + ", news_terminal=" + news_terminal
				+ ", news_template_url=" + news_template_url + ", news_template=" + news_template + ", pub_date="
				+ pub_date + "]";
	}
}
