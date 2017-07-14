package vsec.com.yupax.ui.screen.home.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.HomeFgContract;
import vsec.com.yupax.presenter.HomeFgPresenter;
import vsec.com.yupax.ui.screen.home.activity.CompanyDetailActivity;
import vsec.com.yupax.ui.view.adapter.LocationAdapter;
import vsec.com.yupax.utils.ResizeAnimation;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class HomeFg extends BaseFragment<HomeFgPresenter> implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, HomeFgContract.View {

    public HomeFg() {
    }

    View rootView;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.map_down_icon)
    ImageView mapDownIcon;

    Location mLastLocation;
    GoogleApiClient mGoogleApiClient;
    GoogleMap mGoogleMap;
    LocationRequest locationRequest;

    LocationReceiver mLocationReceiver;
    IntentFilter mLocationIntentFilter;
    private boolean mMapViewExpanded = false;

    @BindView(R.id.address_rv)
    RecyclerView locationRv;
    @BindView(R.id.process)
    ProgressBar progressBar;
    LocationAdapter locationAdapter;
    ArrayList<vsec.com.yupax.model.http.response.Location> locations;
    RecyclerView.LayoutManager layoutManager;


    void initLocationList() {
        locations = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        locationRv.setLayoutManager(layoutManager);
        locationAdapter = new LocationAdapter(getActivity(), locations, new LocationAdapter.IItemClick() {
            @Override
            public void onItemClick(vsec.com.yupax.model.http.response.Location location) {
                CompanyDetailActivity.callCompanyDetailActivity(getActivity(), new Bundle());
            }
        });
        locationRv.setAdapter(locationAdapter);

        locationRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("ntdong", "dy: " + dy);
                if (dy > 0) {
                    // Scrolling up
                    Log.d("ntdong", "isScrolling up");
                    animateMapView(0);

                } else if (dy < 0) {

                    Log.d("ntdong", "isScrolling down");
                    animateMapView(getResources().getInteger(R.integer.address_google_map_fragment));
                    // Scrolling down
                }
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.address_fg;
    }

    @Override
    protected void initEventAndData() {

        initMapView();

        tabLayout.addTab(tabLayout.newTab().setText("Dịch vụ chuyến bay"));
        tabLayout.addTab(tabLayout.newTab().setText("Tất cả"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Hàng hóa"));
        tabLayout.addTab(tabLayout.newTab().setText("Đặt chuyến"));
        tabLayout.addTab(tabLayout.newTab().setText("Đặt chỗ"));
        initLocationList();

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d("ntdong", "handleMessage");
                onGetLocationSuccess();
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    public void registerLocationReceiver() {
        mLocationReceiver = new LocationReceiver();
        mLocationIntentFilter = new IntentFilter("getLocationOK");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mLocationReceiver, mLocationIntentFilter);
    }

    public void unRegisterLocationReceiver() {
        if (mLocationReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mLocationReceiver);
            mLocationReceiver = null;
        }
    }


    void initMapView() {
        mMapView.onCreate(getBundle());

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);
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

    @OnClick(R.id.map_down_icon)
    void onMapDownClicked() {
        animateMapView(getMaximumMapHeight());
    }

    private void animateMapView(int height) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mMapView.getLayoutParams();

        ResizeAnimation a = new ResizeAnimation(mMapView);
        a.setDuration(100);
        a.setParams(lp.height, dpToPx(getResources(), height));
        mMapView.startAnimation(a);
    }

    private boolean getMapViewStatus() {
        return mMapViewExpanded;
    }

    public int getMaximumMapHeight() {
        int max = Utils.getHeightScreen(getActivity()) - Utils.getStatusBarHeight(getActivity())
                - dpToPx(getResources(), (int) getResources().getDimension(R.dimen.actionbar_height))
                - dpToPx(getResources(), (int) getResources().getDimension(R.dimen.home_control_layout_height));
        return max;
    }

    public int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("getLocationOK"));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocation();
        registerLocationReceiver();
    }


    private void getLocation() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }


    @Override
    public void onDestroy() {
        unRegisterLocationReceiver();
        super.onDestroy();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            Log.d("ntdong", "lat : " + mLastLocation.getLatitude() + " Long: " + mLastLocation.getLongitude());
        } else {
            Log.d("ntdong", "Lat long null");
        }
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("getLocationOK"));
    }

    public void onGetLocationSuccess() {
        Log.d("ntdong", "onGetLocationSuccess");

        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "VietJet TravelCare", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "VietJet sáng tạo SkyBoss dành riêng cho bạn", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Dịch vụ hành lý", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Dịch vụ hàng hóa", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ của chúng tôi", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ của chúng tôi", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ của chúng tôi", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));
        locations.add(new vsec.com.yupax.model.http.response.Location(R.drawable.product_item_icon, "Công Ty Cổ Phần VietJet Cargo", "Tầng 12 tòa nhà Hải Âu, 39B Trường Sơn", "0.48km", "0979439395", "Giảm giá 30% cho khách hạng vàng sự dụng dịch vụ", false));

        locationAdapter.notifyDataSetChanged();

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetProductItemsSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }


    class LocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("ntdong", "localReceiver");
            if (mGoogleMap != null && mLastLocation != null) {
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
            }
        }
    }
}
