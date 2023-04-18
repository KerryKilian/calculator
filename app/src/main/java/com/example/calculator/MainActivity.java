package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnPlus;
    Button btnMinus;
    Button btnMultiplikation;
    Button btnDivide;

    Button btnNegative;
    Button btnSquare;
    Button btnRadical;
    Button btnC;
    Button btnCE;
    Button btnEnter;

    private double[] values = {0,0};
    private int currentValue = 0;
    private String action = "";
    private double result = 0;
    private TextView tvCurrentCalculation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        tvCurrentCalculation = findViewById(R.id.tvCurrentCalculation);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiplikation = (Button) findViewById(R.id.btnMultiplikation);
        btnDivide = (Button) findViewById(R.id.btnDivide);

        btnNegative = (Button) findViewById(R.id.btnNegative);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnRadical = (Button) findViewById(R.id.btnRadical);
        btnC = (Button) findViewById(R.id.btnC);
        btnCE = (Button) findViewById(R.id.btnCE);
        btnEnter = (Button) findViewById(R.id.btnEnter);

        Button[] buttonsNumbers = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        Button[] buttonsActions = {btnPlus, btnMinus, btnMultiplikation, btnDivide};

        // [0-9]
        for (int i = 0; i < buttonsNumbers.length; i++) {
            int finalI = i;
            buttonsNumbers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newString = (result == 0 ? "" : (int) result) + buttonsNumbers[finalI].getText().toString();
                    setResult(Double.parseDouble(newString));
//                    setResult(result + Double.parseDouble(buttonsNumbers[finalI].getText().toString()));
//                    tvResult.setText((text.equals("0") ? "" : text) + buttonsNumbers[finalI].getText().toString());
                    values[currentValue] = result;
                }
            });
        }

        // Addition, Subtraktion, Multiplikation, Division
        for (int i = 0; i < buttonsActions.length; i++) {
            int finalI = i;
            buttonsActions[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action = buttonsActions[finalI].getText().toString();
                    log(action);
                    if (currentValue == 1) {
                        values[0] = result;
                    }
                    currentValue = 1;
                    setResult(0.0);
//                    tvResult.setText("0");
                    tvCurrentCalculation.setText(makeClear(values[0]) + action);
//                    setCurrentValues(values[currentValue], action, null);
                }
            });
        }

        // Enter
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCurrentCalculation.setText(makeClear(values[0]) + action + makeClear(values[1]));
                switch(action) {
                    case "+":
                        result = values[0] + values[1];
                        break;
                    case "-":
                        result = values[0] - values[1];
                        break;
                    case "x":
                        result = values[0] * values[1];
                        break;
                    case "÷":
                        result = values[0] / values[1];
                        break;
                }

//                if (result % 1 == 0) {
//                    tvResult.setText(String.valueOf((int) result));
//                } else {
//                    tvResult.setText(String.valueOf(result));
//                }
                setResult(result);
            }
        });

        // C
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tvResult.setText("0");
                setResult(0.0);
                tvCurrentCalculation.setText("");
                values[0] = 0;
                values[1] = 0;
                currentValue = 0;
            }
        });

        // CE
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(0.0);
//                tvResult.setText("0");
                values[currentValue] = 0.0;

                if (currentValue == 0) {
                    tvCurrentCalculation.setText("");
                } else {
                    tvCurrentCalculation.setText(makeClear(values[0]) + (action.length() == 1 ? action : "") );
                }
            }
        });

        // Negative
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values[currentValue] *= -1;
                setResult(values[currentValue]);
//                tvResult.setText(String.valueOf(values[currentValue]));
                tvCurrentCalculation.setText(makeClear(values[0]) + action + makeClear(values[1]));
            }
        });

        // Radical
        btnRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                double current = Double.parseDouble(tvResult.getText().toString());
                tvCurrentCalculation.setText("√" + makeClear(result)); // current
//                tvResult.setText(String.valueOf(Math.sqrt(current)));
                setResult(Math.sqrt(result));
            }
        });

        // Square
        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCurrentCalculation.setText(makeClear(result) + "²");
                setResult(result * result);
            }
        });


    }

    private void setResult(double result) {
        log("now in setResult, result = " + result);
        this.result = result;
        if (result % 1 == 0) {
            log("now in if");
            tvResult.setText(String.valueOf((int) result));
        } else {
            log("now in else");
            tvResult.setText(String.valueOf(result));
        }
    }

    private void setCurrentValues(Double first, String action, Double second) {
        if (first != null) {
            values[0] = first;
        }
        if (second != null) {
            values[1] = second;
        }
        if (action != null) {
            this.action = action;
        }

        tvCurrentCalculation.setText((first != null ? first : "") + " " + action + " " + (second != null ? second : ""));

    }

    /**
     * round double values get converted into a integer string: 5.0 -> 5 ; 0.0 -> 0
     * @param value
     * @return
     */
    private String makeClear(double value) {
        if (value % 1 == 0) {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }


    private void log(String message) {
        Log.d("MainActivity", message);
    }

}