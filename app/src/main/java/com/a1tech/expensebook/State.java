package com.a1tech.expensebook;

public class State {

    private String name; // название
    private String capital;  // столица
    private String price; // цена

    public State(String name, String capital, String price) {
        this.name = name;
        this.capital = capital;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
