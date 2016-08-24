package cancelledtrain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ankur.railwayinfo.R;

import java.util.ArrayList;

/**
 * Created by ankur on 7/20/16.
 */
public class CancelledTrainAdapter extends BaseAdapter {
    ArrayList<CancelTrain>listUsers;
    Context context;
    public CancelledTrainAdapter (Context context, ArrayList<CancelTrain>listUsers){
        this.context = context;
        this.listUsers = listUsers;
    }

    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.custom_cancelled_train_info, null);

        TextView trainNameTv = (TextView)itemView.findViewById(R.id.trainNameTv);
        TextView trainTypeTv = (TextView)itemView.findViewById(R.id.trainTypeTv);
        TextView startTimeTv = (TextView)itemView.findViewById(R.id.startTimeTv);
        TextView sourceTv = (TextView)itemView.findViewById(R.id.sourceTv);
        TextView destinationTv = (TextView)itemView.findViewById(R.id.destinationTv);

        trainNameTv.setText(listUsers.get(position).getTrainName());
        trainTypeTv.setText(listUsers.get(position).getTrainType());
        startTimeTv.setText(listUsers.get(position).getStartTime());
        sourceTv.setText(listUsers.get(position).getSourceStation());
        destinationTv.setText(listUsers.get(position).getDestinationStation());

        return itemView;
    }
}
