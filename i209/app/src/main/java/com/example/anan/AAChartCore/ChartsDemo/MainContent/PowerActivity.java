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


import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.DrawChartWithAAOptionsActivity;
import com.example.anan.AAChartCore.ChartsDemo.AdditionalContent.PowerChartActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        Button button17;
        button17 = (Button) findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText et = new EditText(PowerActivity.this);

                EditText et_power = (EditText) findViewById(R.id.edt_power);
                EditText et_power2 = (EditText) findViewById(R.id.edt_power2);
                et_power.getText();
                et_power2.getText();
                String strXueya = String.valueOf(et_power.getText().toString());
                String strXueya2 = String.valueOf(et_power2.getText().toString());

                if ((!et_power.getText().toString().equals(""))&&(!et_power2.getText().toString().equals(""))) {
                double power = Double.valueOf(et_power.getText().toString());
                double power2= Double.valueOf(et_power2.getText().toString());
                AlertDialog.Builder builder = new AlertDialog.Builder(PowerActivity.this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("血压报告");

                if (power<10&&power2<10) {
                    Toast.makeText(getApplicationContext(), "血压过低请重新测量或联系家人！", Toast.LENGTH_LONG).show();

                } else if (power>= 90 && power <= 130&&power2>=60&&power2<=90) {
                    builder.setMessage("您的血压为正常血压，请继续保持哦！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog b = builder.create();
                    b.show();//显示对话框

                }

                else if (power>= 90 && power <= 130&&power2>=60&&power2<=80) {
                    builder.setMessage("您的血压为正常血压，请继续保持哦！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog b = builder.create();
                    b.show();//显示对话框
                }
                    else if (power>130 && power <=140&&power2>80&&power2<=90) {
                    builder.setMessage("警告！您血压偏高，有临界高血压症状，请多走动，注意饮食搭配！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog b = builder.create();
                    b.show();//显示对话框

                } else if (power>140||power2>90) {
                    builder.setMessage("警告！您血压偏高，请尽快联系家人，并及时就医!");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog b = builder.create();
                    b.show();//显示对话框


                }

                else {

                    Toast.makeText(getApplicationContext(), "输入内容有误", Toast.LENGTH_LONG).show();
                }
                BeanRecord4 data=new BeanRecord4();
                data.setXueya(strXueya);
                    data.setXueya2(strXueya2);
                SimpleDateFormat formatter=new SimpleDateFormat("MM/dd HH:mm");
                Date curDate=new Date(System.currentTimeMillis());
                data.setTime(formatter.format(curDate));
                MyDatabaseManager4 dbManager=new MyDatabaseManager4(getBaseContext());
                dbManager.addData(data);
            }}
        });
        Button button18;
        button18 = (Button) findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PowerActivity.this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("血压测量小贴士");
                builder.setMessage("①患者取坐位，被测的上臂应裸露，手掌向上平伸，肘部位于心脏水平，上肢胳膊与身躯呈45。\n" +
                                "\n"+
                        "②角，袖带下缘与肘前间隙间距为2—3厘米，充气至挠动脉搏动消失后再加4.0千帕(30毫米汞柱)，此时为最大充气水平。\n" +
                        "\n"+
                        "③如果加压过高会得到收缩压过高的结果。如果充气到达40.0千帕(300毫米汞柱)水平时，即会导致“气囊充气性高血压。\n" +
                        "\n"+
                        "④然后逐渐放气，速度为0.27千帕(2毫米汞柱)/秒，第一听诊音为收缩压，搏动音消失时为舒张压(旧制单位血压读数应精确到2毫米汞柱)。\n" +
                        "\n"+
                        "⑤充气压迫的时间不宜过长，否则易造成血压升高的假象。");
                builder.setPositiveButton("确定",  new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框

            }
        });

        Button button19;
        button19 = (Button) findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PowerActivity.this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("血压测量注意事项");
                builder.setMessage("①测量场所：场所的选择上，一般来说是诊所或者是患者家中，已经确定是高血压患者，可以直接在家中测量，防治因为紧张到医院检查导致血压升高。\n" +
                        "\n" +
                        "②测量体位：体位的选择上，可以坐着也可以躺着，患者舒服就行。一般来说，选择最多的还是坐位，如果是家中测量，也可以选择躺着。\n" +
                        "\n" +
                        "③测量仪器：仪器的选择上，血压计是主要选择。常用的血压机主要是水银柱式血压计，家中常用的也有电子血压计。但是，无论是哪种血压计，都要经过选择经过认证的，且符合国际标准的。\n" +
                        "\n" +
                        "④测量状态：测量血压时，一定要避免精神紧张或者不够安静等情况，要保持安静。");
                builder.setPositiveButton("确定",  new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框

            }
        });

        Button button20;
        button20 = (Button) findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PowerActivity.this);
                builder.setIcon(null);//设置图标, 这里设为空值
                builder.setTitle("血压异常小贴士");
                builder.setMessage("低血压：\n" +
                        "①首先要加强锻炼、增强体质。每天坚持体育锻炼是防治低血压的有效措施。对于老年人，可以选择些强度不大的运动，比如散步、骑自行车、游泳、太极拳、广场舞等；\n" +
                        "\n" +"②其次要合理饮食、加强营养。在生活中合理搭配膳食，不偏食，以保证摄入全面充足的营养物质。夏季是低血压的高发季节，血压偏低的人要注意多饮水、补充血容量，适当多吃一些含盐量高的食物，防止血压过度下降。\n" +
                        "\n" +"③低血压者应避免长时间在闷热的环境中站立。老年人要特别重视体位性低血压的预防。\n" +
                        "\n" +"④平时要注意变更体位时的速度，比如起床或由坐位起立时不要过急过快。如果症状长期得不到缓解，就要及时去医院诊治。\n" +
                        "高血压：\n" +
                        "\n" + "①减轻体重，减轻胰岛素抵抗，低盐、低脂饮食，补充钙和钾盐，多吃绿色蔬菜和水果，补充膳食纤维。\n" +
                        "\n" +"②保持良好的睡眠，保持心情舒畅。经常监测血压，根据血压情况，及时调整降压药物的用量。 \n" +
                        "\n" +"③高血压的治疗是综合性的，如果血压在150/90mmHg以上，建议口服降压药物，同时要改善生活方式，注意坚持运动。\n");
                builder.setPositiveButton("确定",  new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "确定成功！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(PowerActivity.this, "取消成功！", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog b = builder.create();
                b.show();//显示对话框

            }
        });

        Button buttonpower;
        buttonpower = (Button) findViewById(R.id.buttonpower);
        buttonpower.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.PowerActivity.this, PowerecordActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });
        Button buttonpowerchart;
        buttonpowerchart = (Button) findViewById(R.id.buttonpowerchart);
        buttonpowerchart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.setClass(com.example.anan.AAChartCore.ChartsDemo.MainContent.PowerActivity.this, PowerChartActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称

                startActivity(intent);

            }


        });
    }
}
