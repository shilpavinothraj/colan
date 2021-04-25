package com.example.colan_infotech.database;

import java.util.List;

public class SqliteHelper {

    public static final String TABLE_NAME = "auth";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN__PRODUCT_ID = "productid";
    public static final String COLUMN__NAME = "name";
    public static final String COLUMN__COMMENT= "comment";
    public static final String COLUMN__TITLE = "title";



    private List<Integer> id;
    private List<String> productid;

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    private List<String> name;
    private List<String> comment;
    private List<String> title;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN__PRODUCT_ID + " TEXT,"
                    + COLUMN__NAME + " TEXT,"
                    + COLUMN__COMMENT + " TEXT,"
                    + COLUMN__TITLE + " TEXT"
                    + ")";

    public SqliteHelper() {
    }

    public SqliteHelper(List<Integer> id, List<String> productid,List<String> name,List<String> comment,List<String> title) {
        this.id = id;
        this.productid = productid;
        this.name=name;
        this.comment=comment;
        this.title=title;
    }





    public List<String> getProductid() {
        return productid;
    }



    public void setProductid(List<String> productid) {
        this.productid = productid;
    }
    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

}
