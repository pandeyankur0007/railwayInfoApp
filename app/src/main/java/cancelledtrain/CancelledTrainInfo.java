package cancelledtrain;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ankur.railwayinfo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CancelledTrainInfo extends AppCompatActivity {

    ListView listView1;
    TextView lastUpdateTime;
    ArrayList<CancelTrain> listUSERS = new ArrayList<>();
    CancelledTrainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_train_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("CancelledTrain");
        lastUpdateTime = (TextView)findViewById(R.id.lastUpdateTv);
        listView1 = (ListView) findViewById(R.id.listView1);
        adapter = new CancelledTrainAdapter(this, listUSERS);
        listView1.setAdapter(adapter);

        Intent intent = getIntent();
        String date = intent.getStringExtra("Date");

        String url = "http://api.railwayapi.com/cancelled/date/"+date+"/apikey/rigng1122/";
        LoadUsersTask task = new LoadUsersTask();
        task.execute(url);

    }

    class LoadUsersTask extends AsyncTask<String, Void, String>
    {

        ProgressDialog dialog = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(CancelledTrainInfo.this);
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
                    JSONObject jsonLastUpdate = jsonResponse.getJSONObject("last_updated");
                    String date = jsonLastUpdate.getString("date");
                    String time = jsonLastUpdate.getString("time");
                    String resultUpdate = date+"("+time+")";
                    lastUpdateTime.setText(resultUpdate);

                    JSONArray jsonArray = jsonResponse.getJSONArray("trains");
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject r = jsonArray.getJSONObject(i);

                        JSONObject jsonTrain = r.getJSONObject("train");
                        String trainNumber = jsonTrain.getString("number");
                        String trainName = jsonTrain.getString("name");
                        String resultTrainName = trainName+"-"+trainNumber;

                        String startTime = jsonTrain.getString("start_time");
                        String trainType = jsonTrain.getString("type");

                        JSONObject jsonSource = r.getJSONObject("source");
                        String sourceTrainName = jsonSource.getString("name");
                        String sourceTrainCode = jsonSource.getString("code");
                        String resultSource = sourceTrainName+"-"+sourceTrainCode;

                        JSONObject jsonDestination = r.getJSONObject("dest");
                        String destinationTrainName = jsonDestination.getString("name");
                        String destinationTrainCode = jsonDestination.getString("code");
                        String resultDesination = destinationTrainName+"-"+destinationTrainCode;

                        listUSERS.add(new CancelTrain(resultTrainName, trainType, resultSource, startTime, resultDesination));
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
