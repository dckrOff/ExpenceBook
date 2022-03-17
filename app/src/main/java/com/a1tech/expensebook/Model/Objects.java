package com.a1tech.expensebook.Model;

public class Objects {

    private String objectName;
    private String objectPrice;

    public Objects(String objectName, String objectPrice) {
        this.objectName = objectName;
        this.objectPrice = objectPrice;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectPrice() {
        return objectPrice;
    }

    public void setObjectPrice(String objectPrice) {
        this.objectPrice = objectPrice;
    }
}
