package vsec.com.yupax.ui.screen.home.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.HomeFgContract;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.model.http.response.ListStoreResponse;
import vsec.com.yupax.model.http.response.Store;
import vsec.com.yupax.presenter.HomeFgPresenter;
import vsec.com.yupax.ui.screen.home.activity.StoreDetailActivity;
import vsec.com.yupax.ui.view.adapter.StoreAdapter;
import vsec.com.yupax.utils.PerUtils;
import vsec.com.yupax.utils.ResizeAnimation;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;

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
    RecyclerView storeRv;
    @BindView(R.id.process)
    ProgressBar progressBar;
    StoreAdapter storeAdapter;
    ArrayList<Store> stores;
    RecyclerView.LayoutManager layoutManager;

    private int currentCategoryId;
    private String currentProvinceId;
    private String keySearch = "";


    public void updateProvinceID(String provinceID, String searchKey) {
        currentProvinceId = provinceID;
        this.keySearch = searchKey;
        storeRv.setVisibility(View.GONE);
        mPresenter.getListStores(keySearch, currentCategoryId, currentProvinceId);
    }

    void initLocationList() {
        stores = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        storeRv.setLayoutManager(layoutManager);
        storeAdapter = new StoreAdapter(getActivity(), stores, new StoreAdapter.IItemClick() {
            @Override
            public void onItemClick(Store location) {
                Bundle b = new Bundle();
                b.putString("ID", location.getStoreBranchHashcode());
                StoreDetailActivity.callStoreDetailActivity(getActivity(), b);
            }
        });
        storeRv.setAdapter(storeAdapter);

        storeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        mPresenter.getCategories();
        currentCategoryId = 0;
        currentProvinceId = "01";
        mPresenter.getListStores("", currentCategoryId, currentProvinceId);
        initLocationList();
        onLoading();

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

        mMapView.onResume();

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
        animateMapView(Utils.getMaximumMapHeight(getActivity()));
    }

    private void animateMapView(int height) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mMapView.getLayoutParams();
        ResizeAnimation a = new ResizeAnimation(mMapView);
        a.setDuration(100);
        a.setParams(lp.height, Utils.dpToPx(getResources(), height));
        mMapView.startAnimation(a);
    }

    private boolean getMapViewStatus() {
        return mMapViewExpanded;
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

    private String[] verifyPermission() {
        String[] pers = new String[2];
        if (!PerUtils.hasAccessCoarseLocationPermission(getActivity()) && PerUtils.isNeverAskAgainWithAccessCoarseLocationPermission(getActivity())) {
            pers[0] = (Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!PerUtils.hasAccessFineLocationPermission(getActivity()) && PerUtils.isNeverAskAgainWithAccessFineLocationPermission(getActivity())) {
            pers[1] = Manifest.permission.ACCESS_FINE_LOCATION;
        }
        return pers;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (verifyLocationPermission()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, locationRequest, this);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PerUtils.REQUEST_LOCATION_PERMISSIONS:
                if (isPermissionGrantedByUser(grantResults)) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(
                            mGoogleApiClient, locationRequest, this);
                }
                break;
            default:
                break;
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private boolean verifyFineLocationPermission() {
        List<String> permissionNeeded = new ArrayList<String>();
        if (!PerUtils.hasAccessFineLocationPermission(getActivity()) &&
                !PerUtils.isNeverAskAgainWithAccessFineLocationPermission(getActivity())) {
            permissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionNeeded.size() > 0) {
            this.requestPermissions(permissionNeeded.toArray(new String[permissionNeeded.size()]), PerUtils.REQUEST_CODE_FINE_LOCATION_PERMISSIONS);
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean verifyLocationPermission() {
        List<String> permissionNeeded = new ArrayList<String>();
        if (!PerUtils.hasAccessFineLocationPermission(getActivity()) &&
                !PerUtils.isNeverAskAgainWithAccessFineLocationPermission(getActivity())) {
            permissionNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!PerUtils.hasAccessFineLocationPermission(getActivity()) &&
                !PerUtils.isNeverAskAgainWithAccessFineLocationPermission(getActivity())) {
            permissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionNeeded.size() > 0) {
            this.requestPermissions(permissionNeeded.toArray(new String[permissionNeeded.size()]), PerUtils.REQUEST_LOCATION_PERMISSIONS);
            return false;
        }
        return true;
    }

    private boolean isPermissionGrantedByUser(int[] grantResults) {
        boolean isOK = true;
        if (grantResults.length > 0) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isOK = false;
                }
            }
        }
        return isOK;
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
            return;
        }
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("getLocationOK"));
    }

    @Override
    public void useLanguage(String language) {

    }


    @Override
    public void onGetListStoreSuccess(ListStoreResponse listStoreResponse) {
        DLog.d("onGetListStoreSuccess");
        stores.clear();
        if (listStoreResponse != null && listStoreResponse.getErrorResponse() != null && listStoreResponse.getErrorResponse().getCode().contains("200")) {
            for (int i = 0; i < listStoreResponse.getStores().size(); i++) {
                stores.add(listStoreResponse.getStores().get(i));
            }
        } else {
            Toast.makeText(getActivity(), listStoreResponse.getErrorResponse().getMessage(), Toast.LENGTH_SHORT).show();
            DLog.d(listStoreResponse.getErrorResponse().getMessage());
            if (listStoreResponse.getErrorResponse().getCode().contains("327")) {
                mPresenter.onRegisterUserToMerchant();
            }
        }
        onStopLoading();
        storeRv.setVisibility(View.VISIBLE);
        storeAdapter.notifyDataSetChanged();
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
    public void onGetCategoriesSuccess(GetCategoriesResponse getCategoriesResponse) {
        DLog.d("onGetCategoriesSuccess");

        if (getCategoriesResponse != null && getCategoriesResponse.getErrorResponse() != null
                && getCategoriesResponse.getErrorResponse().getCode().contains("200")) {
            for (int i = 0; i < getCategoriesResponse.getCategories().size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(getCategoriesResponse.getCategories().get(i).getName()));
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        DLog.d("Category Position : " + position);
                        currentCategoryId = position;
                        mPresenter.getListStores("", position, currentProvinceId);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        } else {
            Toast.makeText(getActivity(), getCategoriesResponse.getErrorResponse().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegisterUserToMerchantSuccess(BaseResponse baseResponse) {
        if (baseResponse != null && baseResponse.getErrorResponse().getCode().contains("200")) {
            mPresenter.getListStores(keySearch, currentCategoryId, currentProvinceId);
        } else {
            DLog.d(baseResponse.getErrorResponse().getMessage());
        }
    }


    class LocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            DLog.d("localReceiver");
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
                if (stores != null && stores.size() > 0) {
                    for (int i = 0; i < stores.size(); i++) {
                        stores.get(i).setMyLat(latitude);
                        stores.get(i).setMyLog(longitude);
                    }
                    storeAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
