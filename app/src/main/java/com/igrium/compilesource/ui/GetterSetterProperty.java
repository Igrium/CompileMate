package com.igrium.compilesource.ui;

import javafx.beans.property.ObjectPropertyBase;

public class GetterSetterProperty<T, O> extends ObjectPropertyBase<T> {

    private O bean;

    @Override
    public O getBean() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}
