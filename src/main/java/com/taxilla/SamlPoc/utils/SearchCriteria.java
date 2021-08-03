package com.taxilla.SamlPoc.utils;

public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public SearchOperation getOperation() {
        return operation;
    }
    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
}
