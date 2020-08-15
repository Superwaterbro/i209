package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartModel;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AAChartView;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartCreator.AASeriesElement;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartAnimationType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartVerticalAlignType;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AADataLabels;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAPie;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAStyle;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AATooltip;
import com.example.anan.AAChartCore.AAChartCoreLib.AAOptionsModel.AAWaterfall;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AAColor;
import com.example.anan.AAChartCore.AAChartCoreLib.AATools.AAGradientColor;


import java.util.HashMap;
import java.util.Map;

public class SpecialChartActivity extends AppCompatActivity {
    private AAChartModel aaChartModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AAChartView aaChartView = (AAChartView) findViewById(R.id.AAChartView);

        Intent intent = getIntent();
        String chartType = intent.getStringExtra("chartType");

        aaChartModel = configureChartModelWithChartType(chartType);


        aaChartView.aa_drawChartWithChartModel(aaChartModel);


    }

    AAChartModel configureChartModelWithChartType(String chartType) {
        switch (chartType) {

            case AAChartType.Pie:
                return configurePieChart();

            case AAChartType.Line:
                return configureLineChart();
            case AAChartType.Area:
                return configureAreaChart();

            default:
                break;
        }

        return configurePieChart();
    }


    AAChartModel configurePieChart() {
        return new AAChartModel()
                .chartType(AAChartType.Pie)
                .backgroundColor("#ffffff")
                .title("LANGUAGE MARKET SHARES JANUARY,2020 TO MAY")
                .subtitle("virtual data")
                .dataLabelsEnabled(true)//是否直接显示扇形图数据
                .yAxisTitle("℃")
                .series(new AAPie[]{
                                new AAPie()
                                        .name("Language market shares")
                                        .innerSize("20%")
                                        .size(150f)
                                        .dataLabels(new AADataLabels()
                                                .enabled(true)
                                                .useHTML(true)
                                                .distance(5f)
                                                .format("<b>{point.name}</b>: <br> {point.percentage:.1f} %"))
                                        .data(new Object[][]{
                                        {"Java", 67},
                                        {"Swift", 999},
                                        {"Python", 83},
                                        {"OC", 11},
                                        {"Go", 30},
                                })
                                ,
                        }
                );
    }


    AAChartModel configureLineChart() {
        return new AAChartModel()
                .chartType(AAChartType.Line)//图形类型
                .animationType(AAChartAnimationType.Bounce)//图形渲染动画类型为"bounce"
                .title("STEP LINE CHART")//图形标题
                .subtitle("2020/08/08")//图形副标题
                .dataLabelsEnabled(false)//是否显示数字
                .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)//折线连接点样式
                .markerRadius(7f)//折线连接点半径长度,为0时相当于没有折线连接点
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("Berlin")
                                .data(new Object[]{
                                        450, 432, 401, 454, 590, 530, 510})
                                .step("right")//设置折线样式为直方折线,折线连接点位置靠右👉
                        ,
                        new AASeriesElement()
                                .name("New York")
                                .data(new Object[]{
                                        220, 282, 201, 234, 290, 430, 410})
                                .step("center")//设置折线样式为直方折线,折线连接点位置居中
                        ,
                        new AASeriesElement()
                                .name("Tokyo")
                                .data(new Object[]{
                                        120, 132, 101, 134, 90, 230, 210})
                                .step("left")//设置折线样式为直方折线,折线连接点位置靠左👈
                        ,
                });
    }

    AAChartModel configureAreaChart() {
        return new AAChartModel()
                .chartType(AAChartType.Area)//图形类型
                .animationType(AAChartAnimationType.Bounce)//图形渲染动画类型为"bounce"
                .title("STEP AREA CHART")//图形标题
                .subtitle("2049/08/08")//图形副标题
                .dataLabelsEnabled(false)//是否显示数字
                .markerSymbolStyle(AAChartSymbolStyleType.InnerBlank)//折线连接点样式
                .markerRadius(0f)//折线连接点半径长度,为0时相当于没有折线连接点
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("Berlin")
                                .data(new Object[]{
                                        450, 432, 401, 454, 590, 530, 510})
                                .step(true)//设置折线样式为直方折线,折线连接点位置靠左👈
                        ,
                        new AASeriesElement()
                                .name("New York")
                                .data(new Object[]{
                                        220, 282, 201, 234, 290, 430, 410})
                                .step(true)//设置折线样式为直方折线,折线连接点位置靠左👈
                        ,
                        new AASeriesElement()
                                .name("Tokyo")
                                .data(new Object[]{
                                        120, 132, 101, 134, 90, 230, 210})
                                .step(true)//设置折线样式为直方折线,折线连接点位置靠左👈
                        ,
                });
    }

}