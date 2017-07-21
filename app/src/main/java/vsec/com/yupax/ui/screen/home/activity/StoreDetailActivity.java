package vsec.com.yupax.ui.screen.home.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.StoreDetailContract;
import vsec.com.yupax.model.http.response.Store;
import vsec.com.yupax.model.http.response.StoreDetailResponse;
import vsec.com.yupax.presenter.StoreDetailPresenter;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;

public class StoreDetailActivity extends BaseActivity<StoreDetailPresenter> implements StoreDetailContract.View, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    public static void callStoreDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @BindView(R.id.mapView)
    MapView mMapView;
    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    GoogleMap mGoogleMap;
    LocationRequest locationRequest;
    private String storeHashcodeBrand;
    @BindView(R.id.process)
    ProgressBar progressBar;

    @BindView(R.id.com_iv)
    ImageView storeImv;
    @BindView(R.id.com_name_tv)
    TextView storeName;
    @BindView(R.id.com_address_tv)
    TextView storeAddress;
    @BindView(R.id.address_value_tv)
    TextView distanceTv;
    @BindView(R.id.phone_value_tv)
    TextView phoneTv;
    @BindView(R.id.time_note_tv)
    TextView timeTv;
    @BindView(R.id.title_actionbar)
    TextView titleActionbar;
    @BindView(R.id.sale_tv_one)
    TextView saleTv;

    @BindView(R.id.time_status_tv)
    TextView timeStatus;

    private Store currentStore;

    void updateStoreDetailUI(Store store) {
        DLog.d("LOGO: " + store.getImages());
        Glide.with(this).load(store.getImages()).into(storeImv);
        storeName.setText(store.getName());
        storeAddress.setText(store.getAddress());
        phoneTv.setText(store.getMobile());
        timeStatus.setText(store.getOpenTime());
        titleActionbar.setText(store.getName());
        saleTv.setText("" + store.getDescription());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        updateStoreHashcodeBrand();
        onLoading();
        mPresenter.getStoreDetail(storeHashcodeBrand);
    }

    private void updateStoreHashcodeBrand() {
        if (getIntent() != null) {
            Bundle b = getIntent().getBundleExtra("home_data");
            storeHashcodeBrand = b.getString("ID");
            DLog.d("Store: " + storeHashcodeBrand);
        }
    }


    @Override
    public void onStart() {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void getLocation() {
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @OnClick(R.id.back_icon_iv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_company_detail_new_layout;
    }

    @Override
    protected void initEventAndData() {
        getLocation();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetStoreDetailSuccess(StoreDetailResponse storeDetailResponse) {
        DLog.d("onGetStoreDetailSuccess");
        onStopLoading();
        if (storeDetailResponse.getError().getCode().equals("200")) {
            currentStore = storeDetailResponse.getStore();
            updateStoreDetailUI(storeDetailResponse.getStore());
        }
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, locationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mLastLocation != null) {
            DLog.d("lat : " + mLastLocation.getLatitude() + " Long: " + mLastLocation.getLongitude());
        } else {
            DLog.d("Lat long null");
        }
        if (mGoogleMap != null && mLastLocation != null) {
            Log.d("ntdong", "localReceiver23234234234");
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            // create marker
            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(latitude, longitude)).title("Hello Maps");

            // Changing marker icon
            marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            // adding marker
            mGoogleMap.addMarker(marker);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude)).zoom(12).build();
            mGoogleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));
            if (currentStore != null) {
                distanceTv.setText(Utils.calculateDistance(latitude, longitude, Double.parseDouble(currentStore.getLat()),
                        Double.parseDouble(currentStore.getLg())) + " km");
            }
        }
        ;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
    }
}
