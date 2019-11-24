package com.example.goodjob.classes;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class ActividadEsperaAdmin  implements Parcelable {

    private Integer id;
    private String title;
    private String description;
    private String author;
    private String creationDate;


    public static final Creator<ActividadEsperaAdmin> CREATOR = new Creator<ActividadEsperaAdmin>() {
        @Override
        public ActividadEsperaAdmin createFromParcel(Parcel in) {
            return new ActividadEsperaAdmin(in);
        }

        @Override
        public ActividadEsperaAdmin[] newArray(int size) {
            return new ActividadEsperaAdmin[size];
        }
    };

    public ActividadEsperaAdmin() {

    }

    public static ActividadEsperaAdmin loadActivityDataFromJsonObject(JSONObject jsonObject)
    {
        ActividadEsperaAdmin actividad = new ActividadEsperaAdmin();

        actividad.id = jsonObject.optInt("id");
        actividad.title = jsonObject.optString("titulo");
        actividad.description = jsonObject.optString("descripcion");
        actividad.author = jsonObject.optString("nombre_completo");
        actividad.creationDate = jsonObject.optString("fecha_creacion");

        return actividad;
    }

    public ActividadEsperaAdmin(Parcel in) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(author);
        parcel.writeString(creationDate);
    }

    public interface OnActivityListener {
        void onActivityClick(int position);
    }
}
