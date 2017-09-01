package com.saresource.aws.beans;

public class Category {
    private String catid;
    private String catgroup;
    private String catname;
    private String catdesc;

    public String toString() {
        return String.format("catid: %s; catgroup: %s; catname: %s; catdesc: %s",
                getCatid(), getCatgroup(), getCatname(), getCatdesc());
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatgroup() {
        return catgroup;
    }

    public void setCatgroup(String catgroup) {
        this.catgroup = catgroup;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }
}
