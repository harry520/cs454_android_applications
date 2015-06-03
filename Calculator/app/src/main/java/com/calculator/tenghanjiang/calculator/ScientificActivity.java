package com.calculator.tenghanjiang.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ScientificActivity extends ActionBarActivity {

    static EditText display;
    static String scientificTextNum;
    private ButtonClickListener buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scientific);
        display = (EditText) findViewById(R.id.display);
        display.setEnabled(false);
        Intent intent = getIntent();
        display.setText(intent.getStringExtra("mainTextNum"));
        buttonClick = new ButtonClickListener();
        int buttons[] = {R.id.del, R.id.sin, R.id.cos, R.id.tan, R.id.ln, R.id.log, R.id.pi,
                R.id.e, R.id.percent, R.id.factorial, R.id.power, R.id.sqrt, R.id.leftBracket,
                R.id.rightBracket};
        for (int button : buttons) {
            View v = findViewById(button);
            v.setOnClickListener(buttonClick);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scientific, menu);
        return true;
    }

    private class ButtonClickListener implements View.OnClickListener {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.del:
                    display.setText("");
                    MainActivity.operation = "";
                    break;
                case R.id.sin:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float sin = (float) Math.sin(Math.toRadians(
                                Float.parseFloat(display.getText().toString())));
                        display.setText(String.valueOf(sin));
                    }
                    break;
                case R.id.cos:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float cos = (float) Math.cos(Math.toRadians(
                                Float.parseFloat(display.getText().toString())));
                        display.setText(String.valueOf(cos));
                    }
                    scientificTextNum = display.getText().toString();
                    break;
                case R.id.tan:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float tan = (float) Math.tan(Math.toRadians(Float.parseFloat(
                                display.getText().toString())));
                        display.setText(String.valueOf(tan));
                    }
                    break;
                case R.id.ln:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float ln = (float) Math.log(Float.parseFloat(
                                display.getText().toString()));
                        display.setText(String.valueOf(ln));
                    }
                    break;
                case R.id.log:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float log = (float) Math.log10(Float.parseFloat(
                                display.getText().toString()));
                        display.setText(String.valueOf(log));
                    }
                    break;
                case R.id.pi:
                    display.setText(String.valueOf(Math.PI));
                    scientificTextNum = display.getText().toString();
                    break;
                case R.id.e:
                    display.setText(String.valueOf(Math.E));
                    break;
                case R.id.percent:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float percent = Float.parseFloat(display.getText().toString())
                                * 0.01F;
                        display.setText(String.valueOf(percent));
                    }
                    break;
                case R.id.factorial:
                    if (display.getText().toString().equals("")) {
                    } else {
                        int fact = 1;
                        for (int i = 1; i <= Float.parseFloat(display.getText()
                                .toString()); i++)
                            fact *= i;
                        display.setText(String.valueOf(fact));
                    }
                    break;
                case R.id.sqrt:
                    if (display.getText().toString().equals("")) {
                    } else {
                        float sqrt = (float) Math.sqrt(Float.parseFloat(
                                display.getText().toString()));
                        display.setText(String.valueOf(sqrt));
                    }
                case R.id.power:
                    if (ScientificActivity.display.getText().toString().equals("")) {
                    } else {
                        MainActivity.firstNum = Float.parseFloat(display.getText().toString());
                        math("^");
                    }
                    break;
                default:
                    String function = ((Button) v).getText().toString();
                    getKeyboard(function);
                    break;
            }
        }

    }

    private void math(String str) {
        MainActivity.firstNum = Float.parseFloat(display.getText().toString());
        MainActivity.operation = str;
        display.setText(String.valueOf(MainActivity.firstNum));
        display.setText("");
    }

    public void getKeyboard(String str) {
        String strCurrent = display.getText().toString();
        if (strCurrent.equals("0"))
            strCurrent = "";
        strCurrent += str;
        display.setText(strCurrent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.basic) {
            Intent intent = new Intent(ScientificActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            scientificTextNum = display.getText().toString();
            intent.putExtra("scientificTextNum", scientificTextNum);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
