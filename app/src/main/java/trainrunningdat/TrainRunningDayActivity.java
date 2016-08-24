package trainrunningdat;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.ankur.railwayinfo.R;

import autocomplete.AutoCompleteTrainAdapter;

public class TrainRunningDayActivity extends AppCompatActivity {

    AutoCompleteTextView trainNameOrNumber;
    Button findTrainRunningDay;
    AutoCompleteTrainAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_running_day);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("RunningTrain");

        findTrainRunningDay = (Button)findViewById(R.id.findTrainRunningDayBtn);

        trainNameOrNumber = (AutoCompleteTextView)findViewById(R.id.trainAutoComplete);
        adapter1 = new AutoCompleteTrainAdapter(this, android.R.layout.simple_dropdown_item_1line);
        trainNameOrNumber.setAdapter(adapter1);

        //when autocomplete is clicked
        trainNameOrNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String trainName = adapter1.getItem(position).getName();
                trainNameOrNumber.setText(trainName);
            }
        });

        findTrainRunningDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainName = trainNameOrNumber.getText().toString();

                String trainNumber = trainName.substring(trainName.indexOf("(") + 1, trainName.indexOf(")"));

                Intent intent = new Intent(TrainRunningDayActivity.this, TrainRunningDayInfo.class);
                intent.putExtra("TrainNumber", trainNumber);
                startActivityForResult(intent, 101);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101)
        {
            trainNameOrNumber.setText("");

        }
    }
}
