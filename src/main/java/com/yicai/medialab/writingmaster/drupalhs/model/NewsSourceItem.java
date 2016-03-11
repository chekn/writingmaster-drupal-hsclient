package com.yicai.medialab.writingmaster.drupalhs.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class NewsSourceItem {
	public static final String TYPE = "news_source_item";
	private String mongo_id;
	private String title;
	private String origin_url;
	private Date pub_date;
	private String data_type;
	private String collection;
	private String content;
	private String format;
	// private List<String> news_template_list;
	// private List<String> news_template_url_list;

	public String getMongo_id() {
		return mongo_id;
	}

	public void setMongo_id(String mongo_id) {
		this.mongo_id = mongo_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrigin_url() {
		return origin_url;
	}

	public void setOrigin_url(String origin_url) {
		this.origin_url = origin_url;
	}

	public Date getPub_date() {
		return pub_date;
	}

	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// public List<String> getNews_template_list() {
	// return news_template_list;
	// }

	// public void setNews_template_list(List<String> news_template_list) {
	// this.news_template_list = news_template_list;
	// }

	// public List<String> getNews_template_url_list() {
	// return news_template_url_list;
	// }

	// public void setNews_template_url_list(List<String>
	// news_template_url_list) {
	// this.news_template_url_list = news_template_url_list;
	// }

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Map<String, Object> convertToEntityMap(Properties templateProps) {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("type", TYPE);
		map.put("title", this.title);
		map.put("field_mongo_id",
				Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("value", this.mongo_id))));
		map.put("field_origin_url",
				Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("value", this.origin_url))));
		map.put("field_pub_date", Collections.singletonMap("und",
				Arrays.asList(Collections.singletonMap("value", this.pub_date.getTime() / 1000))));
		map.put("field_data_type",
				Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("value", this.data_type))));
		map.put("field_collection",
				Collections.singletonMap("und", Arrays.asList(Collections.singletonMap("value", this.collection))));
		HashMap<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("value", this.content);
		contentMap.put("format", this.format);
		map.put("field_content", Collections.singletonMap("und", Arrays.asList(contentMap)));
		// List<Map<String, Object>> template_list = new ArrayList<Map<String,
		// Object>>();
		// if (this.news_template_list != null) {
		// for (String template : news_template_list) {
		// template_list.add(Collections.singletonMap("tid",
		// templateProps.getProperty(template)));
		// }
		// }
		// map.put("field_news_template", Collections.singletonMap("und",
		// template_list));
		// List<Map<String, Object>> template_url_list = new
		// ArrayList<Map<String, Object>>();
		// if (this.news_template_url_list != null) {
		// for (String template_url : news_template_url_list) {
		// template_url_list.add(Collections.singletonMap("value",
		// template_url));
		// }
		// }
		// map.put("field_news_template_url", Collections.singletonMap("und",
		// template_url_list));
		return map;
	}

	@Override
	public String toString() {
		return "NewsSourceItem [mongo_id=" + mongo_id + ", title=" + title + ", origin_url=" + origin_url
				+ ", pub_date=" + pub_date + ", data_type=" + data_type + ", collection=" + collection + ", content="
				+ content + ", format=" + format + "]";
	}

}
