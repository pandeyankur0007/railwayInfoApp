package trainbetweenstation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ankur.railwayinfo.R;

import autocomplete.AutoCompleteStationAdapter;

public class TrainBetweenStationActivity extends AppCompatActivity {
    Button findTrainBtn;
    EditText dojEditText;
    String dateandmonth;
    AutoCompleteTextView stationSearch1;
    AutoCompleteTextView stationSearch2;
    AutoCompleteStationAdapter adapter1;
    AutoCompleteStationAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_between_station);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("TrainBetweenStation");

        dojEditText = (EditText)findViewById(R.id.dojEdit);
        findTrainBtn = (Button) findViewById(R.id.btnFindTrain);

        stationSearch1 = (AutoCompleteTextView) findViewById(R.id.search1);
        adapter1 = new AutoCompleteStationAdapter(this, android.R.layout.simple_dropdown_item_1line);
        stationSearch1.setAdapter(adapter1);

        //when autocomplete is clicked
        stationSearch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stationName1 = adapter1.getItem(position).getName();
                stationSearch1.setText(stationName1);

            }
        });

        stationSearch2 = (AutoCompleteTextView) findViewById(R.id.search2);
        adapter2 = new AutoCompleteStationAdapter(this, android.R.layout.simple_dropdown_item_1line);
        stationSearch2.setAdapter(adapter2);

        //when autocomplete is clicked
        stationSearch2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stationName2 = adapter2.getItem(position).getName();
                stationSearch2.setText(stationName2);

            }
        });

        // open data picker on dob field click
        dojEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                DatePickerDialog dialog = new DatePickerDialog(TrainBetweenStationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String str = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
                        dateandmonth = dayOfMonth+"-"+(monthOfYear+1);
                        dojEditText.setText(str);
                    }
                },2016,7,20);
                dialog.show();
            }
        });


        findTrainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = stationSearch1.getText().toString();
                String name2 = stationSearch2.getText().toString();
                String fromCode = name1.substring(name1.indexOf("(") + 1, name1.indexOf(")"));
                String toCode = name2.substring(name2.indexOf("(") + 1, name2.indexOf(")"));

                Intent intent = new Intent(TrainBetweenStationActivity.this, TrainBetweenStationInfo.class);

                intent.putExtra("FromCode", fromCode);
                intent.putExtra("ToCode", toCode);
                intent.putExtra("DOJ", dateandmonth );
                startActivityForResult(intent, 101);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101)
        {
            stationSearch1.setText("");
            stationSearch2.setText("");
            dojEditText.setText("");
        }
    }
}
