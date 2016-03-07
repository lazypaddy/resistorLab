package com.lazypaddy.resistorcalculator;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private TextView lblResult;
    private TextView lblResultToler;
    private Spinner band01;

    private TextView band01Color;
    private TextView band02Color;
    private TextView multiplierColor;
    private TextView toleranceColor;

    private Spinner band02;
    private Spinner multi;
    private Spinner tolerance;
    private int value01;
    private int value02;
    private int value03;
    private int value04;

    private  ArrayList<ColorSpinnerAdapterItem> colors; // android.graphics.Color list
    final ArrayList<ColorSpinnerAdapterItem> color_band = new ArrayList<ColorSpinnerAdapterItem>();
    ArrayList<ColorSpinnerAdapterItem> color_tole = new ArrayList<ColorSpinnerAdapterItem>();


    //private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lazypaddy.resistorcalculator.R.layout.activity_main);

        lblResult = (TextView)findViewById(com.lazypaddy.resistorcalculator.R.id.textView6);
        lblResultToler = (TextView)findViewById(com.lazypaddy.resistorcalculator.R.id.textView7);
        band01 = (Spinner)findViewById(com.lazypaddy.resistorcalculator.R.id.spinner1);
        band01Color = (TextView)findViewById(com.lazypaddy.resistorcalculator.R.id.textViewBand01);
        band02Color = (TextView)findViewById(com.lazypaddy.resistorcalculator.R.id.textViewBand02);
        multiplierColor = (TextView)findViewById(R.id.textViewMultiplier);
        toleranceColor = (TextView)findViewById(R.id.textViewTolerance);



        band02 = (Spinner)findViewById(com.lazypaddy.resistorcalculator.R.id.spinner2);
        multi = (Spinner)findViewById(com.lazypaddy.resistorcalculator.R.id.spinner3);
        tolerance = (Spinner)findViewById(com.lazypaddy.resistorcalculator.R.id.spinner4);


        //Alternativa 1: Array java
        //ArrayAdapter<String> adaptadorBand =
            //    new ArrayAdapter<String>(this,
            //            android.R.layout.simple_spinner_item, color_band);

        //Alternativa 2: Recurso XML de tipo string-array
        //ArrayAdapter<CharSequence> adaptador =
        //	    ArrayAdapter.createFromResource(this,
        //	        R.array.valores_array, android.R.layout.simple_spinner_item);

        /*adaptadorBand.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);*/

