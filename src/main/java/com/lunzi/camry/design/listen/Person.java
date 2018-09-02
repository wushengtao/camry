package com.lunzi.camry.design.listen;

/**
 * Created by lunzi on 2018/7/16 下午8:04
 */
public class Person {
    private String name;
    private int age;
    private boolean sex;

    private Person(Builder builder){
        this.name=builder.name;
        this.age=builder.age;
        this.sex=builder.sex;
    }

    public static class Builder{
        private String name;
        private int age;
        private boolean sex;

        private Builder(String name,int age){
            this.name=name;
            this.age=age;
        }
        public Builder setSex(boolean sex){
            this.sex=sex;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }
    public static void main(String args[]){
        Person person=new Builder("mayan",1). setSex(false).build();
    }
}
