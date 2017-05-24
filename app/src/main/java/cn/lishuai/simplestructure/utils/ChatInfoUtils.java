package cn.lishuai.simplestructure.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.hnshangyu.zuyu.R;
import cn.hnshangyu.zuyu.db.ChatInfoHelper;
import cn.hnshangyu.zuyu.db.DbUtil;
import cn.hnshangyu.zuyu.db.green.ChatInfo;

public class ChatInfoUtils {

    private static ChatInfoHelper sInfoHelper;

    static {
        sInfoHelper = DbUtil.getChatInfoHelper();
    }

    /**
     * get EaseUser according username
     */
    public static ChatInfo getChatInfo(String username) {
        if (sInfoHelper != null)
            return sInfoHelper.query(username);

        return null;
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(Context context, String username, ImageView imageView) {
        ChatInfo user = getChatInfo(username);
        if (user != null) {
            String avatar = user.getImgPath();
            Glide.with(context).load(avatar).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ease_default_avatar).into(imageView);
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            ChatInfo user = getChatInfo(username);
            if (user != null && user.getRealName() != null) {
                textView.setText(user.getRealName());
            } else {
                textView.setText(username);
            }
        }
    }

}
