package com.shiwangapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV, solutionTV;
    MaterialButton btnC,btnBrackOpen,btnBrackClose;
    MaterialButton btnDivide,btnMultiply,btnPlus,btnMinus,btnEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        assignId(btnC,R.id.button_c);
        assignId(btnBrackOpen,R.id.button_open_bracket);
        assignId(btnBrackClose,R.id.button_close_bracket);
        assignId(btnDivide,R.id.button_divide);
        assignId(btnMultiply,R.id.button_multiply);
        assignId(btnPlus,R.id.button_plus);
        assignId(btnMinus,R.id.button_minus);
        assignId(btnEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button =(MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();

        if(buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText());
            return;
        }
        if(buttonText.equals("C")){
            if (dataToCalculate.length()>0){
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }else {
                return;
            }

        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTV.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTV.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
}