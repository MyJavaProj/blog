package com.blog.common;

import java.util.ResourceBundle;

public class ResourceUtil {
	
	private static final String OWN_CONFIG_FILE_NAME = "config";
    
    public synchronized static String getPropertyValue(String property){
        ResourceBundle properties = ResourceBundle.getBundle(OWN_CONFIG_FILE_NAME);
        return properties.getString(property);
    }
}
