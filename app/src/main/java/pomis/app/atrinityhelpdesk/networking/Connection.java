package pomis.app.atrinityhelpdesk.networking;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by romanismagilov on 02.11.16.
 */

public class Connection {
    public final static String host = "http://mobile.atrinity.ru/";


    public static String get(String address)
            throws Exception {
        URL url = new URL(address);
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream in = new BufferedInputStream(
                connection.getInputStream()
        );
        String response = convertStreamToString(in);
        connection.disconnect();
        return response;
    }


    public static void withJSON(String endpoint, String type, JSONObject jsonObject, @Nullable final OnResponseCodeReceived callback)
            throws MalformedURLException, IOException {
        String json = "";
        json = jsonObject.toString();
        Log.d("kek", json);
        URL url = new URL(host + endpoint);
        final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(type);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(json);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        Log.d("kek", "Response code: " + connection.getResponseCode());
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } finally {
            if (callback != null) {
                if (connection.getResponseCode() < 300){
                    final InputStream finalIn = in;
                    final String response = convertStreamToString(finalIn);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(response);
                        }
                    });
                }
                else{
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callback.onFailure(connection.getResponseCode());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

        }
    }


    static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
