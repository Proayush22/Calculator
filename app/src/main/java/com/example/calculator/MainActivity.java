package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Button clear;
    Button delete;
    Button exponent;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num0;
    Button var;
    Button ansB;
    Button square;
    Button multiply;
    Button add;
    Button subtract;
    Button equals;
    Drawable button;

    TextView input;
    TextView output;
    String outputS;
    String calcS;
    Spanned inputS;
    String answer;
    String answerS;
    boolean carrotPressed;
    int parCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = findViewById(R.id.clear);
        delete = findViewById(R.id.delete);
        exponent = findViewById(R.id.exponent);
        num1 = findViewById(R.id.one);
        num2 = findViewById(R.id.two);
        num3 = findViewById(R.id.three);
        num4 = findViewById(R.id.four);
        num5 = findViewById(R.id.five);
        num6 = findViewById(R.id.six);
        num7 = findViewById(R.id.seven);
        num8 = findViewById(R.id.eight);
        num9 = findViewById(R.id.nine);
        num0 = findViewById(R.id.zero);
        var = findViewById(R.id.variable);
        ansB = findViewById(R.id.answer);
        square = findViewById(R.id.squared);
        multiply = findViewById(R.id.multiply);
        add = findViewById(R.id.plus);
        subtract = findViewById(R.id.subtract);
        equals  = findViewById(R.id.equals);
        button = Drawable.createFromPath("buttons.xml");

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        carrotPressed = false;
        answer = "";
        answerS = "";

    }
    public void updateScreen(View view, String str){
        clear.setText("CE");
        if(input.getText().toString().equals("0") || input.getText().toString().equals("Enter a Value")) {
            calcS = str;
            ((TextView)view).setText(str);
        }
        else{
            if(carrotPressed){
                //Spanned c = Html.fromHtml("<sup><small>" + str + "</small><sup>");
                ((TextView)view).setText(TextUtils.concat(input.getText(), str));
                //((TextView)view).setText(TextUtils.concat(input.getText(), c));
                calcS += str;
            }
            else {
                calcS += str;
                ((TextView)view).setText(TextUtils.concat(input.getText(), str));
            }
        }

    }
    void updateSign(View view, String str) {
        clear.setText("CE");
        if (input.getText().toString().equals("0")) {
            ((TextView)view).setText(str);
            calcS = str;
        } else {
            input.setText(TextUtils.concat(input.getText(), (str + " ")));
            calcS += str;
        }
    }

    public void clearB(View view) {
        if(clear.getText().toString().equals("AC")){
            outputS = "0";
            output.setText(outputS);
            ((TextView)view).setText("0");

        }
        input.setText("0");
        clear.setText("AC");
        carrotPressed = false;
    }

    public void deleteB(View view) {
        if(input.getText().toString().length() != 1) {
            input.setText(input.getText().toString().substring(0, (input.getText().toString().length() - 1)));
            calcS = calcS.substring(0, calcS.length()-1);
        }
        else {
            input.setText("0");
        }
    }

    public void ansB(View view) {
        if(!(input.getText().toString().equals("0") || input.getText().toString().equals("Enter a Value"))) {
            input.setText(TextUtils.concat(input.getText(), answer));
            calcS += answerS;
            Log.d(answer, answerS);
            Log.d(answer, calcS);
        }
        else if(!answerS.equals(null)){
            input.setText(TextUtils.concat(answer));
            calcS = answerS;
            Log.d(answer, answerS);
            Log.d(answer, calcS);
        }
    }

    public void square(View view) {
        //carrotPressed = true;
        //updateScreen(input, "2");
        //carrotPressed = false;
        calcS += "^(2)";
        input.setText(TextUtils.concat(input.getText(), "^(2)"));

    }

    public void oneB(View view) {
        updateScreen(input, "1");
    }

    public void twoB(View view) {
        updateScreen(input, "2");
    }

    public void threeB(View view) {
        updateScreen(input, "3");
    }

    public void multiplyB(View view) {
        updateSign(input, " *");
    }

    public void fourB(View view) {
        updateScreen(input, "4");
    }

    public void fiveB(View view) {
        updateScreen(input, "5");
    }

    public void sixB(View view) {
        updateScreen(input, "6");
    }

    public void addB(View view) {
        updateSign(input, " +");
    }

    public void sevenB(View view) {
        updateScreen(input, "7");
    }

    public void eightB(View view) {
        updateScreen(input, "8");
    }

    public void nineB(View view) {
        updateScreen(input, "9");
    }

    public void subtractB(View view) {
        updateSign(input, " -");
    }

    public void variableB(View view) {
        updateScreen(input, "x");
    }

    public void zeroB(View view) {
        updateScreen(input, "0");
    }

    public void carrotB(View view) {
        clear.setText("CE");
        if(!carrotPressed) {
            input.setText(TextUtils.concat(input.getText(), "^("));
            calcS += "^(";
            carrotPressed = true;
            //exponent.setBackground(button);
            //exponent.setBackgroundColor(Color.rgb(22, 23, 28));

        }
        else if (carrotPressed){
            input.setText(TextUtils.concat(input.getText(), ")"));
            calcS += ")";
            carrotPressed = false;
            //exponent.setBackgroundColor(Color.rgb(34, 36, 43));
        }

    }

    public void equalsB(View view) {
        try {
            outputS = "";
            calcinput(output);
            answerS = calcS;
            answer = output.getText().toString();
        }
        catch(Exception e){
            outputS = "Syntax Error";
            output.setText(outputS);
        }
    }

    private void calcinput(View view) {
        HashMap<String, Integer> powers = new HashMap<>();                                  // power, coefficent
        HashMap<String, Integer> coeffs = new HashMap<>();                                     // coefficents
        ArrayList<String> xVals = new ArrayList<>();
        calculate(calcS, powers, coeffs, xVals, view);
    }
    void calculate(String inputS, HashMap<String, Integer> powers, HashMap<String, Integer> coeffs, ArrayList<String> xVals, View view){
        StringTokenizer st = new StringTokenizer(inputS, " ", false);
        while(st.hasMoreTokens()){
            String exp = st.nextToken();
            setPower(exp, powers, coeffs, xVals);
        }
        for(int i = 0; i < xVals.size(); i++){
            String resp = "Not set";
            int ressyp = 0;
            int reppyc = 0;
            try {
                if(xVals.get(i).substring(0,1).equals("*")){
                    if(powers.get(xVals.get(i - 1)) == 0 && powers.get(xVals.get(i)) == 0){
                        reppyc = coeffs.get(xVals.get(i - 1)) * coeffs.get(xVals.get(i));
                        resp = reppyc + "";
                    }
                    else if(powers.get(xVals.get(i - 1)) == 0 && powers.get(xVals.get(i)) > 0){
                        reppyc = coeffs.get(xVals.get(i-1)) * coeffs.get(xVals.get(i));
                        ressyp = powers.get(xVals.get(i));
                        resp = reppyc + "x^(" + ressyp + ")";
                    }
                    else if(powers.get(xVals.get(i)) == 0 && powers.get(xVals.get(i - 1)) > 0){
                        reppyc = coeffs.get(xVals.get(i - 1)) * coeffs.get(xVals.get(i));
                        ressyp = powers.get(xVals.get(i - 1));
                        resp = reppyc + "x^(" + ressyp + ")";
                    }
                    else if((powers.get(xVals.get(i - 1)) > 0) && (powers.get(xVals.get(i)) > 0)){
                        ressyp = powers.get(xVals.get(i - 1)) + powers.get(xVals.get(i));
                        reppyc = coeffs.get(xVals.get(i - 1)) * coeffs.get(xVals.get(i));
                        resp = reppyc + "x^(" + ressyp + ")";
                    }
                    powers.remove(xVals.get(i));
                    powers.remove(xVals.get(i - 1));
                    powers.put(resp, ressyp);
                    coeffs.remove(xVals.get(i));
                    coeffs.remove(xVals.get(i - 1));
                    coeffs.put(resp, reppyc);
                    xVals.set(i, resp);
                    xVals.remove(i - 1);
                    i--;
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                outputS = "Syntax Error";
                output.setText(outputS);
            }
        }
        ArrayList<Integer> pows = new ArrayList<>();
        for(int i = 0; i < xVals.size(); i++){
            String idek = xVals.get(i);
            int whoknows = powers.get(idek);
            if(!pows.contains(whoknows)){
                pows.add(whoknows);
            }
        }
        ArrayList<String> results = new ArrayList<>();
        for(int i = 0; i < pows.size(); i++){
            int comb = 0;
            for(int j = 0; j < xVals.size(); j++){
                if(powers.get(xVals.get(j)) == pows.get(i)){
                    comb += coeffs.get(xVals.get(j));
                    powers.remove(xVals.get(j));
                    coeffs.remove(xVals.get(j));
                }
            }
            String res = "";
            if(comb == 0)
                res = "";
            else if(pows.get(i) > 1){
                res = comb + "x^(" + pows.get(i) + ")";
                results.add(res);
            }
            else if(pows.get(i) == 1){
                res = comb + "x";
                results.add(res);
            }
            else if(pows.get(i) == 0){
                res = comb + "";
                results.add(res);
            }
        }
        sortExp(results, powers, coeffs, xVals);
        outputS = "";
        String temp;
        for(int i = 0; i < results.size(); i++){
            if(i == 0 && (results.get(i).substring(0,1).equals("-")))
                outputS += results.get(i);
            else if(results.get(i).substring(0,1).equals("-") || results.get(i).substring(0,1).equals("*")) {
                if(results.get(i).contains("-")) {
                    temp = results.get(i).substring(results.get(i).indexOf("-")+1);
                    outputS += (" - " + temp);
                    Log.d(temp, results.get(i));
                }
                if(results.get(i).contains("*")) {
                    temp = results.get(i).substring(results.get(i).indexOf("*")+1);
                    outputS += (" * " + temp);
                }
            }
            else if(results.get(i).contains("x") && !(i == 0))
                outputS += (" + " + results.get(i));
            else if(!results.get(i).contains("x") && !(i == 0))
                outputS += (" + " + results.get(i));
            else
                outputS += results.get(i);
            temp = "";
        }
        if(outputS.equals("") || outputS.equals(" "))
            outputS = "0";
        output.setText(outputS);

    }

    void sortExp(ArrayList<String> results, HashMap<String, Integer> powers, HashMap<String, Integer> coeffs, ArrayList<String> xVals) {
        for(int i = 0; i < results.size(); i++){
            setPower(results.get(i), powers, coeffs, xVals);
        }
        for(int i = 0; i < results.size(); i++){
            for(int j = 0; j < results.size(); j++){
                if(powers.get(results.get(i)) > powers.get(results.get(j))){
                    String temp = results.get(i);
                    results.set(i, results.get(j));
                    results.set(j, temp);
                }
            }
        }
    }

    void setPower(String exp, HashMap<String, Integer> powers, HashMap<String, Integer> coeffs, ArrayList<String> xVals){
        if(exp.contains("x")){
            if(exp.contains("^")){
                int power;
                int coefficent;
                System.out.println(exp);
                xVals.add(exp);
                power = Integer.parseInt(exp.substring(exp.indexOf("(") + 1, exp.indexOf(")")));
                if(exp.substring(1, 2).equals("x")){
                    if(exp.substring(0, 1).equals("-"))
                        coefficent = -1;
                    else if(exp.substring(0, 1).equals("+"))
                        coefficent = 1;
                    else
                        coefficent = Integer.parseInt(exp.substring(0, exp.indexOf("x")));
                }
                else if(!exp.contains("*"))
                    coefficent = Integer.parseInt(exp.substring(0, exp.indexOf("x")));
                else
                    coefficent = Integer.parseInt(exp.substring(1, exp.indexOf("x")));
                powers.put(exp, power);
                coeffs.put(exp, coefficent);
            }
            else{
                int power = 1;
                int coefficent;
                xVals.add(exp);
                if(exp.substring(1).equals("x")){
                    if(exp.substring(0, 1).equals("-"))
                        coefficent = -1;
                    else
                        coefficent = Integer.parseInt(exp.substring(0, exp.indexOf("x")));;
                }
                else if(!exp.contains("*"))
                    coefficent = Integer.parseInt(exp.substring(0, exp.indexOf("x")));
                else
                    coefficent = Integer.parseInt(exp.substring(1, exp.indexOf("x")));
                powers.put(exp, power);
                coeffs.put(exp, coefficent);
            }
        }
        else if(exp.contains("^")){
            int power = Integer.parseInt(exp.substring(exp.indexOf("(") + 1, exp.indexOf(")")));
            int coefficent;
            xVals.add(exp);
            if (!exp.contains("*")){
                coefficent = (int) Math.pow(Integer.parseInt(exp.substring(0, exp.indexOf("^"))), power);
            }
            else
                coefficent = (int) Math.pow(Integer.parseInt(exp.substring(1, exp.indexOf("^"))), power);
            powers.put(exp, 0);
            coeffs.put(exp, coefficent);
        }
        else if(!exp.contains("x") && !exp.contains("^")){
            int power = 0;
            int coefficent;
            xVals.add(exp);
            if (!exp.contains("*"))
                coefficent = Integer.parseInt(exp);
            else
                coefficent = Integer.parseInt(exp.substring(1));
            powers.put(exp, power);
            coeffs.put(exp, coefficent);
        }
    }
}