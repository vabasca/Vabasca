package com.vabasca.data.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

public class TodoRequest {

    @SerializedName("text")
    private String text;

    public TodoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
