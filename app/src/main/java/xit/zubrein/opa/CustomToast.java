package xit.zubrein.opa;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;


public class CustomToast {
    public static Dialog dialog;
    public static void showCustomToast(Activity activity, String messages) {
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_toast);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView message = dialog.findViewById(R.id.dialog_message);
        message.setText(messages);

        FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
        mDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
