package com.example.sony.ecommerce.Database;

import android.provider.BaseColumns;

/**
 * Created by SONY on 7/8/2016.
 */
public class DatabaseSchema {

    public static abstract class CARTTABLE implements BaseColumns {
        public static final String TABLE_NAME = "CART";
        public static final String COLUMN_NAME_ENTRY_ID = "ROW_ID";
        public static final String COLUMN_NAME_USER_NAME = "USERNAME";
        public static final String COLUMN_NAME_ITEM_ID = "ITEM_ID";
        public static final String COLUMN_NAME_ITEM_NAME = "ITEM_NAME";
        public static final String COLUMN_NAME_ITEM_IMAGE="ITEM_PHOTO";
        public static final String COLUMN_NAME_ITEM_PRICE = "PRICE";
        public static final String COLUMN_NAME_QUANTITIY = "QUANTITY";
        public static final String COLUMN_NAME_TOTAL = "TOTAL";
        public static final String COMMA = ",";
        public static final String SPACE=" ";
        public static final String TEXT_TYPE = "TEXT";
        public static final String INT_TYPE = "INTEGER";
        public static final String NULL = "NULL";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY" + COMMA + SPACE + COLUMN_NAME_USER_NAME + SPACE+
                TEXT_TYPE + COMMA + SPACE + COLUMN_NAME_ITEM_ID + SPACE+ INT_TYPE + COMMA + SPACE+ COLUMN_NAME_ITEM_NAME + SPACE+ TEXT_TYPE +
                COMMA +SPACE+ COLUMN_NAME_ITEM_IMAGE + SPACE + TEXT_TYPE+ COMMA +SPACE+COLUMN_NAME_ITEM_PRICE +SPACE+ INT_TYPE + COMMA
                + SPACE + COLUMN_NAME_QUANTITIY + SPACE + INT_TYPE + COMMA+SPACE+ COLUMN_NAME_TOTAL+ SPACE +INT_TYPE+" )";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }

}
