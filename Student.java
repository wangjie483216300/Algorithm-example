package com.company.proxy.InterfaceTest;

public class Student implements Stu{
    public String name;
    public String year;

    public Student(String name, String year) {
        this.name = name;
        this.year = year;
    }
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                '}';
    }



    public void sleep(){
        System.out.println("睡觉,睡觉");
    }

    @Override
    public void eat() {
        System.out.println("吃饭");
    }


}
