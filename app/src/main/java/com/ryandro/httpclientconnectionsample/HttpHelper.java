package com.ryandro.httpclientconnectionsample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpHelper extends AsyncTask<String, Integer, String> {
    private ProgressDialog progressDialog;
    private Context context;
    private final String MAIN_URL = "http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=";
    private int TIMEOUT_CONNECT_MILLIS = (1 * 10 * 1000);
    private int TIMEOUT_READ_MILLIS = (3 * 60 * 10);

    public HttpHelper(MainActivity mainActivity) {
        context = mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Getting the data from server....");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection = null;
        String paramValue = strings[0];
        try {
            URL url = new URL(MAIN_URL + paramValue);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(TIMEOUT_CONNECT_MILLIS);
            httpURLConnection.setReadTimeout(TIMEOUT_READ_MILLIS);

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String tmpStr = "";
            while ((tmpStr = bufferedReader.readLine()) != null) {
                sb.append(tmpStr);
            }
//           doParsing(sb.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }
        doParsing(sb.toString());

        return sb.toString();
    }

    private void doParsing(String jsonString) {
        ArrayList<PlantsDataObject> plantsDataObjects = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("plants");
            int size = jsonArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                PlantsDataObject plantDO = new PlantsDataObject();
                plantDO.setId(jsonObj.getString("id"));
                plantDO.setGenus(jsonObj.getString("genus"));
                plantDO.setSpecies(jsonObj.getString("species"));
                plantDO.setCultivar(jsonObj.getString("cultivar"));
                plantDO.setCommon(jsonObj.getString("common"));
                plantsDataObjects.add(plantDO);
            }
            Log.d("Response", "Size of ArrayList = " + plantsDataObjects.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("Response came", "\n----------------data is------------\n" + s);
//        MainActivity.UpdateData(s);
        progressDialog.cancel();
    }


}
