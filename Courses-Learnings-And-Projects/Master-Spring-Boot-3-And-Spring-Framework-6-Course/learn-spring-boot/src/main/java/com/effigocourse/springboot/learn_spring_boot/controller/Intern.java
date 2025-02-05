package com.effigocourse.springboot.learn_spring_boot.controller;

public class Intern {
    private long id;
    private String name;
    private String department;
    private int stipend;

    public Intern(long id, String name, String department, int stipend) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.stipend = stipend;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getStipend() {
        return stipend;
    }

    @Override
    public String toString() {
        return "Intern{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", stipend=" + stipend +
                '}';
    }
}
