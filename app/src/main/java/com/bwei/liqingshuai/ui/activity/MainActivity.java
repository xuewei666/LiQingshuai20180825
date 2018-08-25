package com.bwei.liqingshuai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.liqingshuai.R;
import com.fyales.tagcloud.library.TagBaseAdapter;
import com.fyales.tagcloud.library.TagCloudLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_text;
    private Button sousuo;
    private TagCloudLayout tag_cloud_layout;
    private ArrayList list;
    private TagBaseAdapter tagBaseAdapter;
    private Button qingchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        list = new ArrayList<>();
        list.add("琅琊榜");
        tagBaseAdapter = new TagBaseAdapter(this, list);
        tag_cloud_layout.setAdapter(tagBaseAdapter);
        tag_cloud_layout.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(MainActivity.this, GouWuCheActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        edit_text = (EditText) findViewById(R.id.edit_text);
        sousuo = (Button) findViewById(R.id.sousuo);
        tag_cloud_layout = (TagCloudLayout) findViewById(R.id.tag_cloud_layout);
        sousuo.setOnClickListener(this);

        qingchu = (Button) findViewById(R.id.qingchu);
        qingchu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sousuo:
                String string = edit_text.getText().toString();
                list.add(string);
                tagBaseAdapter.notifyDataSetChanged();
                break;

//            case R.id.tag_cloud_layout:
//
//                Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.qingchu:

                list.clear();
                tagBaseAdapter.notifyDataSetChanged();
                break;
        }
    }


}
