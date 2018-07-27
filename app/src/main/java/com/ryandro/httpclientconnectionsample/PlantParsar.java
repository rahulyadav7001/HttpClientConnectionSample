package com.ryandro.httpclientconnectionsample;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PlantParsar {
  /*  GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
    paySlipResponseDo = gsonBuilder.create().fromJson(response.toString().replace("Wage type", "Wagetype"),PaySlipResponseDo.class);
*/

    public static PlantList getData(StringBuilder stringBuffer) {
        PlantList plantList = new PlantList();
        try {
           /* GsonBuilder gsonBuilder = new GsonBuilder();
            plantList = gsonBuilder.create().fromJson(stringBuffer.toString(), PlantList.class);*/

            Gson mGson = new GsonBuilder().create();
//            plantList = mGson.fromJson(stringBuffer.toString(),new TypeToken<PlantList>(){}.getType());
            plantList = mGson.fromJson(stringBuffer.toString(),PlantList.class);

            Log.d("Response", String.valueOf(""+plantList!=null?plantList.getPlantsList().size():0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plantList;
    }

}
