package com.intelliware.qmk.mapper;

import java.util.Arrays;

/**
 * Created by jr on 2016-10-27.
 */
public class MappedRow {


    public static final String CURL_BRACKET_LEFT = "{";
    public static final String CURL_BRACKET_RIGHT = "}";

    private String[] mappedKeys = new String[KeyMapConstants.MAX_COLUMNS];

    public MappedRow() {
        Arrays.fill(mappedKeys, KeyMapper.KC_TRNS);
    }

    public void populateKeys(String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            mappedKeys[i] = KeyMapper.getInstance().mapKey(keys[i]);
        }
    }

    public String generateMappedString() {

        StringBuilder strBuld = new StringBuilder(CURL_BRACKET_LEFT);

        for (int j = 0; j < mappedKeys.length; j++) {
            if (j != 0) {
                strBuld.append(", ");
            }
            strBuld.append(mappedKeys[j]);

        }

        return strBuld.toString();
    }
}
