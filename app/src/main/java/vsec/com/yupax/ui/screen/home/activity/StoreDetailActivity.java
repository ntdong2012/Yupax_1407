package vsec.com.yupax.ui.screen.home.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oguzbabaoglu.fancymarkers.CustomMarker;
import com.oguzbabaoglu.fancymarkers.MarkerManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.StoreDetailContract;
import vsec.com.yupax.model.http.response.Store;
import vsec.com.yupax.model.http.response.StoreDetailResponse;
import vsec.com.yupax.presenter.StoreDetailPresenter;
import vsec.com.yupax.utils.PicassoMarker;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;
import vsec.com.yupax.utils.marker.NetworkMarker;

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
    private String logo;

    private MarkerManager<NetworkMarker> networkMarkerManager;


    void updateStoreDetailUI(Store store) {
        Glide.with(this).load(store.getImages()).into(storeImv);
        storeName.setText(store.getName());
        storeAddress.setText(store.getAddress());
        phoneTv.setText(store.getMobile());
        timeStatus.setText(store.getOpenTime());
        titleActionbar.setText(store.getName());
        saleTv.setText("" + store.getDescription());
        if (!TextUtils.isEmpty(store.getLogo())) {
            logo = store.getLogo();
        }
        addMarker(Double.parseDouble(store.getLat()), Double.parseDouble(store.getLg()), store.getName());

        double latitude = mLastLocation.getLatitude();
        double longitude = mLastLocation.getLongitude();
        if (currentStore != null) {
            distanceTv.setText(Utils.calculateDistance(latitude, longitude, Double.parseDouble(currentStore.getLat()),
                    Double.parseDouble(currentStore.getLg())) + " km");
            // create marker

        }
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Vị trí của tôi");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        // adding marker
        mGoogleMap.addMarker(marker);
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
            logo = b.getString("LOGO");
            DLog.d("Store: " + storeHashcodeBrand + " logo: " + logo);
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


    @OnClick(R.id.facebook_share)
    void handleFacebookShare() {
        ToastUtils.shortShow("facebook share");
    }

    @OnClick(R.id.mail_share)
    void handleMailShare() {
        ToastUtils.shortShow("email share");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_company_detail_new_layout;
    }

    @Override
    protected void initEventAndData() {
        if (imageLoader == null) {
            final RequestQueue queue = Volley.newRequestQueue(this);
            imageLoader = new ImageLoader(queue, new NoImageCache());
        }
        getLocation();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetStoreDetailSuccess(StoreDetailResponse storeDetailResponse) {
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
        }
        if (mGoogleMap != null && mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();
            if (currentStore != null) {
                distanceTv.setText(Utils.calculateDistance(latitude, longitude, Double.parseDouble(currentStore.getLat()),
                        Double.parseDouble(currentStore.getLg())) + " km");
                // create marker

            }
            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(latitude, longitude)).title("Vị trí của tôi");

            // Changing marker icon
            marker.icon(BitmapDescriptorFactory
                    .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            // adding marker
            mGoogleMap.addMarker(marker);
        }

    }

    private void addMarker(double latitude, double longitude, String name) {


        networkMarkerManager.addMarkers(createNetworkMarkers());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(latitude, longitude)).zoom(14).build();
        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        networkMarkerManager = new MarkerManager<>(googleMap);

    }

    private static final ArrayList<LatLng> LOCATIONS = new ArrayList<>();
    private static ImageLoader imageLoader;


    private ArrayList<NetworkMarker> createNetworkMarkers() {
        LOCATIONS.add(new LatLng(Double.parseDouble(currentStore.getLat()), Double.parseDouble(currentStore.getLg())));
        final ArrayList<NetworkMarker> networkMarkers = new ArrayList<>(LOCATIONS.size());

        for (LatLng location : LOCATIONS) {
            networkMarkers.add(new NetworkMarker(this, location, imageLoader, logo));
        }

        return networkMarkers;
    }

    /**
     * Not interested in caching images.
     */
    private static class NoImageCache implements ImageLoader.ImageCache {

        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            // Do nothing
        }
    }

    /**
     * Marker click listener that always returns true.
     */
    private static class DisableClick<T extends CustomMarker>
            implements MarkerManager.OnMarkerClickListener<T> {

        @Override
        public boolean onMarkerClick(T marker) {
            return true;
        }
    }
}
