package com.example.ankur.railwayinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import cancelledtrain.CancelledTrainActivity;
import livetrainstatus.LiveTrainStatusActivity;
import pnrstatus.PnrStatusActivity;
import rescheduledtrain.RescheduledTrainActivity;
import seatavailability.SeatAvailabilityActivity;
import trainarrival.TrainArrivalActivity;
import trainbetweenstation.TrainBetweenStationActivity;
import trainfareenquiry.TrainFareEnquiryActivity;
import trainrunningdat.TrainRunningDayActivity;

public class WelcomeActivity extends AppCompatActivity {


    GridView gridView;
    public static String[] gridViewStrings = {
            "Train Between Station",
            "PNR Status",
            "Live Train",
            "Seat Status",
            "Cancelled Train",
            "Rescheduled Train",
            "Running Train",
            "Fare Enquiry",
            "Train Arrive",
            "Route Status",
            "HelpLine",
            "AboutUs"
    };

    public static int[] gridViewImages = {
            R.drawable.trainbetweenstation,
            R.drawable.pnrstatus,
            R.drawable.livestatus,
            R.drawable.seat,
            R.drawable.cancelled,
            R.drawable.rescheduled,
            R.drawable.running,
            R.drawable.fare,
            R.drawable.arrive,
            R.drawable.route,
            R.drawable.helpline,
            R.drawable.aboutus
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        gridView = (GridView) findViewById(R.id.gridView);

        gridView.setAdapter(new GridViewAdapter(this, gridViewStrings, gridViewImages));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), TrainBetweenStationActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), PnrStatusActivity.class);
                        startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(), LiveTrainStatusActivity.class);
                        startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), SeatAvailabilityActivity.class);
                        startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(getApplicationContext(), CancelledTrainActivity.class);
                        startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(getApplicationContext(), RescheduledTrainActivity.class);
                        startActivity(intent5);
                        break;

                    case 6:
                        Intent intent6 = new Intent(getApplicationContext(), TrainRunningDayActivity.class);
                        startActivity(intent6);
                        break;

                    case 7:
                        Intent intent7 = new Intent(getApplicationContext(), TrainFareEnquiryActivity.class);
                        startActivity(intent7);
                        break;

                    case 8:
                        Intent intent8 = new Intent(getApplicationContext(), TrainArrivalActivity.class);
                        startActivity(intent8);
                        break;

                    case 10:
                        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
                        LayoutInflater inflater = WelcomeActivity.this.getLayoutInflater();
                        builder.setView(inflater.inflate(R.layout.custom_helpline, null))
                                .setTitle("HelpLine")
                                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        builder.show();
                        break;

                    case 11:
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(WelcomeActivity.this);
                        LayoutInflater inflater1 = WelcomeActivity.this.getLayoutInflater();
                        builder1.setView(inflater1.inflate(R.layout.custom_aboutus, null))
                                .setTitle("HelpLine")
                                .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        builder1.show();
                        break;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_fb:
                Uri uri = Uri.parse("https://www.facebook.com/railwayInfo/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>https://play.google.com/store/apps/details?id=com.augylabs.ankur.railwayinfo</p>"));
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;

        }

        return true;
    }
}
