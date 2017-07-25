package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.District;
import vsec.com.yupax.model.http.response.Province;

/**
 * Created by ntdong2012 on 7/25/2017.
 */

public class DistrictAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<District> districts;
        private LayoutInflater inflater;

        public DistrictAdapter(Context context, ArrayList<District> districts) {
            this.context = context;
            this.districts = districts;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return districts != null ? districts.size() : 0;
        }

        @Override
        public Object getItem(int i) {
            return districts != null ? districts.get(i) : null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.city_item_actionbar, viewGroup, false);

            ((TextView) view.findViewById(R.id.text1)).setText(districts.get(i).getName());

            return view;
        }
}
