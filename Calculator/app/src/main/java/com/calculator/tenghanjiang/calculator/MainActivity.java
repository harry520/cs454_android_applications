package com.calculator.tenghanjiang.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    static EditText display;
    static float firstNum = 0, secondNum = 0;
    static String operation;
    static String mainTextNum;
    static String num;
    private ButtonClickListener buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (EditText) findViewById(R.id.display);
        display.setEnabled(false);
        Intent intent = getIntent();
        display.setText(intent.getStringExtra("scientificTextNum"));
        buttonClick = new ButtonClickListener();
        int buttons[] = {R.id.del, R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
                R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9, R.id.point,
                R.id.div, R.id.mul, R.id.sub, R.id.add, R.id.equal};
        for (int button : buttons) {
            View v = findViewById(button);
            v.setOnClickListener(buttonClick);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class ButtonClickListener implements View.OnClickListener {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.del:
                    display.setText("");
                    break;
                case R.id.add:
                    if (display.getText().toString().equals("")) {
                    } else
                        math("+");
                    break;
                case R.id.sub:
                    if (display.getText().toString().equals("")) {
                    } else
                        math("-");
                    break;
                case R.id.mul:
                    if (display.getText().toString().equals("")) {
                    } else
                        math("*");
                    break;
                case R.id.div:
                    if (display.getText().toString().equals("")) {
                    } else
                        math("/");
                    break;
                case R.id.equal:
                    if (display.getText().toString().equals("")) {
                    } else
                        result();
                    break;
                default:
                    num = ((Button) v).getText().toString();
                    getKeyboard(num);
                    break;
            }
        }

    }

    private void math(String str) {
        firstNum = Float.parseFloat(display.getText().toString());
        operation = str;
        display.setText(String.valueOf(firstNum));
        display.setText("");
    }

    public void getKeyboard(String str) {
        String strCurrent = display.getText().toString();
        if (strCurrent.equals("0"))
            strCurrent = "";
        strCurrent += str;
        display.setText(strCurrent);
    }

    public void result() {

        secondNum = Float.parseFloat(display.getText().toString());
        float result = 0;
        if (operation.equals("+"))
            result = firstNum + secondNum;
        if (operation.equals("-"))
            result = firstNum - secondNum;
        if (operation.equals("*"))
            result = firstNum * secondNum;
        if (operation.equals("/"))
            result = firstNum / secondNum;
        if (operation.equals("^"))
            result = (float) Math.pow(firstNum, secondNum);
        display.setText(String.valueOf(result));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.scientific) {
            mainTextNum = display.getText().toString();
            Intent intent = new Intent(MainActivity.this, ScientificActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.putExtra("mainTextNum", mainTextNum);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
