package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_resultBmi, tv_resultComment;
    private EditText et_weight, et_heightFt, et_inches;
    private Button b_calcuate;
    private double wgt, totheight;
    private double result = 0;
    private int feet, inch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BMI Calculator");
        b_calcuate = (Button) findViewById(R.id.b_calculate);
        et_weight = (EditText) findViewById(R.id.et_weight);
        et_heightFt = (EditText) findViewById(R.id.et_heightFt);
        et_inches = (EditText) findViewById(R.id.et_inch);
        tv_resultBmi = (TextView) findViewById(R.id.tv_resultBmi);
        tv_resultComment = (TextView) findViewById(R.id.tv_resultComment);


        b_calcuate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_resultComment.setVisibility(view.GONE);
                tv_resultBmi.setVisibility(view.GONE);

                if (et_weight.getText().toString().equals("")) {
                    et_weight.setError("Hey! I need a value for weight!");
                    Toast.makeText(MainActivity.this, " Invalid Inputs for weights", Toast.LENGTH_SHORT).show();
                } else if (et_heightFt.getText().toString().equals("")) {
                    et_heightFt.setError("Hey! I need a value for height in feet!");
                    Toast.makeText(MainActivity.this, " Invalid Inputs for height in feet!", Toast.LENGTH_SHORT).show();
                } else if (et_inches.getText().toString().equals("")) {
                    et_inches.setError("Hey! I need a value for height in inches!");
                    Toast.makeText(MainActivity.this, " Invalid Inputs for height in inches!", Toast.LENGTH_SHORT).show();
                } else {
                    InputMethodManager imgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imgr.hideSoftInputFromWindow(b_calcuate.getWindowToken(), 0);

                    wgt = Double.parseDouble(et_weight.getText().toString());
                    feet = Integer.parseInt(et_heightFt.getText().toString());
                    inch = Integer.parseInt(et_inches.getText().toString());
                    totheight = feet * 12 + inch;

                    result = (wgt) * 703 / (totheight * totheight);
                    Log.i("result", Double.toString(result));
                    result = Math.round(result * 100.0) / 100.0;
                    tv_resultBmi.setText("Your BMI :" + Double.toString(result));
                    tv_resultBmi.setVisibility(view.VISIBLE);

                    if (result <= 18.5) {
                        tv_resultComment.setText("You are Underweight!");
                    } else if (result > 18.5 && result <= 24.9) {
                        tv_resultComment.setText("You are Normal weight!");
                    } else if (result >= 25 && result <= 29.9) {
                        tv_resultComment.setText("You are Overweight!");
                    } else if (result > 30) {
                        tv_resultComment.setText("You are Obese!");
                    }
                    tv_resultComment.setVisibility(view.VISIBLE);
                    Toast.makeText(MainActivity.this, "BMI Calculated", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }
}
