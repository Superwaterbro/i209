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
import com.example.anan.AAChartCore.ChartsDemo.MainContent.CandyActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper2;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;

import java.util.HashMap;
import java.util.Map;

public class CandyChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candy_chart);


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
        double lala2[] = new double[100];
        Object[] o2 = new Object[10];
        String baba[]=new String[100];
        String[] u=new String[10];
        MyDatabaseHelper2 helper = new MyDatabaseHelper2(CandyChartActivity.this);
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

        Map map1 = new HashMap();
        map1.put("value", 1);
        map1.put("color", "#1e90ff");
        Map map2 = new HashMap();
        map2.put("value", 3.9);
        map2.put("color", "#04d69f");
        Map map3 = new HashMap();
        map3.put("value", 6.1);
        map3.put("color", "#ffd066");
        Map map4 = new HashMap();
        map4.put("color", "#ef476f");
        Map[] zonesArr = new Map[]{map1, map2, map3, map4};

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)//图形类型
                .dataLabelsEnabled(true)
                .title("近十次血糖变化趋势")
                .titleFontSize(20f)
                .titleFontColor("rgba(30, 144, 255,1)")
                .categories(u)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("血糖")
                                .data(o2)
                                .fillOpacity(0.5f)
                                .lineWidth(5f)
                                .threshold((0f))
                                .zones(zonesArr)
                });

        AAOptions aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        AAPlotLinesElement[] aaPlotLinesElementsArr = {
                new AAPlotLinesElement()
                        .color("#1e90ff")//颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.LongDashDotDot)//样式：Dash,Dot,Solid等,默认Solid
                        .width((1f)) //标示线粗细
                        .value((1f)) //所在位置

                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("血糖偏低")
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
                        .value((3.9f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("正常血糖")
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
                        .value((6.1f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("血糖偏高")
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

