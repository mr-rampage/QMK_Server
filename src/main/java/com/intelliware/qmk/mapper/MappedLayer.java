package com.intelliware.qmk.mapper;

/**
 * Created by jr on 2016-10-27.
 */
public class MappedLayer {

    public static final String CURL_BRACKET_LEFT = "{";
    public static final String CURL_BRACKET_RIGHT = "}";

    private MappedRow[] mappedRows = new MappedRow[KeyMapConstants.MAX_ROWS];

    public MappedLayer() {

        for ( int i = 0; i < mappedRows.length; i++) {
            mappedRows[i] = new MappedRow();
        }
    }


    public void populateKeys(String[][] keys) {
        for (int i = 0; i < keys.length; i++) {
            mappedRows[i].populateKeys(keys[i]);
        }
    }

    public String generateMappedString() {
        StringBuilder strBuid = new StringBuilder(CURL_BRACKET_LEFT + "\n");

        for (int i = 0; i < mappedRows.length; i++) {
            if (i != 0) {
                strBuid.append(", \n");
            }


            strBuid.append(mappedRows[i].generateMappedString());

            strBuid.append(CURL_BRACKET_RIGHT);

        }
        strBuid.append("\n" + CURL_BRACKET_RIGHT);

        return strBuid.toString();
    }
}
