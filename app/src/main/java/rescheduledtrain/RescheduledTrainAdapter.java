package rescheduledtrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ankur.railwayinfo.R;

import java.util.ArrayList;

/**
 * Created by ankur on 7/21/16.
 */
public class RescheduledTrainAdapter extends BaseAdapter {
    ArrayList<RescheduledTrain> listUsers;
    Context context;
    public RescheduledTrainAdapter (Context context, ArrayList<RescheduledTrain>listUsers){
        this.context = context;
        this.listUsers = listUsers;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return  listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.custom_rescheduled_train_info, null);

        TextView trainNameTv = (TextView)itemView.findViewById(R.id.trainNameTv);
        TextView fromStationTv = (TextView)itemView.findViewById(R.id.fromSationTv);
        TextView toStationTv = (TextView)itemView.findViewById(R.id.toStationTv);
        TextView rescheduledDateTv = (TextView)itemView.findViewById(R.id.rescheduledDateTv);
        TextView rescheduledTimeTv = (TextView)itemView.findViewById(R.id.rescheduledTimeTv);
        TextView timeDifferenceTv = (TextView)itemView.findViewById(R.id.timeDifferenceTv);

        trainNameTv.setText(listUsers.get(position).getTrainName());
        fromStationTv.setText(listUsers.get(position).getFromStation());
        toStationTv.setText(listUsers.get(position).getToStation());
        rescheduledDateTv.setText(listUsers.get(position).getRescheduledDate());
        rescheduledTimeTv.setText(listUsers.get(position).getRescheduledTime());
        timeDifferenceTv.setText(listUsers.get(position).getTimeDifference());

        return itemView;
    }
}
