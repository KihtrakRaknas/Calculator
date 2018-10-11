package com.example.kihtrakraknas.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.id_dispay);
        //display.setMovementMethod(new ScrollingMovementMethod());
    }

    public void NumberClicked(View v) {
        if(display.getText().equals("ERROR"))
            display.setText("");
        String prevChar = " ";
        if(display.getText().length()-1>0)
            prevChar = ((String)display.getText()).substring(display.getText().length()-1,display.getText().length());
        if(prevChar.equals("+")||prevChar.equals("-")||prevChar.equals("/")||prevChar.equals("*")){
            display.setText((String)display.getText()+" "+(String)((Button)v).getText());
        }else{
            display.setText((String)display.getText()+(String)((Button)v).getText());
        }
    }

    public void SignClicked(View v) {
        display.setText((String)display.getText()+" "+(String)((Button)v).getText());
    }

    public void MakeNumNeg(View v) {
        String[] strs = ((String)display.getText()).split(" ");
        String newText = "";
        if(!((String)display.getText()).equals(""))
        for(int i = 0; i!=strs.length; i++){
            if (!newText.equals(""))
                newText +=" ";
            if(!(i==strs.length-1))
                newText += strs[i];
            else
                if(strs[i].startsWith("(-"))
                    newText += strs[i].substring(2,strs[i].length()-1);
                else
                    newText += "(-"+strs[i]+")";
        }
        display.setText(newText);
    }

    public void clear(View v) {
        display.setText("");
    }

    public void equals(View v) {
        try {
            String[] strs = ((String) display.getText()).split(" ");
            ArrayList<String> strsList = new ArrayList<String>();
            for (int i = 0; i != strs.length; i++) {
                strsList.add(strs[i]);
            }
            for (int i = 0; i != strsList.size(); i++) {
                Log.d("i", "" + i);
                if (strsList.get(i).equals("*") || strsList.get(i).equals("/")) {
                    Double answer;
                    Double num1;
                    String old1 = strsList.get(i - 1);

                    if (old1.length() > 3 && old1.substring(0, 2).equals("(-") && old1.substring(old1.length() - 1, old1.length()).equals(")")) {
                        num1 = -1 * Double.parseDouble(old1.substring(2, old1.length() - 1));
                    } else {
                        num1 = Double.parseDouble(old1);
                    }

                    Double num2;
                    String old2 = strsList.get(i + 1);
                    if (old2.length() > 3 && old2.substring(0, 2).equals("(-") && old2.substring(old2.length() - 1, old2.length()).equals(")")) {
                        num2 = -1 * Double.parseDouble(old2.substring(2, old2.length() - 1));
                    } else {
                        num2 = Double.parseDouble(old2);
                    }

                    if (strsList.get(i).equals("*"))
                        answer = num1 * num2;
                    else
                        answer = num1 / num2;

                    Log.d("calc", "" + strsList);
                    strsList.remove(i + 1);
                    strsList.set(i, "" + answer);
                    strsList.remove(i - 1);
                    i -= 2;
                }
            }
            for (int i = 0; i != strsList.size(); i++) {
                if (strsList.get(i).equals("+") || strsList.get(i).equals("-")) {
                    Double answer;
                    Double num1;
                    String old1 = strsList.get(i - 1);

                    if (old1.length() > 3 && old1.substring(0, 2).equals("(-") && old1.substring(old1.length() - 1, old1.length()).equals(")")) {
                        num1 = -1 * Double.parseDouble(old1.substring(2, old1.length() - 1));
                    } else {
                        num1 = Double.parseDouble(old1);
                    }

                    Double num2;
                    String old2 = strsList.get(i + 1);
                    if (old2.length() > 3 && old2.substring(0, 2).equals("(-") && old2.substring(old2.length() - 1, old2.length()).equals(")")) {
                        num2 = -1 * Double.parseDouble(old2.substring(2, old2.length() - 1));
                    } else {
                        num2 = Double.parseDouble(old2);
                    }


                    if (strsList.get(i).equals("+"))
                        answer = num1 + num2;
                    else
                        answer = num1 - num2;
                    Log.d("calc", "" + strsList);
                    strsList.remove(i + 1);
                    strsList.set(i, "" + answer);
                    strsList.remove(i - 1);
                    i -= 2;
                }
            }
            Log.d("final", "" + strsList);
            if (new Double((int) (Double.parseDouble(strsList.get(0)))) == Double.parseDouble(strsList.get(0)))
                strsList.set(0, "" + (int) (Double.parseDouble(strsList.get(0))));
            if(Double.isInfinite(Double.parseDouble(strsList.get(0))))
                display.setText("ERROR");
            else
                display.setText(strsList.get(0));
        }catch(Exception e){
            display.setText("ERROR");
        }
    }

}
