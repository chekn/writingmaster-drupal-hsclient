package com.yicai.medialab.writingmaster.drupalhs.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yicai.medialab.writingmaster.drupalhs.model.Auth;
import com.yicai.medialab.writingmaster.drupalhs.model.NewsSourceItem;
import com.yicai.medialab.writingmaster.drupalhs.util.PropsUtil;

import jodd.http.Cookie;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

public class NSIDrupalClient extends BaseDrupalClient {
	private static final Properties templateProps = PropsUtil.getMergedClassPathProps("newstemplate.properties");
	private static final Properties drupalProps = PropsUtil.getMergedClassPathProps("drupal.properties");
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public NSIDrupalClient() {
	}

	public Map<String, Object> insertOneNewsSourceItem(Auth auth, NewsSourceItem newsSourceItem) {
		Map<String, Object> drupalMap = newsSourceItem.convertToEntityMap(templateProps);
		drupalMap.put("uid", auth.getUser().getUid());
		return insertOneDrupalMap(drupalProps.getProperty("drupal.entity.node.url"), auth, drupalMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map<String, Object>> getOneNewsSourceItemByMongoId(Auth auth, String mongoId) {
		HttpResponse response = HttpRequest
				.get(drupalProps.getProperty("drupal.entity.node.url") + "?fields=nid&parameters[field_mongo_id]="
						+ mongoId)
				.header("X-CSRF-Token", auth.getToken()).cookies(new Cookie(auth.getSession_name(), auth.getSessid()))
				.send();
		try {
			List list = objectMapper.readValue(response.bodyText(), List.class);
			if (list.get(0) instanceof String) {
				list = new ArrayList<Map<String, Object>>();
			}
			return list;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
