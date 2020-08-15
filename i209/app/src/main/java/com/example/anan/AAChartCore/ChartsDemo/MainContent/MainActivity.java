package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anan.AAChartCore.AAChartCoreLib.AAChartEnum.AAChartType;
import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.DrawChartWithAAOptionsActivity;



public class MainActivity extends AppCompatActivity {
    private static final String  kChartTypeKey = "chartType";

    private String[] data = {


            /*特殊类型图表*/

            "Pie Chart---扇形图",


            "Step Line Chart--- 直方折线图",
            "Step Area Chart--- 直方折线填充图",


            /*自定义样式图表*/

            "AreaChartThreshold",

            "HexagonRadarChart",
            /*使用AAOptions绘制图表*/

            "AAPlotLinesForChart",

            "_DataLabels_XAXis_YAxis_Legend_Style",

            /*自定义 formatter 函数*/



            /*Double Charts Linked Work---双表联动*/

            /*Scrollable Chart---可滚动图表*/



    };

    String[] chartTypeArr = {

            /*特殊类型图表*/

            AAChartType.Pie,


            AAChartType.Line,
            AAChartType.Area,



            /*自定义样式图表*/

            "AreaChartThreshold",

            "HexagonRadarChart",
            /*使用AAOptions绘制图表*/

            "AAPlotLinesForChart",

            "_DataLabels_XAXis_YAxis_Legend_Style",


            /*自定义 formatter 函数*/

            /*执行由 JavaScript 字符串映射转换成的 js function 函数*/



            /*Double Charts Linked Work---双表联动*/
            "doubleChartsLinkedWork",
            /*Scrollable Chart---可滚动图表*/





    };
    private String chartType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                System.out.println(position);
             if (position <= 2) {/*特殊类型图表*/
                    goToSpecialChartActivity(position);

                } else if (position <= 4)  {/*自定义样式图表*/
                    goToCustomStyleChartActivity(position);
                } else if (position <= 6){/*使用AAOptions绘制图表*/
                    goToDrawChartWithAAOptionsActivity(position);
                }
            }

        });
    }


    void goToSpecialChartActivity(int position) {
        Intent intent = new Intent(this, SpecialChartActivity.class);
        intent.putExtra(kChartTypeKey, chartTypeArr[position]);

        startActivity(intent);
    }

    void goToCustomStyleChartActivity(int position) {
        Intent intent = new Intent(this, CustomStyleChartActivity.class);
        intent.putExtra(kChartTypeKey, chartTypeArr[position]);

        startActivity(intent);
    }



    void goToDrawChartWithAAOptionsActivity(int position) {
        Intent intent = new Intent(this, DrawChartWithAAOptionsActivity.class);
        intent.putExtra(kChartTypeKey, chartTypeArr[position]);

        startActivity(intent);
    }



    }



