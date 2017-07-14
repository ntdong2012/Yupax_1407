package vsec.com.yupax.ui.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.base.SimpleActivity;
import vsec.com.yupax.model.prefs.PreferencesHelper;
import vsec.com.yupax.model.prefs.PreferencesHelperImpl;
import vsec.com.yupax.utils.Common;

public class LanguageActivity extends SimpleActivity {

    @BindView(R.id.back_tv)
    TextView backTv;
    @BindView(R.id.eng_selected_iv)
    ImageView enLangIv;
    @BindView(R.id.vietnam_selected_iv)
    ImageView viLangIv;

    private PreferencesHelper php;

    @OnClick(R.id.vietnam_language_rl)
    void onViLangSelected() {
        if (viLangIv.getVisibility() == View.VISIBLE) {
            viLangIv.setVisibility(View.GONE);
            enLangIv.setVisibility(View.VISIBLE);
            php.setLanguage(Common.SPF.LANGUAGE_EN);

        } else {
            viLangIv.setVisibility(View.VISIBLE);
            enLangIv.setVisibility(View.GONE);
            php.setLanguage(Common.SPF.LANGUAGE_VI);
        }

        setDisplayLanguage();
    }

    @OnClick(R.id.english_language_rl)
    void onEnLangSelected() {
        if (viLangIv.getVisibility() == View.VISIBLE) {
            viLangIv.setVisibility(View.GONE);
            enLangIv.setVisibility(View.VISIBLE);
            php.setLanguage(Common.SPF.LANGUAGE_EN);
        } else {
            php.setLanguage(Common.SPF.LANGUAGE_VI);
            viLangIv.setVisibility(View.VISIBLE);
            enLangIv.setVisibility(View.GONE);
        }

        setDisplayLanguage();
    }

    public static void callLanguageActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, LanguageActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_language;
    }

    @Override
    protected void initEventAndData() {
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
        php = new PreferencesHelperImpl();
        setDefaultLanguageSetting();

    }


    void setDefaultLanguageSetting() {
        String currentLang = php.getLanguage();
        if (currentLang.contains(Common.SPF.LANGUAGE_VI)) {
            viLangIv.setVisibility(View.VISIBLE);
            enLangIv.setVisibility(View.GONE);
        } else {
            viLangIv.setVisibility(View.GONE);
            enLangIv.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
