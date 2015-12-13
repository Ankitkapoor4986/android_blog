package com.example.com.myapplication.model;

/**
 * Created by ankit on 15/11/15.
 */
public class Person {
    private String name;
    private String attr;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", attr='" + attr + '\'' +
                '}';
    }

    public Person(String name, String attr) {
        this.name = name;
        this.attr = attr;
    }

    public Person() {
    }

    public String getName() {
        return name;

    }



    public void setName(String name) {
        this.name = name;
    }


    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getAttr() {

        return attr;
    }
}
