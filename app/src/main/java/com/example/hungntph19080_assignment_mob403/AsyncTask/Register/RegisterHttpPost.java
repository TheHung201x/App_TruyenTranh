package com.example.hungntph19080_assignment_mob403.AsyncTask.Register;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterHttpPost extends AsyncTask<String, Void, String> {
    TextInputLayout user, pass, email, fullname;

    public RegisterHttpPost(TextInputLayout user, TextInputLayout pass, TextInputLayout email, TextInputLayout fullname) {
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.fullname = fullname;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;

        try {
            url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject postData = new JSONObject();
            postData.put("username", user.getEditText().getText().toString());
            postData.put("password", pass.getEditText().getText().toString());
            postData.put("email", email.getEditText().getText().toString());
            postData.put("fullName", fullname.getEditText().getText().toString());

            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));


            writer.append(postData.toString());

            writer.flush();
            writer.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            inputStream.close();
            connection.disconnect();

            Log.d("zzzzzzz", "Trả về: " + builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
