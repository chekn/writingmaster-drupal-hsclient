package com.yicai.medialab.writingmaster.drupalhs.client;

import java.util.Map;
import java.util.Properties;

import com.yicai.medialab.writingmaster.drupalhs.model.Auth;
import com.yicai.medialab.writingmaster.drupalhs.model.NewsDraft;
import com.yicai.medialab.writingmaster.drupalhs.util.PropsUtil;

public class NDDrupalClient extends BaseDrupalClient {
	private static final Properties templateProps = PropsUtil.getMergedClassPathProps("newstemplate.properties");
	private static final Properties terminalProps = PropsUtil.getMergedClassPathProps("newsterminal.properties");
	private static final Properties drupalProps = PropsUtil.getMergedClassPathProps("drupal.properties");

	public Map<String, Object> insertOneNewsDraft(Auth auth, NewsDraft newsDraft) {
		Map<String, Object> drupalMap = newsDraft.convertToEntityMap(templateProps, terminalProps);
		drupalMap.put("uid", auth.getUser().getUid());
		return insertOneDrupalMap(drupalProps.getProperty("drupal.entity.node.url"), auth, drupalMap);
	}
}
