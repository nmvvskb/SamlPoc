package com.taxilla.SamlPoc.entity;

public class GivenIdp {
    private String name;
    private String selectedIDPType;
    private String errorMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSelectedIDPType() {
        return selectedIDPType;
    }

    public void setSelectedIDPType(String selectedIDPType) {
        this.selectedIDPType = selectedIDPType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
