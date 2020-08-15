package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;


import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.DrawChartWithAAOptionsActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TemActivity extends AppCompatActivity {
    private static final String kChartTypeKey = "chartType";
    private static TemActivity instance;
    private String chartType;
    private MyDatabaseHelper helper;

    public double tem2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tem);
        instance = this;
        Button button10;
        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            final EditText et = new EditText(TemActivity.this);

                                            EditText et_amount = (EditText) findViewById(R.id.edt_amount);


                                            et_amount.getText();

                                            String strTiwen = String.valueOf(et_amount.getText().toString());
                                            if(!et_amount.getText().toString().equals("")){
                                            double tem2 = Double.valueOf(et_amount.getText().toString());





                                            AlertDialog.Builder builder = new AlertDialog.Builder(TemActivity.this);
                                            builder.setIcon(null);//设置图标, 这里设为空值
                                            builder.setTitle("体温报告");

                                            if (tem2 < 35.5) {
                                                Toast.makeText(getApplicationContext(), "体温过低请重新测量或联系家人！", Toast.LENGTH_LONG).show();

                                            } else if (tem2 >= 35.5 && tem2 <= 37.3) {
                                                builder.setMessage("您的体温为正常体温，请继续保持哦！");
                                                builder.setPositiveButton("确定", new OnClickListener() {
                                                    public void onClick(DialogInterface arg0, int arg1) {


                                                        FileOutputStream fos;
                                                        try {
                                                            EditText et_amount = (EditText) findViewById(R.id.edt_amount);
                                                            String saveInfo=et_amount.getText().toString().trim();
                                                            fos=openFileOutput("data.txt",MODE_APPEND);//把文件输出到data中
                                                            fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                                                            fos.close();
                                                        }
                                                        catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        Toast.makeText(TemActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                builder.setNegativeButton("取消", new OnClickListener() {
                                                    public void onClick(DialogInterface arg0, int arg1) {

                                                        String content="";
                                                        try {
                                                            FileInputStream fis=openFileInput("data.txt");
                                                            byte [] buffer=new  byte[fis.available()];
                                                            fis.read(buffer);
                                                            content=new String(buffer);
                                                            fis.close();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        Toast.makeText(TemActivity.this,"保存的数据是"+content, Toast.LENGTH_SHORT).show();


                                                    }
                                                });
                                                AlertDialog b = builder.create();
                                                b.show();//显示对话框

                                            } else if (tem2 > 37.3 && tem2 < 38.5) {
                                                builder.setMessage("警告！您体温偏高，有低烧症状，请多喝水注意休息！");
                                                builder.setPositiveButton("确定", new OnClickListener() {
                                                    public void onClick(DialogInterface arg0, int arg1) {

                                                        Toast.makeText(TemActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                builder.setNegativeButton("取消", new OnClickListener() {
                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                        Toast.makeText(TemActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                AlertDialog b = builder.create();
                                                b.show();//显示对话框

                                            } else if (tem2 >= 38.5) {
                                                builder.setMessage("警告！您已高烧，请尽快联系家人，并及时就医!");
                                                builder.setPositiveButton("确定", new OnClickListener() {

                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                        Toast.makeText(TemActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                                builder.setNegativeButton("取消", new OnClickListener() {
                                                    public void onClick(DialogInterface arg0, int arg1) {
                                                        Toast.makeText(TemActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                                AlertDialog b = builder.create();
                                                b.show();//显示对话框


                                            }

                                            else {

                                                Toast.makeText(getApplicationContext(), "输入内容有误", Toast.LENGTH_LONG).show();
                                            }
                                            BeanRecord data=new BeanRecord();

                                            data.setTiwen(strTiwen);
                                                SimpleDateFormat formatter=new SimpleDateFormat("MM/dd HH:mm");
                                            Date curDate=new Date(System.currentTimeMillis());
                                            data.setTime(formatter.format(curDate));

                                            MyDatabaseManager dbManager=new MyDatabaseManager(getBaseContext());
                                            dbManager.addData(data);
                                        }}
                                    });
                Button button11;
                button11 = (Button) findViewById(R.id.button11);
                button11.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TemActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("体温测量小贴士");
                        builder.setMessage("方法/步骤1:\n" +
                                "首先，使用水银温度计之前，要用酒精棉对温度计做整体的杀毒，确保无细菌交叉感染，使用起来才安全。\n" +
                                "方法/步骤2:\n" +
                                "要将水银温度计刷一刷，让水银柱回到35摄氏度的刻度线以下位置，以免测量不准确。\n" +
                                "方法/步骤3:\n" +
                                "放入口腔测量体温，含住温度计3-5分钟，再取出看刻度即可。\n" +
                                "方法/步骤4:\n" +
                                "接着正对水银温度计，看水银柱到了刻度线的哪里，读取温度，并根据温度判断是否属于正常体温。\n" +
                                "方法/步骤5:\n" +
                                "然后将温度计再次消毒，干燥后用专门的收纳盒收纳起来，以免不小心落地，水银逸散出来，造成污染。");
                        builder.setPositiveButton("确定",  new OnClickListener(){

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(TemActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(TemActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    }
                });

                Button button12;
                button12 = (Button) findViewById(R.id.button12);
                button12.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TemActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("体温异常小贴士");
                        builder.setMessage("1、原因不明的发热不要急于退热。\n" +
                                "对热度不高且发热原因不明者，通常不主张急于退热，以免掩盖病情，降低机体抵抗力。应集中精力尽早找到病因。\n" +
                                "2、宜及时退热的几种情况。\n" +
                                "体温过高，如达39℃以上，，可考虑退热。 肿瘤性发热将加重患者体内物质的消耗，对原有心肌损伤的患者，发热会加重心肌负荷，诱发心力衰竭的发生，如遇到这些病例也可及时退热。\n" +
                                "3、加强对高热或持久发热患者的护理。\n" +
                                "发热期间应补充易消化的营养食物，要给予足够的糖和维生素B、维生素C的供应，注意纠正水、电解质和酸碱平衡的紊乱，尤其要补给充足的水分，预防脱水和虚脱的发生。\n" +
                                "4、选择适宜的退热措施。\n" +
                                "发热不是孤立的症状或病理过程，所以必须针对发热病因采取有效的措施。 致热原性发热，应根据发热机制及解热剂药理特性，选用合适的解热措施。");
                        builder.setPositiveButton("确定",  new OnClickListener(){

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(TemActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(TemActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    }
            });
        Button buttonlist;
        buttonlist = (Button) findViewById(R.id.buttonlist);
        buttonlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.TemActivity.this, RecordActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
    }
});
        Button buttonchart;
        buttonchart = (Button) findViewById(R.id.buttonchart);
        buttonchart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.TemActivity.this, DrawChartWithAAOptionsActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称

                startActivity(intent);

            }


        });
    }
}