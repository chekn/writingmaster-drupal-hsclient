package com.yicai.medialab.writingmaster.drupalhs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {
	public static Properties getMergedClassPathProps(String... propsClassPath) {
		ClassLoader classLoader = PropsUtil.class.getClassLoader();
		Properties properties = new Properties();
		for (int i = 0; i < propsClassPath.length; i++) {
			String path = propsClassPath[i];
			InputStream inputStream = classLoader.getResourceAsStream(path);
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return properties;
	}
}
