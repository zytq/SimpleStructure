package cn.lishuai.simplestructure.utils;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class RefreshComplete {

    public static void refreshComplete(XRecyclerView rv) {
        if (rv != null) {
            rv.refreshComplete();
            rv.loadMoreComplete();
        }
    }
}
