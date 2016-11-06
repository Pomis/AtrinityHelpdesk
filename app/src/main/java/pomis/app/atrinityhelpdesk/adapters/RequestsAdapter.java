package pomis.app.atrinityhelpdesk.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pomis.app.atrinityhelpdesk.R;
import pomis.app.atrinityhelpdesk.models.RequestModel;

/**
 * Created by romanismagilov on 01.11.16.
 */

public class RequestsAdapter extends ArrayAdapter {

    Context context;
    List list;

    public RequestsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);

        this.context = context;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RequestViewHolder holder = null;
        RequestModel model = (RequestModel) list.get(position);

        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_request, null);

            holder = new RequestViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_request_title);
            holder.status = (TextView) convertView.findViewById(R.id.tv_request_status);
            holder.number = (TextView) convertView.findViewById(R.id.tv_request_number);
            holder.date = (TextView) convertView.findViewById(R.id.tv_request_datetime);
            convertView.setTag(holder);

        } else {
            holder = (RequestViewHolder) convertView.getTag();
        }
        holder.date.setText(model.CreatedAt);
        holder.number.setText(model.RequestID);
        holder.status.setText(model.StatusDisplayName);
        holder.title.setText(model.Name);

        if (model.StatusDisplayName.equals("Закрыта")){
            holder.status.setAlpha(0.5f);
        } else {
            holder.status.setAlpha(1);
        }

        return convertView;
    }

    private class RequestViewHolder {
        public TextView title;
        public TextView date;
        public TextView status;
        public TextView number;

    }
}
