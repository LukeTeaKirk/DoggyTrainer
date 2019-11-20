package com.rajcomics.dogtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateFile();
        InitViews();
    }
    private void CreateFile(){
        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"MyDir");
        if(!fileDir.exists()){
            try{
                fileDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"BlogData"+File.separator+"MyText.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private void WriteFile(String text){
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"BlogData"+File.separator+"MyText.txt");
        if(file.exists()){
            try {
                FileWriter fileWriter  = new FileWriter(file);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter);
                bfWriter.write(text);
                bfWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void InitViews(){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList;
        arrayList = new ArrayList<>();
        arrayList.add("Dog Pebbles");//1
        arrayList.add("Rice");//2
        arrayList.add("Roti");//3
        arrayList.add("Water");//4
        arrayList.add("Milk");//5
        arrayList.add("Vegetables");//6
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int FoodIndex = parent.getSelectedItemPosition();


            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
    });
    }
}
