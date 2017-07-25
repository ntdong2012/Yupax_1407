package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ChangeProfileContract;
import vsec.com.yupax.model.http.request.UserInfoChange;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.District;
import vsec.com.yupax.model.http.response.GetDistrictResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.model.http.response.Province;
import vsec.com.yupax.presenter.ChangeProfilePresenter;
import vsec.com.yupax.ui.view.adapter.ProvinceAdapter;
import vsec.com.yupax.ui.view.adapter.DistrictAdapter;
import vsec.com.yupax.utils.AnimationUtils;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;
import vsec.com.yupax.utils.log.DateUtils;

public class ChangeProfileActivity extends BaseActivity<ChangeProfilePresenter> implements ChangeProfileContract.View, DatePickerDialog.OnDateSetListener {


    public static void callChangeProfileActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangeProfileActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_change_profile;
    }

    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    @Override
    protected void initEventAndData() {
        isFirstTime = true;
        initCitySpinner();

        mPresenter.getUserInfo();
        mPresenter.getProvinces();

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onChangeProfileSuccess(BaseResponse baseResponse) {
        if (baseResponse != null && baseResponse.getErrorResponse() != null && baseResponse.getErrorResponse().getCode().contains("200")) {
            ToastUtils.shortShow(getString(R.string.update_profile_success));
            String lastName = lastNameEdt.getText().toString();
            String firstName = firstNameEdt.getText().toString();

            mPresenter.saveUserName(firstName + " " + lastName);
        } else {
            ToastUtils.shortShow(baseResponse.getErrorResponse().getMessage());
            DLog.d(baseResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetProvincesSuccess(GetProvincesResponse getProvincesResponse) {
        if (getProvincesResponse != null && getProvincesResponse.getErrorResponse() != null &&
                getProvincesResponse.getErrorResponse().getCode().equals("200")) {
            provinces.clear();
            for (int i = 0; i < getProvincesResponse.getProvinces().size(); i++) {
                provinces.add(getProvincesResponse.getProvinces().get(i));
            }
            provinceAdapter.notifyDataSetChanged();
            updateProvinceSpinner();
        } else {
            ToastUtils.shortShow(getProvincesResponse.getErrorResponse().getMessage());
            DLog.d(getProvincesResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    public void onStopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.city_value_tv)
    void onClickProvince() {
        isFirstTime = false;
        citySpinner.performClick();
    }

    @OnClick(R.id.district_value_tv)
    void onClickDistrict() {
        districtSpinner.performClick();
    }

    @OnClick(R.id.update_profile_btn)
    void onUpdateProfileClick() {
        String firstName = firstNameEdt.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            AnimationUtils.shake(this, firstNameEdt);
            return;
        }

        String lastName = lastNameEdt.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            AnimationUtils.shake(this, lastNameEdt);
            return;
        }
        String email = emailEdt.getText().toString();
        String phone = phoneEdt.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            AnimationUtils.shake(this, phoneEdt);
            return;
        }
        String birthDay = birthDayEdt.getText().toString();
        if (TextUtils.isEmpty(birthDay)) {
            AnimationUtils.shake(this, birthDayEdt);
            return;
        }
        String city = cityTv.getText().toString();
        if (TextUtils.isEmpty(city)) {
            AnimationUtils.shake(this, cityTv);
            return;
        }
        String district = districtTv.getText().toString();
        if (TextUtils.isEmpty(district)) {
            AnimationUtils.shake(this, districtTv);
            return;
        }
        String address = addressEdt.getText().toString();
        if (TextUtils.isEmpty(address)) {
            AnimationUtils.shake(this, addressEdt);
            return;
        }
        String gender = "";
        if (maleRb.isChecked()) {
            gender = "MALE";
        } else {
            gender = "FEMALE";
        }

        mPresenter.requestChangeProfile(firstName, lastName, gender, getProvinceId(city), getDistrictId(district), address, email, DateUtils.convertTimeFromString(birthDay),
                phone);
    }

    String getProvinceId(String string) {
        if (!TextUtils.isEmpty(string)) {
            for (int i = 0; i < provinces.size(); i++) {
                if (provinces.get(i).getName().contains(string)) {
                    return provinces.get(i).getId();
                }
            }
        }
        return "";
    }

    String getDistrictId(String string) {
        if (!TextUtils.isEmpty(string)) {
            for (int i = 0; i < districts.size(); i++) {
                if (districts.get(i).getName().contains(string)) {
                    return districts.get(i).getId();
                }
            }
        }
        return "";
    }

    @OnClick(R.id.birthday_value_edt)
    void onBirthdayClick() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ChangeProfileActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onGetUserInfoSuccess(GetUserInfoResponse baseResponse) {
        updateUI(baseResponse.getUserInfoChange());
    }


    @Override
    public void onGetDistrictSuccess(GetDistrictResponse getDistrictResponse) {
        if (getDistrictResponse != null && getDistrictResponse.getErrorResponse() != null && getDistrictResponse.getErrorResponse().getCode().contains("200")) {
            districts.clear();
            for (int i = 0; i < getDistrictResponse.getDistricts().size(); i++) {
                districts.add(getDistrictResponse.getDistricts().get(i));
            }
            districtAdapter.notifyDataSetChanged();
            updateDistrictSpinner();
        } else {
            ToastUtils.shortShow(getDistrictResponse.getErrorResponse().getMessage());
            DLog.d(getDistrictResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }

    void updateUI(UserInfoChange userInfoChange) {
        if (!TextUtils.isEmpty(userInfoChange.getFirstName()))
            firstNameEdt.setText("" + userInfoChange.getFirstName());
        if (!TextUtils.isEmpty(userInfoChange.getLastName()))
            lastNameEdt.setText("" + userInfoChange.getLastName());
        if (!TextUtils.isEmpty(userInfoChange.getEmail()))
            loginEdt.setText("" + userInfoChange.getEmail());
        if (!TextUtils.isEmpty(userInfoChange.getEmail()))
            emailEdt.setText("" + userInfoChange.getEmail());
        if (!TextUtils.isEmpty(userInfoChange.getMobile()))
            phoneEdt.setText("" + userInfoChange.getMobile());
        if (!TextUtils.isEmpty(userInfoChange.getDateOfBirth()))
            birthDayEdt.setText("" + DateUtils.converTimeFromLongForBirthDay(Long.parseLong(userInfoChange.getDateOfBirth())));
        if (!TextUtils.isEmpty(userInfoChange.getGender()) && userInfoChange.getGender().contains("FEMALE")) {
            femaleRb.setChecked(true);
        } else {
            maleRb.setChecked(true);
        }
        if (!TextUtils.isEmpty(userInfoChange.getAddress()))
            addressEdt.setText("" + userInfoChange.getAddress());

        userProvinceId = userInfoChange.getProvinceId();
        updateProvinceSpinner();
        if (!TextUtils.isEmpty(userProvinceId))
            mPresenter.getDistricts(userProvinceId);
        userDistrictId = userInfoChange.getDistrictId();
        updateDistrictSpinner();
    }

    void updateDistrictSpinner() {
        DLog.d("DISTRICT id: " + userDistrictId + " District szie:  " + districts.size());
        if (!TextUtils.isEmpty(userDistrictId) && districts.size() > 0) {
            for (int i = 0; i < districts.size(); i++) {
                if (districts.get(i).getId().equals(userDistrictId)) {
                    districtTv.setText("" + districts.get(i).getName());
                }
            }
        }
    }

    void updateProvinceSpinner() {
        DLog.d("Province id: " + userProvinceId + " Province Size: " + provinces.size());
        if (!TextUtils.isEmpty(userProvinceId) && provinces.size() > 0) {
            for (int i = 0; i < provinces.size(); i++) {
                if (provinces.get(i).getId().equals(userProvinceId)) {
                    cityTv.setText("" + provinces.get(i).getName());
                }
            }
        }
    }

    void initCitySpinner() {

        provinces = new ArrayList<>();
        provinceAdapter = new ProvinceAdapter(this, provinces);
        citySpinner.setAdapter(provinceAdapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!isFirstTime) {
                    cityTv.setText("" + provinces.get(i).getName());
                    DLog.d("ProvinceID: " + provinces.get(i).getName() + " ID: " + provinces.get(i).getId());
                    mPresenter.getDistricts(provinces.get(i).getId());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("ntdong", "cityName: ");
            }
        });

        districts = new ArrayList<>();
        districtAdapter = new DistrictAdapter(this, districts);
        districtSpinner.setAdapter(districtAdapter);
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isFirstTime) {
                    districtTv.setText("" + districts.get(position).getName());
                    DLog.d("District ID: " + districts.get(position).getName());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("ntdong", "districName: ");
            }
        });

    }

    ArrayList<Province> provinces;
    ProvinceAdapter provinceAdapter;
    ArrayList<District> districts;
    DistrictAdapter districtAdapter;
    private boolean isFirstTime;


    @BindView(R.id.first_name_value)
    EditText firstNameEdt;
    @BindView(R.id.last_name_value_edt)
    EditText lastNameEdt;
    @BindView(R.id.login_user_value_edt)
    EditText loginEdt;
    @BindView(R.id.email_label_value_edt)
    EditText emailEdt;
    @BindView(R.id.phone_label_value_edt)
    EditText phoneEdt;
    @BindView(R.id.birthday_value_edt)
    TextView birthDayEdt;
    @BindView(R.id.male_rb)
    RadioButton maleRb;
    @BindView(R.id.female_rb)
    RadioButton femaleRb;
    @BindView(R.id.nation_value_tv)
    TextView nationTv;
    @BindView(R.id.city_value_tv)
    TextView cityTv;
    @BindView(R.id.district_value_tv)
    TextView districtTv;
    @BindView(R.id.address_value_edt)
    EditText addressEdt;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.district_spinner)
    Spinner districtSpinner;
    @BindView(R.id.process)
    ProgressBar progressBar;

    private String userProvinceId;
    private String userDistrictId;

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int date) {
        int monthDisplay = month + 1;
        String dateStr = "";
        if (date < 10) {
            dateStr = "0" + date;
        } else {
            dateStr = "" + date;
        }
        String monStr = "";
        if (monthDisplay < 10) {
            monStr = "0" + monthDisplay;
        } else {
            monStr = "" + monthDisplay;
        }
        String birth = dateStr + "/" + monStr + "/" + year;
        birthDayEdt.setText(birth);
    }
}
