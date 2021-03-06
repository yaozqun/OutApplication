package com.seatwe.zsws.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.seatwe.zsws.R;
import com.seatwe.zsws.model.Module;
import com.seatwe.zsws.ui.adapter.ModuleAdapter;
import com.seatwe.zsws.ui.base.BaseActivity;
import com.seatwe.zsws.util.UploadUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView lv_module;

    private String[] modules_str;// 模块名称

    private int[] mudules_pic;// 模块图片

    private List<Module> modules;

    private ModuleAdapter adapter;// 适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * 初始化
     */
    public void init() {
        initData();
        initView();
        initListener();
    }

    /**
     * 初始化UI
     */
    public void initView() {
        btnTitleRight.setVisibility(View.VISIBLE);
        btnTitleRight.setText("");
        btnTitleRight.setBackgroundResource(R.mipmap.icon_upload);
        btnTitleLeft.setText("");
        Drawable drawable= getResources().getDrawable(R.mipmap.icon_setting);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        btnTitleLeft.setCompoundDrawables(drawable, null, null, null);

        lv_module = (ListView) findViewById(R.id.lv_module);
        // 设置适配器
        adapter = new ModuleAdapter(this, modules);
        lv_module.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        modules_str = new String[]{getResources().getString(R.string.module_line),
                getResources().getString(R.string.module_task),
                getResources().getString(R.string.module_box)};
        mudules_pic = new int[]{R.mipmap.module_line, R.mipmap.module_task,
                R.mipmap.module_box};

        modules = new ArrayList<Module>();
        for (int i = 0; i < modules_str.length; i++) {
            Module module = new Module();
            module.setModuleName(modules_str[i]);
            module.setModulePic(mudules_pic[i]);
            modules.add(module);
        }
    }

    /**
     * 初始化监听
     */
    public void initListener() {
        lv_module.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, LineNodeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, TaskActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, CashboxActivity.class));
                        break;
                }
            }
        });

        btnTitleRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //上传
                UploadUtil.uploadAllRecord(MainActivity.this);
            }
        });

        btnTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

    }

}
