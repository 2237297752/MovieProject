package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.adapter.TabEntryAdapter;
import com.bw.movie.view.fragment.HotFrag;
import com.bw.movie.view.fragment.PlayingFrag;
import com.bw.movie.view.fragment.WillFrag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryActivity extends AppCompatActivity {

    @BindView(R.id.entryview_pager)
    ViewPager entryviewPager;
    @BindView(R.id.entrytab_layout)
    TabLayout entrytabLayout;
    @BindView(R.id.fanhui_entry)
    ImageView fanhuiEntry;
    @BindView(R.id.edit_entry)
    EditText editEntry;
    @BindView(R.id.serach_entry)
    TextView serachEntry;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<Fragment> fragmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list.add("正在热映");
        list.add("即将上映");
        list.add("热门电影");
        fragmes.add(new PlayingFrag());
        fragmes.add(new WillFrag());
        fragmes.add(new HotFrag());
        TabEntryAdapter tabEntryAdapter = new TabEntryAdapter(getSupportFragmentManager(), list, fragmes);
        entryviewPager.setAdapter(tabEntryAdapter);
        entrytabLayout.setupWithViewPager(entryviewPager);

        Intent intent = getIntent();
        int selectentry = intent.getIntExtra("selectentry",0);
        if (selectentry == 1) {
            entryviewPager.setCurrentItem(0);
        } else if (selectentry == 2) {
            entryviewPager.setCurrentItem(1);
        } else if (selectentry == 3) {
            entryviewPager.setCurrentItem(2);
        }
    }

    @OnClick({R.id.fanhui_entry, R.id.serach_entry})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_entry:
                finish();
                break;
            case R.id.serach_entry:
                startActivity(new Intent(EntryActivity.this,ScoutActivity.class));
                break;
        }
    }
}
