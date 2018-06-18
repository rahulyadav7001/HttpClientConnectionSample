package com.ryandro.httpclientconnectionsample;

import android.support.annotation.Nullable;

public class PlantDO {
    /*{
        "id": "1893",
            "genus": "Acer",
            "species": "buergeranum",
            "cultivar": "",
            "common": "Trident Maple"
    },*/
    @Nullable
    private int id;
    @Nullable
    private String genus = "";
    @Nullable
    private String species = "";
    @Nullable
    private String cultivar = "";
    @Nullable
    private String common = "";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCultivar() {
        return cultivar;
    }

    public void setCultivar(String cultivar) {
        this.cultivar = cultivar;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}

