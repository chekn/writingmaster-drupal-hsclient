package com.yicai.medialab.writingmaster.drupalhs.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.yicai.medialab.writingmaster.drupalhs.client.NDDrupalClient;
import com.yicai.medialab.writingmaster.drupalhs.client.NSIDrupalClient;
import com.yicai.medialab.writingmaster.drupalhs.model.Auth;
import com.yicai.medialab.writingmaster.drupalhs.model.NewsDraft;
import com.yicai.medialab.writingmaster.drupalhs.model.NewsSourceItem;
import com.yicai.medialab.writingmaster.drupalhs.util.PropsUtil;

public class WMResultImporter {
	private static final Properties drupalProps = PropsUtil.getMergedClassPathProps("drupal.properties");

	public static void importSourcesAndDrafts(String username, String password, List<NewsSourceItem> sources,
			List<NewsDraft> drafts) {
		NSIDrupalClient nsiDrupalClient = new NSIDrupalClient();
		List<String> originUrlList = new ArrayList<String>();
		for (NewsSourceItem item : sources) {
			originUrlList.add(item.getOrigin_url());
		}
		NDDrupalClient ndDrupalClient = new NDDrupalClient();
		for (NewsDraft draft : drafts) {
			Auth auth = nsiDrupalClient.login(drupalProps.getProperty("drupal.login.url"), username, password);
			draft.setSource_items_url(originUrlList);
			ndDrupalClient.insertOneNewsDraft(auth, draft);
		}
	}
}
