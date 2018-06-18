package com.ryandro.httpclientconnectionsample;

public class PlantsDataObject {
    /*"id": "1893",
            "genus": "Acer",
            "species": "buergeranum",
            "cultivar": "",
            "common": "Trident Maple"*/
    private String id = "";
    private String genus = "";
    private String species = "";
    private String cultivar = "";
    private String common = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
