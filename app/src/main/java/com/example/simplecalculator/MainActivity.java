package com.example.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {


    ImageView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    ImageView btnMul, btnPlus, btnMinus, btnDiv, btnAc, btnModulo, btnDot, btnEqual, btnDelete;
    TextView input, output;
    String data = "";

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btnMul = findViewById(R.id.btn_multiplication);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnDiv = findViewById(R.id.btn_divide);
        btnAc = findViewById(R.id.btn_ac);
        btnEqual = findViewById(R.id.btn_equal);
        btnDot = findViewById(R.id.btn_dot);
        btnModulo = findViewById(R.id.btn_modulo);
        input = findViewById(R.id.displayInput);
        output = findViewById(R.id.answer);
        btnDelete = findViewById(R.id.btn_delete);

        btn0.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "0");
        });
        btn1.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "1");
        });
        btn2.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "2");
        });
        btn3.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "3");
        });
        btn4.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "4");
        });
        btn5.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "5");
        });
        btn6.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "6");
        });
        btn7.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "7");
        });
        btn8.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "8");
        });
        btn9.setOnClickListener(v -> {
            data = input.getText().toString();
            input.setText(data + "9");
        });
        
        btnDot.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText("");
            } else {
                stringFormat(".");
            }
        });

        btnPlus.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText(data + "+");
            } else {
                stringFormat("+");
            }
        });

        btnMinus.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText(data + "-");
            } else {
                stringFormat("-");
            }
        });

        btnMul.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText("");
            } else {
                stringFormat("*");
            }
        });

        btnDiv.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText("");
            } else {
                stringFormat("/");
            }
        });

        btnModulo.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText("");
            } else {
                stringFormat("%");
            }
        });

        btnAc.setOnClickListener(v -> {
            input.setText("");
            output.setText("");
        });

        btnEqual.setOnClickListener(v -> {

            data = input.getText().toString();


            if (data.isEmpty()) {
                input.setText("");
            } else {

                StringBuilder stringBuilder = new StringBuilder(data);
                String check = stringBuilder.substring(data.length() - 1, data.length());
                if (check.contains("/") || check.contains("*")
                        || check.contains("-") || check.contains("+")
                        || check.contains(".") || check.contains("%")) {
                    Toast.makeText(this, "Invalid Value", Toast.LENGTH_SHORT).show();
                } else {
                    data = data.replaceAll("%", "/100");

                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);

                    Scriptable scriptable = rhino.initStandardObjects();
                    String finalResult = rhino.evaluateString(scriptable, data, "Javascript", 1, null).toString();
                    output.setText(finalResult);
                }
            }
        });

        btnDelete.setOnClickListener(v -> {
            data = input.getText().toString();
            if (data.isEmpty()) {
                input.setText("");
            } else {
                StringBuilder sb = new StringBuilder(data);
                sb.deleteCharAt(data.length() - 1);
                data = String.valueOf(sb);
                input.setText(data);
            }
        });
    }

    private void stringFormat(String c) {
        StringBuilder stringBuilder = new StringBuilder(data);
        String check = stringBuilder.substring(data.length() - 1, data.length());
        if (check.contains("/") || check.contains("*")
                || check.contains("-") || check.contains("+")
                || check.contains(".") || check.contains("%")) {
            input.setText(data);
        } else {
            input.setText(String.format("%s%s", data, c));
        }
    }
}