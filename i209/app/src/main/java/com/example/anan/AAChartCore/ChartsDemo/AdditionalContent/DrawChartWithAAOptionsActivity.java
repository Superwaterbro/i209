package com.example.anan.AAChartCore.ChartsDemo.AdditionalContent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartAlignType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartAnimationType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartFontWeightType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartLayoutType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartLineDashStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartStackingType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartView;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAOptionsConstructor;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartVerticalAlignType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAAnimation;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAChart;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAColumn;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AACrosshair;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AADataLabels;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAItemStyle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AALabel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AALabels;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AALegend;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAMarker;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPane;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPlotBandsElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPlotLinesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPlotOptions;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AASeries;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAStyle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AASubtitle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AATitle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AATooltip;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAXAxis;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAYAxis;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AAColor;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AAGradientColor;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AALinearGradientDirection;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.CustomStyleChartActivity;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.MyDatabaseHelper;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.R;
import com.example.anan.AAChartCore.ChartsDemo.MainContent.TemActivity;


import java.util.HashMap;
import java.util.Map;

public class DrawChartWithAAOptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_chart_with_aaoptions);

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
        double lala[] = new double[100];
        Object[] o = new Object[14];
        String baba[]=new String[100];
        String[] u=new String[14];
        MyDatabaseHelper helper = new MyDatabaseHelper(DrawChartWithAAOptionsActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("record", null, null, null, null, null, null);
        //调用moveToFirst()将数据指针移动到第一行的位置。
        if (cursor.moveToFirst()) {
            do {
                //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                Double tiwen = cursor.getDouble(cursor.getColumnIndex("tiwen"));
                String time = cursor.getString(cursor.getColumnIndex("time"));



                    lala[i] = tiwen;
                    o[i] = tiwen;

                    baba[i]=time;

                    u[i]=time;
                    i++;

            } while (cursor.moveToNext());
        }
        cursor.close();

        Map map1 = new HashMap();
        map1.put("value", 35.5);
        map1.put("color", "#1e90ff");
        Map map2 = new HashMap();
        map2.put("value", 37.3);
        map2.put("color", "#04d69f");
        Map map3 = new HashMap();
        map3.put("value", 38.5);
        map3.put("color", "#ffd066");
        Map map4 = new HashMap();
        map4.put("color", "#ef476f");
        Map[] zonesArr = new Map[]{map1, map2, map3, map4};

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Areaspline)//图形类型
                .dataLabelsEnabled(true)

                .title("近十次温度变化趋势(单位：℃）")
                .titleFontSize(20f)
                .titleFontColor("rgba(30, 144, 255,1)")
                .categories(u)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("温度")
                                .data(o)
                                .fillOpacity(0.5f)
                                .lineWidth(5f)
                                .threshold((35f))
                                .zones(zonesArr)
                });

        AAOptions aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        AAPlotLinesElement[] aaPlotLinesElementsArr = {
                new AAPlotLinesElement()
                        .color("#1e90ff")//颜色值(16进制)
                        .dashStyle(AAChartLineDashStyleType.LongDashDotDot)//样式：Dash,Dot,Solid等,默认Solid
                        .width((1f)) //标示线粗细
                        .value((35.5f)) //所在位置

                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("正常体温")
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
                        .value((37.3f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("低烧")
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
                        .value((38.5f)) //所在位置
                        .zIndex((1)) //层叠,标示线在图表中显示的层叠级别，值越大，显示越向前
                        .label(new AALabel()
                        .text("高烧")
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