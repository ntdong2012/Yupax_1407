package vsec.com.yupax.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import vsec.com.yupax.R;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public class NotificationDialog extends Dialog {

    private Context context;
    private String message;

    public interface INotificationCloseEvent {
        void onNotificationClose();
    }

    private INotificationCloseEvent iNotificationCloseEvent;

    public NotificationDialog(Context context, String message, INotificationCloseEvent iNotificationCloseEvent) {
        super(context);
        this.context = context;
        this.message = message;
        this.iNotificationCloseEvent = iNotificationCloseEvent;
    }

    public NotificationDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.notification_dialog_layout);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0x11000000));

        TextView messageTv = (TextView) findViewById(R.id.notification_message_tv);
        if (!TextUtils.isEmpty(message)) {
            messageTv.setText(message);
        }

        AppCompatButton closeBtn = (AppCompatButton) findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNotificationCloseEvent.onNotificationClose();
                NotificationDialog.this.dismiss();
            }
        });
    }
}
