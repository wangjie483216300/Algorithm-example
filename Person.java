package com.company.Reflect;

public class Person {
    private String name = "王杰";
    private String year = "21";
    public String a;
    protected String b;
    String c;
    private String d;
    public Person(String name,String year){
        this.name = name;
        this.year = year;
    }
    public Person(String name,String year,String A,String B,String C,String d){
        this.name = name;
        this.year = year;
        this.a = A;
        this.b = B;
        this.c = C;
        this.d = d;
    }
    public Person(){}
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
        return "Person{" +
                "name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }


    public void aaa(){
        System.out.println("public void aaa");
    }
    public int bbb(int a){
        System.out.println("public int bbb   "+a);
        return a;
    }
    private void ccc(){
        System.out.println("private void ccc");
    }








}
