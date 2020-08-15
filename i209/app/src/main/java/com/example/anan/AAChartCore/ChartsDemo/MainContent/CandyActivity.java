package com.example.anan.AAChartCore.ChartsDemo.MainContent;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.CandyChartActivity;
import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.DrawChartWithAAOptionsActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Double.NaN;

public class CandyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candy);
        Button button13;
        button13 = (Button) findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText et = new EditText(CandyActivity.this);
                EditText et_candy = (EditText) findViewById(R.id.edt_candy);
                et_candy.getText();
                String strXuetang = String.valueOf(et_candy.getText().toString());
                if (!et_candy.getText().toString().equals("")) {


                    double tem3 = Double.valueOf(et_candy.getText().toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(CandyActivity.this);
                    builder.setIcon(null);//设置图标, 这里设为空值
                    builder.setTitle("血糖报告");

                    if (tem3 < 1) {
                        Toast.makeText(getApplicationContext(), "血糖过低请重新测量或联系家人！", Toast.LENGTH_LONG).show();

                    } else if (tem3 >= 1 && tem3 < 3.9) {
                        builder.setMessage("警告！您的血糖偏低，有低血糖症状，请补充营养，保持充足睡眠！");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();


                        b.show();//显示对话框
                    } else if (tem3 >= 3.9 && tem3 <= 6.1) {
                        builder.setMessage("您的血糖为正常血糖，请继续保持哦！");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    } else if (tem3 > 6.1 && tem3 < 7) {
                        builder.setMessage("警告！您血糖偏高，有糖尿病前期症状，请注意饮食多休息！");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    } else if (tem3 >= 7) {
                        builder.setMessage("警告！您血糖过高，已有糖尿病症状，请尽快联系家人，并及时就医!");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框


                    } else {
                        Toast.makeText(getApplicationContext(), "输入内容有误", Toast.LENGTH_LONG).show();

                    }
                    if (tem3 != NaN) {
                        BeanRecord2 data = new BeanRecord2();

                        data.setXuetang(strXuetang);
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd HH:mm");
                        Date curDate = new Date(System.currentTimeMillis());
                        data.setTime(formatter.format(curDate));
                        MyDatabaseManager2 dbManager = new MyDatabaseManager2(getBaseContext());
                        dbManager.addData(data);
                    }
                }
            }});

                Button button14;
                button14 = (Button) findViewById(R.id.button14);
                button14.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CandyActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("血糖测量步骤");
                        builder.setMessage("①调整血糖仪的代码使其与您现在使用的试纸的代码相同，注意不同时间购买的试纸有不同的代码，所以必须先调整血糖仪的代码。\n" +
                                "②洗手，用酒精消毒采血的手指。\n" +
                                "\n" + "③手臂下垂30秒，以便使血液充分流到手指。\n" +
                                "\n" + "4将采血针头装入刺指笔中，根据手指皮肤厚度选择穿刺深度，刺破手指取适量血。\n" +
                                "\n" + "⑤待血糖仪指示取血后，将血滴在血糖试纸指示孔上。\n" +
                                "\n" + "⑥把血糖试纸插入血糖仪中。注意有的血糖仪需先将试纸插入血糖仪中，再将血滴在试纸上。\n" +
                                "\n" + "⑦几秒或十几秒钟之后，从血糖仪上读出血糖值。\n" +
                                "\n" + "⑧在记录本上记录血糖值和监测时间。");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    }
                });

                Button button15;
                button15 = (Button) findViewById(R.id.button15);
                button15.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CandyActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("血糖测量注意事项");
                        builder.setMessage("①血量不够、血糖试纸超过有效期、手指消毒酒精未干、未将血糖仪代码调到和试纸一样时，都会影响检测的准确性。\n" +
                                "\n" + "②手指消毒后，一定要等酒精挥发干燥后再采血。\n" +
                                "\n" + "③采血部位要交替轮换，不要长期刺扎一个地方，以免形成疤痕。在手指侧边采血疼痛较轻，而且血量足。\n" +
                                "\n" + "④妥善保管用过的酒精棉球、针头等，最好集中送到社区卫生站处理。\n" +
                                "\n" + "⑤血糖仪要放置在干燥清洁处，不要让小孩、宠物触及、玩耍。\n" +
                                "\n" + "⑥血糖仪都应该有售后服务，要定期到购买的商店或厂家指定处校正血糖仪是否准确，到医院与抽血检查结果对比也可知道其准确性。\n" +
                                "\n" + "⑦如果血糖水平还在调整阶段，例如，刚刚开始使用胰岛素进行治疗，或血糖控制较差，每天应该监测4~7次，并且选择的监测时间点是有代表意义的。");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框

                    }
                });
                Button button16;
                button16 = (Button) findViewById(R.id.button16);
                button16.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CandyActivity.this);
                        builder.setIcon(null);//设置图标, 这里设为空值
                        builder.setTitle("血糖异常措施");
                        builder.setMessage("①饮食。控制血糖，最主要是从饮食方面控制血糖，一定要控制好主食，控制好热量的摄入。一般1天的主食量是5-6两的主食，多吃蔬菜。\n" +
                                "\n" + "②高蛋白。可以摄入高蛋白的食物，每天保证一个鸡蛋、半斤牛奶，瘦肉可以吃2两，像鱼、豆腐之类的可以多吃，即主食吃不饱可以多吃蔬菜和其它食物。\n" +
                                "\n" + "③运动。1天要保证半个小时的运动消耗，消耗热量控制血糖。最后，药物治疗。\n" +
                                "\n" + "④药物。如果血糖通过生活方式、饮食和运动还不能控制到正常，就要口服一些药物。最常用的就是磺脲类的加上双胍类的药物。\n" +
                                "\n" + "⑤胰岛素治疗。促进周围组织利用降糖之类的药物综合治疗。还有现在可以选择胰岛素治疗。如果口服药物控制不好，建议尽早用胰岛素控制血糖。 ");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(CandyActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog b = builder.create();
                        b.show();//显示对话框


                    }
                });
                Button buttoncandylist;
                buttoncandylist = (Button) findViewById(R.id.buttoncandylist);
                buttoncandylist.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.CandyActivity.this, CandyrecordActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                        startActivity(intent);
                    }
                });

                Button buttoncandychart;
                buttoncandychart = (Button) findViewById(R.id.buttoncandychart);
                buttoncandychart.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent();

                        intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.CandyActivity.this, CandyChartActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称

                        startActivity(intent);

                    }


                });
            }
        }