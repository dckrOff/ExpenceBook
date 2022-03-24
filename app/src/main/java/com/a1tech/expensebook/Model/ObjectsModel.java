package com.a1tech.expensebook.Model;

public class ObjectsModel {

    private String objectName;
    private String objectPrice;

    public ObjectsModel(String objectName, String objectPrice) {
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
