package com.example.sameershekhar.hebecollins.activities.models;

import java.io.Serializable;

/**
 * Created by sameershekhar on 17-Aug-17.
 */

public class UserAlbum implements Serializable {
    private int client_id;
    private String image_name;
    private String time;

    public UserAlbum(int client_id, String image_name, String time) {
        this.client_id = client_id;
        this.image_name = image_name;
        this.time = time;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
