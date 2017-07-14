package vsec.com.yupax.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.akexorcist.localizationactivity.LocalizationActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.model.prefs.PreferencesHelper;
import vsec.com.yupax.model.prefs.PreferencesHelperImpl;
import vsec.com.yupax.utils.Common;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public abstract class SimpleActivity extends LocalizationActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    private PreferencesHelper php;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        php = new PreferencesHelperImpl();
        setDisplayLanguage();
        onViewCreated();
        YupaxApps.getInstance().addActivity(this);
        initEventAndData();
    }

    public void setDisplayLanguage() {
        String lang = php.getLanguage();
        if (lang.contains(Common.SPF.LANGUAGE_VI)) {
            setLanguage("vi");
        } else {
            setLanguage("en");
        }
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        YupaxApps.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