//http://pichelman.com/content/2014/02/25/android-custom-color-spinner/
        color_band.add(new ColorSpinnerAdapterItem(Color.BLACK, ""));
        color_band.add(new ColorSpinnerAdapterItem(Color.rgb(139,69,19), "")); //brown
        color_band.add(new ColorSpinnerAdapterItem(Color.RED, ""));
        color_band.add(new ColorSpinnerAdapterItem(Color.rgb(255,69,0), ""));//orange
        color_band.add(new ColorSpinnerAdapterItem(Color.YELLOW, ""));
        color_band.add(new ColorSpinnerAdapterItem(Color.GREEN, ""));
        color_band.add(new ColorSpinnerAdapterItem(Color.BLUE, ""));
        color_band.add(new ColorSpinnerAdapterItem(Color.rgb(128,0,128), ""));//violet
        color_band.add(new ColorSpinnerAdapterItem(Color.rgb(128,128,128), "")); //gray
        color_band.add(new ColorSpinnerAdapterItem(Color.WHITE, ""));
        final ColorSpinnerAdapter colorSpinnerBand = new ColorSpinnerAdapter(this, android.R.layout.simple_list_item_1, color_band);

        ArrayList<ColorSpinnerAdapterItem> color_multi = new ArrayList<ColorSpinnerAdapterItem>();
        color_multi.add(new ColorSpinnerAdapterItem(Color.BLACK, ""));
        color_multi.add(new ColorSpinnerAdapterItem(Color.rgb(139,69,19), "")); //brown
        color_multi.add(new ColorSpinnerAdapterItem(Color.RED, ""));
        color_multi.add(new ColorSpinnerAdapterItem(Color.rgb(255,69,0), ""));//orange
        color_multi.add(new ColorSpinnerAdapterItem(Color.YELLOW, "1"));
        color_multi.add(new ColorSpinnerAdapterItem(Color.GREEN, ""));
        color_multi.add(new ColorSpinnerAdapterItem(Color.BLUE, ""));
        color_multi.add(new ColorSpinnerAdapterItem(Color.rgb(128,0,128), ""));//violet
        ColorSpinnerAdapter colorSpinnerMulti = new ColorSpinnerAdapter(this, android.R.layout.simple_list_item_1, color_multi);

        color_tole.add(new ColorSpinnerAdapterItem(Color.rgb(139,69,19), "")); //brown
        color_tole.add(new ColorSpinnerAdapterItem(Color.RED, ""));//red
        color_tole.add(new ColorSpinnerAdapterItem(Color.rgb(218,165,32), "")); //gold
        color_tole.add(new ColorSpinnerAdapterItem(Color.rgb(192,192,192), "")); //silver
        ColorSpinnerAdapter colorSpinnerTole = new ColorSpinnerAdapter(this, android.R.layout.simple_list_item_1, color_tole);

        band01.setAdapter(colorSpinnerBand);
        band02.setAdapter(colorSpinnerBand);
        multi.setAdapter(colorSpinnerMulti);
        tolerance.setAdapter(colorSpinnerTole);

        band01.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //position = posicion 0->n-1 en el array
                        value01=  position;
                        double result = (value01*10+value02)*( Math.pow(10,value03));
                        NumberFormat nf = new DecimalFormat("##");
                        lblResult.setText("Value: "+nf.format(result)+" ohms"  + resistanceCommonValues(result) );
                        lblResultToler.setText("Tolerance: +/-: "+value04+"%");
                        //band01Color.setTextColor(Color.YELLOW);
                        band01Color.setBackgroundColor(color_band.get(position).color);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblResult.setText("");
                    }
                });
        band02.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //position = posicion 0->n-1 en el array
                        value02=  position;
                        double result = (value01*10+value02)*( Math.pow(10,value03));
                        NumberFormat nf = new DecimalFormat("##");
                        lblResult.setText("Value: "+nf.format(result) + resistanceCommonValues(result));
                        lblResultToler.setText("Tolerance: +/-: "+value04+"%");
                        band02Color.setBackgroundColor(color_band.get(position).color);

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblResult.setText("");
                    }
                });
        multi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //position = posicion 0->n-1 en el array
                        value03=  position;
                        double result = (value01*10+value02)*( Math.pow(10,value03));
                        NumberFormat nf = new DecimalFormat("##");
                        lblResult.setText("Value: "+nf.format(result) + resistanceCommonValues(result));
                        lblResultToler.setText("Tolerance: +/-: "+value04+"%");
                        multiplierColor.setBackgroundColor(color_band.get(position).color);

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblResult.setText("");
                    }
                });
        tolerance.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //position = posicion 0->n-1 en el array
                        if (position==0)
                            value04=1;
                        else if(position==1)
                            value04=2;
                        else if (position==2)
                            value04=5;
                        else if(position==3)
                            value04=10;

                        double result = (value01*10+value02)*( Math.pow(10,value03));
                        NumberFormat nf = new DecimalFormat("##");
                        lblResult.setText("Value: "+nf.format(result) + resistanceCommonValues(result));
                        lblResultToler.setText("Tolerance: +/-: "+value04+"%");
                        toleranceColor.setBackgroundColor(color_tole.get(position).color);

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblResult.setText("");
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.lazypaddy.resistorcalculator.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.lazypaddy.resistorcalculator.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String resistanceCommonValues (double resistance) {
        int[] barriers = {1, 1000, 1000000, Integer.MAX_VALUE};
        String[] text = {"Ohm", "kOhm", "MOhm"};
        String[] multiplier = {".", "k", "M"};

        String commonResistance;

            String toReturn = "";
            for (int i = 1; i < barriers.length; i++) {
                if (resistance < barriers[i]){
                    float amount = (float)(resistance/barriers[i-1]);
                    commonResistance = Float.toString(amount) ;
                    toReturn = " (" + commonResistance + " " + text[i-1];
                    if (amount > 1){
                        toReturn += "s";
                    }
                    if (i>1) {
                        if (amount == (int) amount){
                            commonResistance =  commonResistance.replace(".0", multiplier[i - 1]);
                        } else{
                            commonResistance = commonResistance.replace(".", multiplier[i - 1]);
                        }

                        toReturn += " or " + commonResistance + " Ohms";
                    }
                    toReturn += ")";

                    break;
                }
            }
            return toReturn;
    }
}