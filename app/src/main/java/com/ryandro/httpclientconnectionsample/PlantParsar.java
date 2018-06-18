package com.ryandro.httpclientconnectionsample;

import com.google.gson.GsonBuilder;

public class PlantParsar {
  /*  GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
    paySlipResponseDo = gsonBuilder.create().fromJson(response.toString().replace("Wage type", "Wagetype"),PaySlipResponseDo.class);
*/

    public static PlantList getData(StringBuilder stringBuffer) {
        PlantList plantList = new PlantList();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            plantList = gsonBuilder.create().fromJson(stringBuffer.toString(), PlantList.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return plantList;
    }

}
