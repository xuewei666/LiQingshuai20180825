package com.bwei.liqingshuai.di.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.liqingshuai.R;
import com.bwei.liqingshuai.data.CartInfo;
import com.bwei.liqingshuai.ui.weigth.MyAddSubView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jack Lee on 2018/8/25.
 * name:Juck Lee
 */

public class Myadapter extends BaseExpandableListAdapter {

    private List<CartInfo.DataBean> sellerData;

    public Myadapter(List<CartInfo.DataBean> sellerData) {
        this.sellerData = sellerData;
    }

    @Override
    public int getGroupCount() {
        return sellerData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return sellerData.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;

        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.layout_group, null);
            groupViewHolder.cb_group_view = convertView.findViewById(R.id.cb_group_view);
            groupViewHolder.tv_title_view = convertView.findViewById(R.id.tv_title_view);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();

        }
        CartInfo.DataBean dataBean = sellerData.get(groupPosition);
        //商家名称
        groupViewHolder.tv_title_view.setText(dataBean.getSellerName());
        //商家box
        boolean cuurllerAllSelected = isCuurllerAllSelected(groupPosition);
        groupViewHolder.cb_group_view.setChecked(cuurllerAllSelected);
        groupViewHolder.cb_group_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCartListChangeListest!=null){
                    onCartListChangeListest.sellerSelectedChange(groupPosition);
                }
            }
        });
        return convertView;
    }

    public void ChangeCuurentSellerAll(int position, boolean b){
        List<CartInfo.DataBean.ListBean> list = sellerData.get(position).getList();
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setSelected(b?1:0);
        }

    }

    public boolean isCuurllerAllSelected(int p){
        List<CartInfo.DataBean.ListBean> list = sellerData.get(p).getList();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getSelected()==0){
                return false;
            }
        }

        return  true;
    }
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.layout_child, null);
            childViewHolder.cb_child_view = convertView.findViewById(R.id.cb_child_view);
            childViewHolder.iv_icon_view = convertView.findViewById(R.id.iv_icon_view);
            childViewHolder.tv_child_view = convertView.findViewById(R.id.tv_child_view);
            childViewHolder.tv_price_view = convertView.findViewById(R.id.tv_price_view);
            childViewHolder.add_sub_view = convertView.findViewById(R.id.add_sub_view);
            convertView.setTag(childViewHolder);
        }else {

            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        CartInfo.DataBean.ListBean listBean = sellerData.get(groupPosition).getList().get(childPosition);
        //商品信息
        childViewHolder.tv_child_view.setText(listBean.getTitle());
        //图片
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        Picasso.get().load(split[0]).into(childViewHolder.iv_icon_view);
        childViewHolder.tv_price_view.setText("￥"+listBean.getPrice());
        childViewHolder.cb_child_view.setChecked(listBean.getSelected()==1);
        childViewHolder.add_sub_view.setOnNumberChangeLiser(new MyAddSubView.OnNumberChangeLiser() {
            @Override
            public void onNumberChang(int number) {
                onCartListChangeListest.productNumberChange(groupPosition,childPosition,number);
            }
        });
        return convertView;
    }
    public void changeNumder(int groupPosition,int childPosition, int number){
        CartInfo.DataBean.ListBean listBean = sellerData.get(groupPosition).getList().get(childPosition);
        listBean.setNum(number);


    }


    public boolean isAllPresentSelected(){
        for (int i = 0; i <sellerData.size() ; i++) {
            List<CartInfo.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                    if (list.get(j).getSelected()==0){
                        return false;
                    }
               }
          }
        return true;
    }
    public void changeAllPresentSelected(boolean b){
        for (int i = 0; i <sellerData.size() ; i++) {
            List<CartInfo.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                list.get(j).setSelected(b?1:0);


            }
        }

    }
    public double  JiSuanJiaGe(){
            double totelPrice = 0;
        for (int i = 0; i <sellerData.size() ; i++) {
            List<CartInfo.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(j).getSelected()==1){
                    double price = list.get(j).getPrice();
                    int num = list.get(j).getNum();
                    totelPrice+=price*num;
                }
            }
        }

        return totelPrice;
    }
    public int  JiSuanshuliang(){
        int totelNumber = 0;
        for (int i = 0; i <sellerData.size() ; i++) {
            List<CartInfo.DataBean.ListBean> list = sellerData.get(i).getList();
            for (int j = 0; j <list.size() ; j++) {
                if (list.get(j).getSelected()==1){

                    int num = list.get(j).getNum();
                    totelNumber+=num;
                }
            }
        }

        return totelNumber;
    }


    public static class GroupViewHolder {

        public CheckBox cb_group_view;
        public TextView tv_title_view;


    }

    public static class ChildViewHolder {

        public CheckBox cb_child_view;
        public ImageView iv_icon_view;
        public TextView tv_child_view;
        public TextView tv_price_view;
        public MyAddSubView add_sub_view;
    }
    OnCartListChangeListest onCartListChangeListest;

    public void setOnCartListChangeListest(OnCartListChangeListest onCartListChangeListest) {
        this.onCartListChangeListest = onCartListChangeListest;
    }

    public  interface  OnCartListChangeListest{
        void sellerSelectedChange(int groupPosition);

        void productNumberChange(int groupPosition, int childPosition, int number);

    }

}
