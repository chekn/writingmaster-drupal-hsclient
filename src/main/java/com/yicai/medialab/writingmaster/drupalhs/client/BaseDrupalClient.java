package com.yicai.medialab.writingmaster.drupalhs.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicai.medialab.writingmaster.drupalhs.model.Auth;
import com.yicai.medialab.writingmaster.drupalhs.model.Taxonomy;

import jodd.http.Cookie;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class BaseDrupalClient {
	protected static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	// http://121.40.108.158/testing7/rest/user/login.json
	public Auth login(String loginJsonUrl, String username, String password) {
		HttpResponse response = HttpRequest.post(loginJsonUrl).form("username", username).form("password", password)
				.send();
		System.out.println(response);
		try {
			Auth auth = objectMapper.readValue(response.bodyText(), Auth.class);
			return auth;
		} catch (IOException e) {
			System.out.println(response);
			throw new RuntimeException(e);
		}
	}

	// 通过vid，获取该vid下的所有术语
	// http://121.40.108.158/mlrepo/rest/taxonomy_vocabulary/getTree.json
	@SuppressWarnings("unchecked")
	public List<Taxonomy> getTaxonomiesByVid(String jsonUrl, Auth auth, String vid) {
		HttpResponse response = HttpRequest.post(jsonUrl).header("X-CSRF-Token", auth.getToken())
				.cookies(new Cookie(auth.getSession_name(), auth.getSessid())).form("vid", vid).send();
		List<Taxonomy> taxonomies = new ArrayList<Taxonomy>();
		try {
			List<Map<String, Object>> list = objectMapper.readValue(response.bodyText(), List.class);
			for (Map<String, Object> map : list) {
				taxonomies.add(
						new Taxonomy(map.get("vid").toString(), map.get("tid").toString(), map.get("name").toString()));
			}
			return taxonomies;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> insertOneDrupalMap(String jsonUrl, Auth auth, Map<String, Object> drupalMap) {
		String bodyJson = null;
		try {
			bodyJson = objectMapper.writeValueAsString(drupalMap);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		String bodyTxt;
		try {
			bodyTxt = new String(bodyJson.getBytes("utf-8"), "ISO-8859-1");
			// System.out.println(bodyTxt);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		HttpResponse response = HttpRequest.post(jsonUrl).contentType("application/json")
				.header("X-CSRF-Token", auth.getToken()).cookies(new Cookie(auth.getSession_name(), auth.getSessid()))
				.body(bodyTxt).send();
		try {
			Map<String, Object> respMap = objectMapper.readValue(response.bodyText(), Map.class);
			respMap.put("status", response.statusCode());
			return respMap;
		} catch (IOException e) {
			System.err.println(response);
			e.printStackTrace();
			return null;
		}
	}
}
