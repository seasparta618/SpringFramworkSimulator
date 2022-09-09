package com.leon.framework.beans;

/**
 * This class is for the property value under the bean tag
 * name: bean name
 * ref:
 * value:
 */
public class PropertyValue {
    private String name;
    private String ref;
    private String value;

    public PropertyValue() {
    }

    private PropertyValue(Builder build) {
        this.name = build.name;
        this.ref = build.ref;
        this.value = build.value;
    }

    public static final class Builder {
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder ref(String ref) {
            this.ref = ref;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public PropertyValue build() {
            return new PropertyValue(this);
        }

        private String name;
        private String ref;
        private String value;


    }

    public PropertyValue(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
