package com.example.workfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity<itemModel> extends AppCompatActivity {

    TextView date;
    Button prevBtn, nextBtn;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentHome fragmentHome = new FragmentHome();
    private FragmentStatistic fragmentStatistic = new FragmentStatistic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DateFormat mFormat = new SimpleDateFormat("yyyy.M.d.EE");
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String formatDate = mFormat.format(cal.getTime());

        date = (TextView) findViewById(R.id.date);
        date.setText(formatDate);
        prevBtn = (Button) findViewById(R.id.prev);
        nextBtn = (Button) findViewById(R.id.next);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }*/

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.DATE, -1);
                date.setText(mFormat.format(cal.getTime()));

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.DATE, +1);
                date.setText(mFormat.format(cal.getTime()));

            }
        });

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    BarChart chart1;

    public void initView(View v) {

        chart1 = (BarChart)v.findViewById(R.id.chart1);

    }

    // 막대 차트 설정
    private void setBarChart(List<itemModel> itemList2) {

        chart1.clearChart();

        chart1.addBar(new BarModel(2.7f, 0xFF123456));
        chart1.addBar(new BarModel("13", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("14", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("15", 20f, 0xFF56B7F1));
        chart1.addBar(new BarModel("16", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("17", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("11", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("13", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("17", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("15", 20f, 0xFF56B7F1));
        chart1.addBar(new BarModel("19", 10f, 0xFF56B7F1));
        chart1.addBar(new BarModel("17", 10f, 0xFF56B7F1));

        chart1.startAnimation();

    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.page_1:
                    transaction.replace(R.id.frameLayout, fragmentHome).commitAllowingStateLoss();

                    break;
                case R.id.page_2:
                    transaction.replace(R.id.frameLayout, fragmentStatistic).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

}
