package cn.lishuai.simplestructure.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.view.Window;

import com.lzy.okgo.request.BaseRequest;

/**
 * ============================================
 * 文件名：SYDialogCallback.java
 * 作者：SYT019
 * 日期：2017-04-14 11:42
 * 更新：2017-04-14 11:42
 * 描述：带有弹框的Callback
 * 版本：1.0
 * 版权：Copyright （C） 2016 河南商宇科技有限公司
 * ============================================
 */
public abstract class SYDialogCallback extends SYStringCallback {
    public static final String TAG = SYDialogCallback.class.getSimpleName();
    private ProgressDialog dialog;

    public SYDialogCallback(Activity activity) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
    }

    public SYDialogCallback(Activity activity, String msg) {
        this(activity);
        dialog.setMessage(msg);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
