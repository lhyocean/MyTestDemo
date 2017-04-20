package com.beijing.ocean.multmediademo.adapter;

import android.content.Context;

import com.beijing.ocean.multimediademo.R;
import com.beijing.ocean.multmediademo.bean.UserInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by lenovo on 2017/4/11.
 */
public class UserAdapter extends BaseQuickAdapter <UserInfo>{

    public UserAdapter(Context context, List<UserInfo> data) {
        super(context, R.layout.item_my_info,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, UserInfo o) {
        if (o!=null){
            baseViewHolder.setText(R.id.item_my_info_name,o.getName());
            baseViewHolder.setText(R.id.item_my_info_des,o.getDes());
            baseViewHolder.setImageUrl(R.id.head_pic,o.getHeadurl());
        }

    }
}
