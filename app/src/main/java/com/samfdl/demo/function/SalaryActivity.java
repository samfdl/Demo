package com.samfdl.demo.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.samfdl.demo.R;

import java.text.DecimalFormat;

public class SalaryActivity extends AppCompatActivity {
    private Spinner mCity;

    private TextView mSalary;
    private TextView mSalaryHou;

    private TextView mGongjijinJishu;
    private TextView mGongjijin;

    private TextView mGongjijinlv;
    private TextView mYiliaolv;
    private TextView mShiyelv;

    private TextView mBaoxianJishu;
    private TextView mYaolao;
    private TextView mYiliao;
    private TextView mShiye;

    private TextView mBuzhu;
    private TextView mSuodeshui;
    private TextView mKouchuzongji;

    private DecimalFormat format = new DecimalFormat("#.##");

    private double salary;
    private double salaryHou;

    private String[] gongjijinlvS;
    private String[] yiliaolvS;
    private String[] shiyelvS;
    private double[] gongjijinlvI = {0.12, 0.07, 0.13, 0, 0.11, 0.08};
    private boolean isBeijing = true;
    private double gongjijinlv = 0.12;
    private double yanglaolv = 0.08;

    private double gongjijinJishu;
    private double gongjijin;
    private double baoxianJishu;
    private double yanglao;
    private double yiliao;
    private double shiye;
    private double buzhu;
    private double suodeshui;
    private double kouchuzongji;

    public void onClickPay(View view) {
        final EditText payInput = (EditText) getLayoutInflater().inflate(
                R.layout.activity_function_salary_input, null);
        new AlertDialog.Builder(this).setTitle(R.string.function_salary_pay).setView(payInput)
                .setPositiveButton(R.string.function_salary_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = payInput.getText().toString();
                        if (text.length() > 0) {
                            salary = Double.valueOf(text);
                            if (gongjijinJishu == 0) {
                                gongjijinJishu = salary;
                                gongjijinJisuan();
                            }

                            if (baoxianJishu == 0) {
                                baoxianJishu = salary;
                                yanglao = baoxianJishu * yanglaolv;
                                baoxianJisuan(isBeijing);
                            }
                            mSalary.setText(format.format(salary));
                            jisuan();
                        }
                    }
                }).create().show();
    }

    public void onClickGongjijin(View view) {
        final EditText payInput = (EditText) getLayoutInflater().inflate(
                R.layout.activity_function_salary_input, null);
        new AlertDialog.Builder(this).setTitle(R.string.function_salary_gongjijin)
                .setView(payInput)
                .setPositiveButton(R.string.function_salary_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = payInput.getText().toString();
                        if (text.length() > 0) {
                            gongjijin = Double.valueOf(text);
                            gongjijinJishu = gongjijin / gongjijinlv;
                            gongjijinJisuan();
                            jisuan();
                        }
                    }
                }).create().show();
    }

    public void onClickYanglao(View view) {
        final EditText payInput = (EditText) getLayoutInflater().inflate(
                R.layout.activity_function_salary_input, null);
        new AlertDialog.Builder(this).setTitle(R.string.function_salary_yanglao)
                .setView(payInput)
                .setPositiveButton(R.string.function_salary_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = payInput.getText().toString();
                        if (text.length() > 0) {
                            yanglao = Double.valueOf(text);
                            baoxianJishu = yanglao / yanglaolv;
                            baoxianJisuan(isBeijing);
                            jisuan();
                        }
                    }
                }).create().show();
    }

    public void onClickBuzhu(View view) {
        final EditText payInput = (EditText) getLayoutInflater().inflate(
                R.layout.activity_function_salary_input, null);
        new AlertDialog.Builder(this).setTitle(R.string.function_salary_buzhu)
                .setView(payInput)
                .setPositiveButton(R.string.function_salary_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = payInput.getText().toString();
                        if (text.length() > 0) {
                            buzhu = Double.valueOf(text);
                            mBuzhu.setText(format.format(buzhu));
                            jisuan();
                        }
                    }
                }).create().show();
    }

    private void gongjijinJisuan() {
        gongjijin = gongjijinJishu * gongjijinlv;
        mGongjijinJishu.setText(format.format(gongjijinJishu));
        mGongjijin.setText(format.format(gongjijin));
    }

    private void baoxianJisuan(boolean isBeijing) {
        if (isBeijing) {
            yiliao = baoxianJishu * 0.02 + 3;
            shiye = baoxianJishu * 0.002;
        } else {
            yiliao = baoxianJishu * 0.02;
            shiye = baoxianJishu * 0.01;
        }
        mBaoxianJishu.setText(format.format(baoxianJishu));
        mYaolao.setText(format.format(yanglao));
        mYiliao.setText(format.format(yiliao));
        mShiye.setText(format.format(shiye));
    }

    private void jisuan() {
        suodeshui = salary + buzhu - (gongjijin + yanglao + yiliao + shiye)
                - 3500;
        if (suodeshui <= 0) {
            suodeshui = 0;
        } else if (suodeshui <= 1500) {
            suodeshui = suodeshui * 0.03;
        } else if (suodeshui <= 4500) {
            suodeshui = suodeshui * 0.1 - 105;
        } else if (suodeshui <= 9000) {
            suodeshui = suodeshui * 0.2 - 555;
        } else if (suodeshui <= 35000) {
            suodeshui = suodeshui * 0.25 - 1005;
        } else if (suodeshui <= 55000) {
            suodeshui = suodeshui * 0.3 - 2755;
        } else if (suodeshui <= 80000) {
            suodeshui = suodeshui * 0.35 - 5505;
        } else {
            suodeshui = suodeshui * 0.45 - 13505;
        }

        kouchuzongji = gongjijin + yanglao + yiliao + shiye + suodeshui;
        salaryHou = salary - kouchuzongji;

        mSuodeshui.setText(format.format(suodeshui));
        mKouchuzongji.setText(format.format(kouchuzongji));
        mSalaryHou.setText(format.format(salaryHou));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_salary);

        mCity = (Spinner) findViewById(R.id.city);
        mCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int id,
                                       long arg3) {
                mGongjijinlv.setText(gongjijinlvS[id]);
                mYiliaolv.setText(yiliaolvS[id]);
                mShiyelv.setText(shiyelvS[id]);
                gongjijinlv = gongjijinlvI[id];
                gongjijinJisuan();
                if (id == 0) {
                    isBeijing = true;
                } else {
                    isBeijing = false;
                }
                baoxianJisuan(isBeijing);
                jisuan();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mSalary = findViewById(R.id.pay);
        mSalaryHou = findViewById(R.id.realpay);

        mGongjijinJishu = findViewById(R.id.gongjijinjishu);
        mGongjijin = findViewById(R.id.gongjijin);

        mGongjijinlv = findViewById(R.id.gongjijinlv);
        mYiliaolv = findViewById(R.id.yiliaolv);
        mShiyelv = findViewById(R.id.shiyelv);

        mBaoxianJishu = findViewById(R.id.baoxianjishu);
        mYaolao = findViewById(R.id.yanglao);
        mYiliao = findViewById(R.id.yiliao);
        mShiye = findViewById(R.id.shiye);

        mBuzhu = findViewById(R.id.buzhu);
        mSuodeshui = findViewById(R.id.suodeshui);
        mKouchuzongji = findViewById(R.id.kouchuzongji);

        gongjijinlvS = getResources().getStringArray(R.array.function_salary_gongjijin);
        yiliaolvS = getResources().getStringArray(R.array.function_salary_yiliao);
        shiyelvS = getResources().getStringArray(R.array.function_salary_shiye);
    }
}