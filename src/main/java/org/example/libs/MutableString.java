package org.example.libs;

public class MutableString {
    private String value;

    public MutableString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void update(String value) {
        this.value = value;
    }
}
