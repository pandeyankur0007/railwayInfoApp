package rescheduledtrain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ankur.railwayinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RescheduledTrainInfo extends AppCompatActivity {

    ListView listView1;
    ArrayList<RescheduledTrain> listUSERS = new ArrayList<>();
    RescheduledTrainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescheduled_train_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("RescheduledTrain");

        listView1 = (ListView) findViewById(R.id.listView1);
        adapter = new RescheduledTrainAdapter(this, listUSERS);
        listView1.setAdapter(adapter);


        Intent intent = getIntent();
        String date = intent.getStringExtra("Date");

        String url = "http://api.railwayapi.com/rescheduled/date/"+date+"/apikey/rigng1122/";
        LoadUsersTask task = new LoadUsersTask();
        task.execute(url);
    }

    class LoadUsersTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(RescheduledTrainInfo.this);
            dialog.setMessage("Loading....");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String str = params[0];
            String result = "";

            //open connect and get data
            try {
                URL url = new URL(str);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setRequestMethod("GET");
                httpCon.connect();
                if (httpCon.getResponseCode()==200)
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    while (true)
                    {
                        String line = bufferedReader.readLine();
                        if (line == null) break;
                        else result=result+line;
                    }
                    bufferedReader.close();
                    httpCon.disconnect();
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return result;
        }//end of doInbackground

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null && s.length()>0)
            {
                try {

                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("trains");
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject r = jsonArray.getJSONObject(i);
                        String trainNumber = r.getString("number");
                        String trainName = r.getString("name");
                        String resultTrainName = trainName+"-"+trainNumber;

                        JSONObject jsontoStation = r.getJSONObject("to");
                        String toStationCode = jsontoStation.getString("code");
                        String toStationName = jsontoStation.getString("name");
                        String resultToStation = toStationName+"-"+toStationCode;

                        JSONObject jsonfromStation = r.getJSONObject("from");
                        String fromStationCode = jsonfromStation.getString("code");
                        String fromStationName = jsonfromStation.getString("name");
                        String resultFromStation = fromStationName+"-"+fromStationCode;

                        String rescheduledDate = r.getString("rescheduled_date");
                        String rescheduledTime = r.getString("rescheduled_time");
                        String timeDifference = r.getString("time_diff");

                        listUSERS.add(new RescheduledTrain(resultTrainName, resultFromStation, resultToStation, rescheduledDate, timeDifference, rescheduledTime));
                        adapter.notifyDataSetChanged();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
