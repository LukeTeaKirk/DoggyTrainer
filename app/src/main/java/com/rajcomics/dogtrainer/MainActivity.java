package com.rajcomics.dogtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean flag1;
    boolean flag2;
    boolean flag3;
    boolean flag4;
    int FoodIndex;
    int AmountIndex;
    EditText FoodTime;
    TextView Datapoints;
    Spinner spinner;
    Spinner spinner2;
    EditText ParkTime;
    Button SubmitLeia;
    Button SubmitLuke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateFile();
        InitViews();
    }
    private void CreateFile(){
        final File fileDir = new File("/storage/emulated/0/testpoints");
        //File direct = new File(getFilesDir() + "/testpoints/");
        if(!fileDir.exists()){
            try{
                fileDir.mkdirs();
                Log.d("abc", "Attempted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File("storage/emulated/0/testpoints/MyText.csv");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        File file2 = new File("storage/emulated/0/testpoints/MyText2.csv");
        if(!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private void WriteFile(String text){
        File file = new File("storage/emulated/0/testpoints/MyText.csv");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"BlogData"+File.separator+"MyText.csv", duration);
        toast.show();
        if(file.exists()){
            try {
                FileWriter fileWriter  = new FileWriter(file, true);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter);
                bfWriter.append(text);
                bfWriter.append("\n");

                bfWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void WriteFile2(String text){
        File file2 = new File("storage/emulated/0/testpoints/MyText2.csv");
        if(file2.exists()){
            try {
                FileWriter fileWriter  = new FileWriter(file2, true);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter);
                bfWriter.append(text);
                bfWriter.append("\n");
                bfWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void Increment(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        SharedPreferences prefs = this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
        String lanSettings = prefs.getString("language", null);
        Integer a = sharedPref.getInt("a",0 );
        if(a == null){
            a = -1;
        }
        a= a+1;
        editor.putInt("a", (a));
        editor.commit();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, "Number =" + (a), duration);
        toast.show();
        Datapoints = findViewById(R.id.datapointsText);
        String a2 = a.toString();
        Datapoints.setText(a2);
    }
    private void InitViews(){
        spinner2 = findViewById(R.id.spinner2);
        ArrayList<String> arrayList2;
        arrayList2 = new ArrayList<>();
        arrayList2.add("Full");//1
        arrayList2.add("Half");//2
        arrayList2.add("Quarter");//3
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }

        });
        spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        arrayList.add("Dog Pebbles");//1
        arrayList.add("Rice");//2
        arrayList.add("Roti");//3
        arrayList.add("Water");//4
        arrayList.add("Milk");//5
        arrayList.add("Vegetables");//6
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }

        });
        FoodTime = findViewById(R.id.FoodTime);
        ParkTime = findViewById(R.id.ParkTime);
        SubmitLeia = findViewById(R.id.LeiaSubmit);
        SubmitLuke = findViewById(R.id.LukeSubmit);
        SubmitLeia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AmountIndex = spinner2.getSelectedItemPosition();
                FoodIndex = spinner.getSelectedItemPosition();
                String FoodStr = FoodTime.getText().toString();
                String ParkStr = ParkTime.getText().toString();
                if(FoodStr.length() == 4){
                    flag3 = true;
                }
                if(ParkStr.length() == 4){
                    flag4 = true;
                }
                String writestring = FoodIndex + "," + AmountIndex + "," + FoodStr + "," + ParkStr;
                if(flag3&flag4) {
                    WriteFile(writestring);
                    Context context = getApplicationContext();
                    CharSequence text = "Data Logged!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Increment();
            }
        });
        SubmitLuke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AmountIndex = spinner2.getSelectedItemPosition();
                FoodIndex = spinner.getSelectedItemPosition();
                String FoodStr = FoodTime.getText().toString();
                if(FoodStr.length() == 4){
                    flag1 = true;
                }
                String ParkStr = ParkTime.getText().toString();
                if(ParkStr.length() == 4){
                    flag2 = true;
                }
                String writestring = FoodIndex + "," + AmountIndex + "," + FoodStr + "," + ParkStr;
                if(flag1&flag2){
                   WriteFile2(writestring);
                    Context context = getApplicationContext();
                    CharSequence text = "Data Logged!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                Increment();
            }
        });








    }
}
