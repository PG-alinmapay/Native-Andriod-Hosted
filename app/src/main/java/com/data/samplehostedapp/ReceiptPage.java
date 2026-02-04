package com.data.samplehostedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ReceiptPage extends AppCompatActivity {


    TableLayout tblrespData;
    TableRow tblrow;
    JSONObject jsonObject;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_page);
        btnHome = findViewById(R.id.btnHome);
        try {
            jsonObject = new JSONObject(getIntent().getStringExtra("jsonObject"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tblrespData = (TableLayout) findViewById(R.id.displayLinear);
        tblrow = findViewById(R.id.display_row);
        tblrespData.setStretchAllColumns(true);
        tblrespData.bringToFront();


        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Log.i("TAG", "key:" + key + "--Value::" + jsonObject.optString(key));

            TableRow tr = new TableRow(this);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            tr.setLayoutParams(params1);
            tr.setPadding(10, 10, 10, 10);

            TextView header1 = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
            header1.setLayoutParams(params2);
            header1.setText(key);

            TextView header2 = new TextView(this);
            TableRow.LayoutParams params3 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
            header2.setLayoutParams(params3);

            String strjsonkey=jsonObject.optString(key);
            String  strvalue= strjsonkey.replaceAll("%20"," ");
            header2.setText(strvalue);

            tr.addView(header1);
            tr.addView(header2);

            tblrespData.addView(tr);

        }



        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnHome.setEnabled(false);
                Intent i = new Intent(ReceiptPage.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}