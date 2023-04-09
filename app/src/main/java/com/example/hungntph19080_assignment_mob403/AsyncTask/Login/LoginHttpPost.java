package com.example.hungntph19080_assignment_mob403.AsyncTask.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
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

public class LoginHttpPost extends AsyncTask<String, Void, String> {
    OnLoginResultListener listener;
    Context context;
    ProgressDialog mProgressDialog;

    public LoginHttpPost(OnLoginResultListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Đang đăng nhập...");
        mProgressDialog.show();

    }

    @Override
    protected String doInBackground(String... strings) {
        String content = null;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line)
                        .append("\n");

            }

            reader.close();
            connection.disconnect();

            content = builder.toString();

            Log.d("zzzzzz", "doInBackground: Kích thước: " + content.length());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mProgressDialog.dismiss();
        if(s.length()==3){
//            Toast.makeText(context, "Thất bại",Toast.LENGTH_SHORT).show();
            listener.onLoginFail();
        }else {
//            Toast.makeText(context, "Thành công",Toast.LENGTH_SHORT).show();
            try {
                JSONArray arr = new JSONArray(s);
                for(int i = 0; i<arr.length(); i++){
                    JSONObject obj = arr.getJSONObject(i);

                    SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", String.valueOf(obj.get("username")));
                    editor.putString("email", String.valueOf(obj.get("email")));
                    editor.putString("fullname", String.valueOf(obj.get("fullName")));
                    editor.commit();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            listener.onLoginSuccess();
        }
    }

    public interface OnLoginResultListener {
        void onLoginSuccess();

        void onLoginFail();
    }
}
