package vsec.com.yupax.utils.marker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.oguzbabaoglu.fancymarkers.BitmapGenerator;
import com.oguzbabaoglu.fancymarkers.CustomMarker;

import vsec.com.yupax.R;

/**
 * Created by ntdong2012 on 8/15/2017.
 */

public class NetworkMarker extends CustomMarker implements ImageLoader.ImageListener {

    private LatLng position;
    private View view;
    private ImageView markerImage;
    private ImageView markerBackground;
    private ImageLoader imageLoader;
    private TextView titleTv;
    private String url;
    private String name;

    public NetworkMarker(Context context, LatLng position, ImageLoader imageLoader, String url) {
        this.position = position;
        this.url = url;
        view = LayoutInflater.from(context).inflate(R.layout.view_network_marker, null);
        markerImage = (ImageView) view.findViewById(R.id.marker_image);
        markerBackground = (ImageView) view.findViewById(R.id.marker_background);
        this.imageLoader = imageLoader;
    }

    @Override
    public void onAdd() {
        imageLoader.get(url, this);
    }

    @Override
    public BitmapDescriptor getBitmapDescriptor() {
        return BitmapGenerator.fromView(view);
    }

    @Override
    public LatLng getPosition() {
        return position;
    }

    @Override
    public boolean onStateChange(boolean selected) {
//        if (selected) {
//            markerBackground.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
//        } else {
//            markerBackground.clearColorFilter();
//        }

        return true;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

        final Bitmap bitmap = response.getBitmap();

        // Set image and update view
        markerImage.setImageBitmap(bitmap);
        updateView();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // Ignore
    }

}
