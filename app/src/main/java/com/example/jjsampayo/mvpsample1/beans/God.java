package com.example.jjsampayo.mvpsample1.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by
 *      jjsampayo on 23/03/2018.
 */

public class God implements Parcelable{

    private String name;

    private String phanteon;

    private String type;

    private String role;

    public static final Creator<God> CREATOR = new Creator<God>() {
        @Override
        public God createFromParcel(Parcel in) {
            return new God(in);
        }

        @Override
        public God[] newArray(int size) {
            return new God[size];
        }
    };

    public God() {
    }

    public God(String name, String phanteon, String type, String role) {
        this.name = name;
        this.phanteon = phanteon;
        this.type = type;
        this.role = role;
    }

    protected God(Parcel in) {
        name = in.readString();
        phanteon = in.readString();
        type = in.readString();
        role = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhanteon() {
        return phanteon;
    }

    public void setPhanteon(String phanteon) {
        this.phanteon = phanteon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phanteon);
        dest.writeString(type);
        dest.writeString(role);
    }
}
