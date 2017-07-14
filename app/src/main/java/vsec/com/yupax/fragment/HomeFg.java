package vsec.com.yupax.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vsec.com.yupax.R;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class HomeFg extends Fragment {

    public HomeFg() {
    }

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fg, container, false);
        return rootView;
    }
}
