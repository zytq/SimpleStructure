package cn.lishuai.simplestructure.utils;


import android.app.Activity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * ============================================
 * 文件名：ShareUtil.java
 * 作者：SYT019
 * 日期：2017-05-05 21:23
 * 更新：2017-05-05 21:23
 * 描述：分享工具类
 * 版本：1.0
 * 版权：Copyright （C） 2016 河南商宇科技有限公司
 * ============================================
 */
public class ShareUtil {

    private ShareUtil() {

    }

    public static void startShare(Activity activity, String content) {
        new ShareAction(activity).withText(content)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener).open();
    }

    /**
     * 开始分享
     *
     * @param media 分享对象
     */
    public static void startShare(Activity activity, UMImage media) {
        new ShareAction(activity).withMedia(media)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener).open();
    }

    /**
     * 开始分享
     *
     * @param media 分享对象
     */
    public static void startShare(Activity activity, UMWeb media) {
        new ShareAction(activity).withMedia(media)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener).open();
    }

    private static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            LogUtils.d("plat", "platform" + platform);

            UIUtils.showToast(platform + " 分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            UIUtils.showToast(platform + " 分享失败啦");
            if (t != null) {
                LogUtils.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            UIUtils.showToast(platform + " 分享取消了");
        }
    };
}
