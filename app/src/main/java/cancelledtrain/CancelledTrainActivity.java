package cancelledtrain;

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

public class CancelledTrainActivity extends AppCompatActivity {

    EditText dateEditText;
    Button findCancelTrainBtn;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_train);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("CancelledTrain");

        dateEditText = (EditText)findViewById(R.id.dateEditText);
        findCancelTrainBtn = (Button)findViewById(R.id.cancelTrainBtn);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                DatePickerDialog dialog = new DatePickerDialog(CancelledTrainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
                        dateEditText.setText(date);
                    }
                },2016,7,20);
                dialog.show();
            }
        });

        findCancelTrainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CancelledTrainActivity.this, CancelledTrainInfo.class);
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
