package br.com.davidmachadosf.test_brasprev.model.enums;
public enum ValidationType {

    ANNONYMOUS ("Online");

    String value;

    ValidationType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}