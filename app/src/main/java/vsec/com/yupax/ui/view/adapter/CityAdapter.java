package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.Province;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class CityAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Province> cities;
    private LayoutInflater inflater;

    public CityAdapter(Context context, ArrayList<Province> cities) {
        this.context = context;
        this.cities = cities;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return cities != null ? cities.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return cities != null ? cities.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.city_item_actionbar, viewGroup, false);

        ((TextView) view.findViewById(R.id.text1)).setText(cities.get(i).getName());

        return view;
    }
}
