package cn.lishuai.simplestructure.callback;

import com.lzy.okgo.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cn.lishuai.simplestructure.utils.LogUtils;
import cn.lishuai.simplestructure.utils.UIUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SYT019 on 2017-04-07.
 */

public abstract class SYStringCallback extends StringCallback {
    public static final String TAG = SYStringCallback.class.getSimpleName();

    /**
     * 对返回数据进行操作的回调， UI线程
     *
     * @param s
     * @param call
     * @param response
     */
    @Override
    public void onSuccess(String s, Call call, Response response) {
        LogUtils.d(TAG, s);
        try {
            JSONObject jo = new JSONObject(s);
            if (jo.has("code")) {
                int code = jo.optInt("code");
                if (jo.has("content")) {
                    if (code == 200) {
                        if (jo.has("content"))
                            onReceive(jo.optString("content"));
                        else
                            onReceive("");
                    } else {
                        if (code == -1) {
                            onFailure(code, "暂无数据！");
                        } else
                            onFailure(code, jo.optString("info"));
                    }
                } else {
                    if (code == 200) {
                        onReceive(jo.optString("info"));
                    } else if (code == -1) {
                        onFailure(code, "出现错误，请稍后重试！");
                    } else if (code == -2) {
                        onFailure(code, "出错了！");
                    } else {
                        if (jo.has("mes"))
                            onFailure(code, jo.optString("mes"));
                        else
                            onFailure(code, jo.optString("info"));
                    }
                }
            } else if (jo.has("status")) {
                int status = jo.optInt("status");
                if (status == 1) {
                    onReceive(jo.optString("data"));
                } else {
                    onFailure(status, jo.optString("info"));
                }
            } else {
                onFailure(601, "服务器返回数据有误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onFailure(600, "数据解析失败！");
        }
    }

    public abstract void onReceive(String data);

    public void onFailure(int code, String msg) {
        LogUtils.e(TAG, "code = " + code + ", msg = " + msg);
        UIUtils.showToast(msg);
    }
}
