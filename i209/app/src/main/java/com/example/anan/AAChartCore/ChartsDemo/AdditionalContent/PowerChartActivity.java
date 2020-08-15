package com.example.anan.AAChartCore.ChartsDemo.AdditionalContent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartView;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartLineDashStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AALabel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPlotLinesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAStyle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAYAxis;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper4;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;

import java.util.HashMap;
import java.util.Map;

public class PowerChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_chart);
        Intent intent = getIntent();
        String chartType = intent.getStringExtra("chartType");

        AAOptions aaOptions = configureTheChartOptions(chartType);

        AAChartView aaChartView = findViewById(R.id.AAChartView);
        aaChartView.aa_drawChartWithChartOptions(aaOptions);


    }

    private AAOptions configureTheChartOptions(String chartType) {


        return configureAAPlotLinesForChart();


    }


    private AAOptions configureAAPlotLinesForChart() {
        int i = 0;
        double lala3[] = new double[14];
        Object[] o3 = new Object[14];
        double lala4[] = new double[14];
        Object[] o4 = new Object[14];

        String baba[]=new String[14];
        String[] u=new String[14];

        MyDatabaseHelper4 helper = new MyDatabaseHelper4(PowerChartActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("record", null, null, null, null, null, null);
        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()) {
            do {
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                Double xueya = cursor.getDouble(cursor.getColumnIndex("xueya"));
                Double xueya2 = cursor.getDouble(cursor.getColumnIndex("xueya2"));
                String time = cursor.getString(cursor.getColumnIndex("time"));


if(i<10) {
    lala3[i] = xueya;

    o3[i] = xueya;
    lala4[i] = xueya2;

    o4[i] = xueya2;


    baba[i] = time;

    u[i] = time;
    i++;
}
            } while (cursor.moveToNext());
        }
        cursor.close();



        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)//图形类型
                .dataLabelsEnabled(true)
                .title("近十次血压变化趋势")
                .titleFontSize(20f)
                .titleFontColor("rgba(30, 144, 255,1)")
                .categories(u)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("收缩压")
                                .data(o3)
                                .fillOpacity(0.5f)
                                .lineWidth(5f)
                                .threshold((80f))

                        ,
                        new AASeriesElement()
                                .name("舒张压")
                                .data(o4)
                                .fillOpacity(0.5f)
                                .lineWidth(5f)
                                .threshold((60f))

                });

        AAOptions aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        AAPlotLinesElement[] aaPlotLinesElementsArr = {
                new AAPlotLinesElement()
                        .color("#1e90ff")//颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.LongDashDotDot)//样式：Dash,Dot,Solid等,默认Solid
                        .width((1f)) //标示线粗细
                        .value((90f)) //所在位置

                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("正常血压")
                        .style(new AAStyle()
                                .color("#1e90ff")
                                .fontWeight("bold")
                        )
                )
                ,
                new AAPlotLinesElement()
                        .color("#ffd066")//颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.LongDashDot)//样式：Dash,Dot,Solid等,默认Solid
                        .width((1f)) //标示线粗细
                        .value((130f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("血压偏高")
                        .style(new AAStyle()
                                .color("#ffd066")
                                .fontWeight("bold")
                        )
                )
                ,
                new AAPlotLinesElement()
                        .color("#ef476f")//颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.LongDash)//样式：Dash,Dot,Solid等,默认Solid
                        .width((1f)) //标示线粗细
                        .value((140f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("血压过高")
                        .style(new AAStyle()
                                .color("#ef476f")
                                .fontWeight("bold")
                        )
                )
                ,
        };

        AAYAxis aaYAxis = aaOptions.yAxis;
        aaYAxis.plotLines(aaPlotLinesElementsArr);
        return aaOptions;
    }
}
