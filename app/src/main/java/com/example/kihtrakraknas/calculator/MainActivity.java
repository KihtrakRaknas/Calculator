package com.example.kihtrakraknas.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void NumberClicked(View v) {
        String prevChar = "";
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
        String[] strs = ((String)display.getText()).split(" ");
        ArrayList<String> strsList = new ArrayList<String>();
        for(int i = 0; i!=strsList.size(); i++) {
            if(strsList.get(i).equals("*")||strs[i].equals("/")){
                int answer;
                if(strsList.get(i).equals("*"))
                    answer = Integer.parseInt(strsList.get(i-1)) * Integer.parseInt(strsList.get(i-1));
                else
                    answer = Integer.parseInt(strsList.get(i-1)) / Integer.parseInt(strsList.get(i-1));

            }
        }
    }
}
