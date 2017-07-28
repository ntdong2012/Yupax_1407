package vsec.com.yupax.ui.screen.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vsec.com.yupax.R;

/**
 * Created by ntdong2012 on 7/28/2017.
 */

public class BlankFg extends Fragment {

    public BlankFg() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_fg_layout, container, false);
    }
}
