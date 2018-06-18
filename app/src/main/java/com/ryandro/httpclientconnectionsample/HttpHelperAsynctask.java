package com.ryandro.httpclientconnectionsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpHelperAsynctask extends AsyncTask<String, Integer, String> {
    private ProgressDialog progressDialog;
    private Context context;
    private final String MAIN_URL = "http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=";
   /* private int TIMEOUT_CONNECT_MILLIS = (1*10*1000);
    private int TIMEOUT_READ_MILLIS = (3*60*1000);*/
    private int TIMEOUT_CONNECT_MILLIS = (1*10*1000);
    private int TIMEOUT_READ_MILLIS = (3*60*1000);

    public HttpHelperAsynctask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Getting Data From Server Please wait...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();

        String strUrl = MAIN_URL + strings[0];

        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT_CONNECT_MILLIS);
            connection.setReadTimeout(TIMEOUT_READ_MILLIS);
            try {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String strLine = "";
                while ((strLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLine);
                }
                PlantList plantList = PlantParsar.getData(stringBuilder);
                Log.d("Response",""+plantList.getPlantDOList().size());

            } finally {
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("", "\n\n");

        Log.d("Response ", "\n\n\n---------------Data Start----------------------------\n" + s);
        Log.d("Response ", "\n----------------------End Data ---------------------\n");
        parseData(s);
        progressDialog.dismiss();
    }

    private void parseData(String s) {
        try {
            ArrayList<PlantDO> plantDOArrayList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(s);
            JSONArray plantObject = jsonObject.getJSONArray("plants");

            for (int i = 0; i < plantObject.length(); i++) {
                JSONObject jsonObject1 = plantObject.getJSONObject(i);
                PlantDO plantDO = new PlantDO();
                plantDO.setId(jsonObject1.getInt("id"));
                plantDO.setGenus(jsonObject1.getString("genus"));
                plantDO.setSpecies(jsonObject1.getString("species"));
                plantDO.setCultivar(jsonObject1.getString("cultivar"));
                plantDO.setCommon(jsonObject1.getString("common"));
                plantDOArrayList.add(plantDO);
            }
            Log.d("PlantDOArrayListSize", "" + plantDOArrayList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
