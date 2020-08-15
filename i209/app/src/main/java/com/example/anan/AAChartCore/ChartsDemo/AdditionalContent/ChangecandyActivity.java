package com.example.anan.AAChartCore.ChartsDemo.AdditionalContent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartView;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartAnimationType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartLineDashStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAChart;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AADataElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAScrollablePlotArea;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AASubtitle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AATitle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAXAxis;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AAJSStringPurer;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper2;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;


public class ChangecandyActivity extends AppCompatActivity {
    private AAChartView aaChartView1;
    private AAChartModel aaChartModel;
    private AAOptions aaOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changecandy);
        Button button8;
        button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangecandyActivity.this, ScrollableChartActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        Button button9;
        button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChangecandyActivity.this, ChangepowerActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        aaChartView1 = findViewById(R.id.AAChartView1);

        AAChartModel aaChartModel = configureChartModel();
        if (aaOptions == null) {
            aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        }

        aaChartView1.aa_drawChartWithChartOptions(aaOptions);
    }


    private AAChartModel configureChartModel() {
        Intent intent = getIntent();
        String chartType = intent.getStringExtra("chartType");
        int position = intent.getIntExtra("position",0);

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(chartType)

                .yAxisTitle("血糖")
                .legendEnabled(false)
                .yAxisGridLineWidth(0f)
                .xAxisGridLineWidth(1f)
                .scrollablePlotArea(
                        new AAScrollablePlotArea()
                                .minWidth(1000)
                                .scrollPositionX(1f)


                );
        this.aaChartModel = aaChartModel;

        configureTheStyleForDifferentTypeChart();
        return aaChartModel;
    }


    void configureTheStyleForDifferentTypeChart() {

        configureLineChartAndSplineChartStyle();


    }


    private void configureLineChartAndSplineChartStyle() {
        int i = 0;
        double lala2[] = new double[14];
        Object[] o2 = new Object[14];
        String baba[]=new String[14];
        String[] u=new String[14];
        MyDatabaseHelper2 helper = new MyDatabaseHelper2(ChangecandyActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("record", null, null, null, null, null, null);
        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()) {
            do {
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                Double xuetang = cursor.getDouble(cursor.getColumnIndex("xuetang"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                Log.d("CandyChartActivity", "candy name is " + xuetang);

                lala2[i] = xuetang;
                o2[i] = xuetang;
                baba[i]=time;

                u[i]=time;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        cursor.close();
        aaChartModel
                .title("血糖变化趋势")
                .titleFontSize(20f)
                .titleFontColor("rgba(30, 144, 255,1)")
                .chartType(AAChartType.Areaspline)
                .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)//设置折线连接点样式为:边缘白色
                .markerRadius(6f)
                .dataLabelsEnabled(true)

                .categories(u);


        AASeriesElement element1 = new AASeriesElement()
                .name("血糖")
                .threshold((8f))
                .lineWidth(7f)
                .color("#B0E0E6")
                .data(o2);


        aaChartModel
                .animationType(AAChartAnimationType.SwingFromTo)
                .series(new AASeriesElement[]{element1});

    }

}



