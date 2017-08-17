package vsec.com.yupax.ui.screen.home.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
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
import com.oguzbabaoglu.fancymarkers.CustomMarker;
import com.oguzbabaoglu.fancymarkers.MarkerManager;

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
import vsec.com.yupax.ui.screen.home.activity.HomeActivity;
import vsec.com.yupax.ui.screen.home.activity.StoreDetailActivity;
import vsec.com.yupax.ui.screen.login.activity.SignInActivity;
import vsec.com.yupax.ui.view.adapter.StoreAdapter;
import vsec.com.yupax.utils.PerUtils;
import vsec.com.yupax.utils.ResizeAnimation;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;
import vsec.com.yupax.utils.marker.NetworkMarker;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class HomeFg extends BaseFragment<HomeFgPresenter> implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener, HomeFgContract.View {

    public HomeFg() {
    }

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.mapView)
    MapView mMapView;

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
    @BindView(R.id.map_down_icon)
    ImageView mapDownIcon;
    StoreAdapter storeAdapter;
    ArrayList<Store> stores;
    RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.map_wrapper)
    RelativeLayout mapWrapper;

    private int currentCategoryId;
    private String currentProvinceId;
    private String keySearch = "";
    private boolean isFirstTime = true;

    private MarkerManager<NetworkMarker> networkMarkerManager;
    private static ImageLoader imageLoader;


    private ArrayList<NetworkMarker> createNetworkMarkers() {

        final ArrayList<NetworkMarker> networkMarkers = new ArrayList<>(stores.size());

        for (Store store : stores) {

            LatLng m = new LatLng(Double.parseDouble(store.getLat()), Double.parseDouble(store.getLg()));

            networkMarkers.add(new NetworkMarker(getActivity(), m, imageLoader, store.getLogo()));
        }

        return networkMarkers;
    }

    public void updateProvinceID(String provinceID, String searchKey) {
        currentProvinceId = provinceID;
        this.keySearch = searchKey;
        storeRv.setVisibility(View.GONE);
        if (mLastLocation != null) {
            mPresenter.getListStores("" + mLastLocation.getLatitude(), "" + mLastLocation.getLongitude(), keySearch, currentCategoryId, currentProvinceId);
        } else {
            mPresenter.getListStores("", "", keySearch, currentCategoryId, currentProvinceId);
        }
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
                b.putString("LOGO", location.getLogo());
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
                if (dy > 0) {
                    animateMapView(0);
                } else if (dy < 0) {
                    animateMapView(getResources().getInteger(R.integer.address_google_map_fragment));
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
        return R.layout.home_fg_layout;
    }

    @Override
    protected void initEventAndData() {

        initMapView();
        mPresenter.getCategories();
        currentCategoryId = 0;
        currentProvinceId = "01";
        if (mLastLocation != null) {
            mPresenter.getListStores("" + mLastLocation.getLatitude(), "" + mLastLocation.getLongitude(), keySearch, currentCategoryId, currentProvinceId);
        } else {
            mPresenter.getListStores("", "", keySearch, currentCategoryId, currentProvinceId);
        }
        initLocationList();
        onLoading();

        if (imageLoader == null) {
            final RequestQueue queue = Volley.newRequestQueue(getActivity());
            imageLoader = new ImageLoader(queue, new NoImageCache());
        }

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
        mapDownIcon.animate().rotation(mapDownIcon.getRotation() + 180F).setDuration(1);
        if (isFirstTime) {
            animateMapView(Utils.getMaximumMapHeight(getActivity()));
            ((HomeActivity) getActivity()).hiddenFloatingButton();
        } else {
            animateMapView(getResources().getInteger(R.integer.address_google_map_fragment));
            ((HomeActivity) getActivity()).showFloatingButton();
        }
        isFirstTime = !isFirstTime;
    }

    private void animateMapView(int height) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mapWrapper.getLayoutParams();
        ResizeAnimation a = new ResizeAnimation(mapWrapper);
        a.setDuration(100);
        a.setParams(lp.height, Utils.dpToPx(getResources(), height));
        mapWrapper.startAnimation(a);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        networkMarkerManager = new MarkerManager<>(googleMap);
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


        mLocationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
//        mLocationManager.requestLocationUpdates(m);
    }

    private LocationManager mLocationManager;

    @Override
    public void onDestroy() {
        unRegisterLocationReceiver();
        super.onDestroy();

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        DLog.d("onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        DLog.d("onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        DLog.d("onLocationChanged");
        if (mLastLocation != null) {
            return;
        }
        mLastLocation = location;
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent("getLocationOK"));
    }


    @Override
    public void useLanguage(String language) {

    }


    @Override
    public void onGetListStoreSuccess(ListStoreResponse listStoreResponse) {
        stores.clear();
        if (listStoreResponse != null && listStoreResponse.getErrorResponse() != null && listStoreResponse.getErrorResponse().getCode().contains("200")) {
            for (int i = 0; i < listStoreResponse.getStores().size(); i++) {
                stores.add(listStoreResponse.getStores().get(i));
            }

            if (stores != null && stores.size() > 0 && mLastLocation != null) {
                for (int i = 0; i < stores.size(); i++) {
                    stores.get(i).setMyLat(mLastLocation.getLatitude());
                    stores.get(i).setMyLog(mLastLocation.getLongitude());
                }
                storeAdapter.notifyDataSetChanged();
            }

        } else {
            Toast.makeText(getActivity(), listStoreResponse.getErrorResponse().getMessage(), Toast.LENGTH_SHORT).show();
            DLog.d(listStoreResponse.getErrorResponse().getMessage());
            if (listStoreResponse.getErrorResponse().getCode().contains("327")) {
                mPresenter.onRegisterUserToMerchant();
            } else if (listStoreResponse.getErrorResponse().getCode().contains("203") ||
                    listStoreResponse.getErrorResponse().getCode().contains("305")) {
                mPresenter.saveToken("");
                SignInActivity.callSignInActivity(getActivity(), new Bundle());
                getActivity().finish();
                return;
            }
        }
        onStopLoading();
        storeRv.setVisibility(View.VISIBLE);
        storeAdapter.notifyDataSetChanged();
        networkMarkerManager.addMarkers(createNetworkMarkers());
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
        if (getCategoriesResponse != null && getCategoriesResponse.getErrorResponse() != null
                && getCategoriesResponse.getErrorResponse().getCode().contains("200")) {
            for (int i = 0; i < getCategoriesResponse.getCategories().size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(getCategoriesResponse.getCategories().get(i).getName()));
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        currentCategoryId = position;
                        if (mLastLocation != null) {
                            mPresenter.getListStores("" + mLastLocation.getLatitude(), "" + mLastLocation.getLongitude(), keySearch, currentCategoryId, currentProvinceId);
                        } else {
                            mPresenter.getListStores("", "", keySearch, position, currentProvinceId);
                        }
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
            if (mLastLocation != null) {
                mPresenter.getListStores("" + mLastLocation.getLatitude(), "" + mLastLocation.getLongitude(), keySearch, currentCategoryId, currentProvinceId);
            } else {
                mPresenter.getListStores("", "", keySearch, currentCategoryId, currentProvinceId);
            }
        } else {
            DLog.d(baseResponse.getErrorResponse().getMessage());
        }
    }


    class LocationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            DLog.d("localReceiver");
            if (mGoogleMap != null && mLastLocation != null) {
                DLog.d("get google map & location is ok " + stores.size());
                double latitude = mLastLocation.getLatitude();
                double longitude = mLastLocation.getLongitude();

                // create marker
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude, longitude)).title("Vị trí của tôi");

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
                    DLog.d("lat long set my lat");
                    storeAdapter.notifyDataSetChanged();
                }
                if (mLastLocation != null) {
                    mPresenter.getListStores("" + mLastLocation.getLatitude(), "" + mLastLocation.getLongitude(), keySearch, currentCategoryId, currentProvinceId);
                } else {
                    mPresenter.getListStores("", "", keySearch, currentCategoryId, currentProvinceId);
                }
            } else if (mLastLocation == null) {
                getLocation();
                if (mGoogleApiClient.isConnected()) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(
                            mGoogleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) HomeFg.this);
                }
            }
        }

    }


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
