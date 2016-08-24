package com.example.ankur.railwayinfo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {
    EditText uname, password;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String username = uname.getText().toString();
                String pass = password.getText().toString();


                String url_login = "http://192.168.76.189:8080/RailwayInfo"+"/LoginServlet?uemail="+username+"&password="+pass;

                new Login().execute(url_login);
            }
        });

    }


    class Login extends AsyncTask<String, Void, String> {

        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(LoginActivity.this);
            dialog.setMessage("Loading....");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String str = params[0];
            String result = "";
            //open connection

            try {
                URL url = new URL(str);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestMethod("GET");
                httpCon.connect();
                if (httpCon.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) break;
                        else result = result + line;
                    }
                    bufferedReader.close();
                    httpCon.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && s.length() > 0) {
                try {
                    JSONObject json = new JSONObject(s);
                    s = json.getString("info");
                    Log.d("Msg", json.getString("info"));
                    if (s.equals("success")) {
                        Intent login = new Intent(getApplicationContext(), WelcomeActivity.class);
                        startActivityForResult(login, 101);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Login is fail", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101 && resultCode==0)
        {

        }
    }
}
