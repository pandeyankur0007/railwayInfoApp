package rescheduledtrain;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ankur.railwayinfo.R;

public class RescheduledTrainActivity extends AppCompatActivity {

    EditText dateEditText;
    Button findRescheduledTrainBtn;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescheduled_train);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("RescheduledTrain");
        dateEditText = (EditText)findViewById(R.id.dateEditText);
        findRescheduledTrainBtn = (Button)findViewById(R.id.rescheduledTrainBtn);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                DatePickerDialog dialog = new DatePickerDialog(RescheduledTrainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
                        dateEditText.setText(date);
                    }
                },2016,7,20);
                dialog.show();
            }
        });

        findRescheduledTrainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RescheduledTrainActivity.this, RescheduledTrainInfo.class);
                intent.putExtra("Date", date);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101)
        {
            dateEditText.setText("");
        }
    }

}
