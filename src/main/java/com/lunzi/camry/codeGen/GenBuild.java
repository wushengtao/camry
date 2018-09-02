package com.lunzi.camry.codeGen;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lunzi on 2018/8/7 下午8:45
 */
public class GenBuild {
    private String suffix;
    private String prefix;
    private String className;
    private Map<String, String> fieldMap;
    private static StringBuilder stringBuilder = new StringBuilder();
    private static final String OB="{";
    private static final String CB="}";
    private static final String SHIFT="\n";
    private static final String SPACE=" ";
    private static final String SEMICOLON=";";
    private static final String CLASS="class";
    private static final String PRIVATE="private";
    private static final String PUBLIC="public";

    public GenBuild(Builder builder) {
        this.className = builder.className;
        this.fieldMap = builder.fieldMap;
        this.suffix = builder.suffix;
        this.prefix = builder.prefix;
    }

    public static class Builder {
        private String className;
        private String suffix;
        private String prefix;
        private Map<String, String> fieldMap=new LinkedHashMap<>(16);

        public Builder(String className) {
            this.className = className;
        }

        public Builder setField(String fieldType, String fieldName) {
            fieldMap.put(fieldType, fieldName);
            return this;
        }


        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public GenBuild build() {
            return new GenBuild(this);
        }
    }

    public GenBuild gen() {
        stringBuilder.append(PUBLIC).append(SPACE)
                .append(CLASS).append(SPACE)
                .append(className)
                .append(OB)
                .append(SHIFT);
                //.append(CB);
        //field
        for(Map.Entry<String,String> entry:fieldMap.entrySet()){
            String classType=entry.getKey();
            String className=entry.getValue();
            stringBuilder.append(PRIVATE).append(SPACE)
                    .append(classType).append(SPACE)
                    .append(className).append(SEMICOLON).append(SHIFT);
        }

        stringBuilder.append(CB);
        return null;
    }

    public void saveFile() {

    }

    public static void main(String args[]) {
        GenBuild genBuild = new Builder("Student")
                .setField("String","name")
                .setField("int","age")
                .build();
        genBuild.gen();


    }

}
