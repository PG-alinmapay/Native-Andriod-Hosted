package com.data.samplehostedapp;

import androidx.appcompat.app.AppCompatActivity;

import com.data.hostedpayments.TrxnPayments;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    EditText edt_Amount,edtmetadata,edtmdata;
    EditText edt_Email;
    Button btn_Pay;

    EditText actionCode,city,state,currency,zip,contrycode,address,merchantIP,edtTrackID,cardToken,maskedCard,edt_transId,edt_channelId;
    boolean flagswitch=false;
    Spinner spnCardOpertaion,spnTrxnType;;
   // String strcustomPIP;

    String transtype;
    public android.content.Context Context = this;
    TrxnPayments trxnPayments;


    TextView multiSelectSpinner;
    String[] items = {"CCI",  "UPI", "WALLET","CHALLAN","NETBANK"};
    boolean[] selectedItems;
    ArrayList<Integer> selectedIndices = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        trxnPayments = new TrxnPayments();

//        multiSelectSpinner = findViewById(R.id.multiSelectSpinner);
        selectedItems = new boolean[items.length];

//        try {
//            // Adjust the path to gradle-wrapper.properties
////            String projectRoot = dataactivity.getFilesDir().getParent();
////            System.out.println("projectRoot  "+projectRoot);
////            String projectRoot1 = dataactivity.getFilesDir().getPath();
//          System.out.println("projectRoot1  "+projectRoot1);
//           String projectRoot2 = dataactivity.getFilesDir().getCanonicalPath();
//           System.out.println("projectRoot2  "+projectRoot2);
//            File file = new File( "/gradle/wrapper/gradle-wrapper.properties");
//
//            if (file.exists()) {
//                Properties properties = new Properties();
//                FileInputStream fis = new FileInputStream(file);
//                properties.load(fis);
//                fis.close();
//
//                // Extract Gradle version
//                String gradleVersion = properties.getProperty("distributionUrl");
//                if (gradleVersion != null) {
//                    gradleVersion = gradleVersion.substring(gradleVersion.lastIndexOf('-') + 1, gradleVersion.lastIndexOf('.'));
//                    System.out.println("AGP Version (inferred): " + gradleVersion);
//                } else {
//                    System.out.println("AGP version not found.");
//                }
//            } else {
//                System.out.println("gradle-wrapper.properties not found.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        btn_Pay.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String strOperation= spnCardOpertaion.getSelectedItem().toString();
                spnCardOpertaion.getSelectedItem().toString();
                if(strOperation.equalsIgnoreCase("Add"))
                {
                    strOperation ="A";
                }
                else  if(strOperation.equalsIgnoreCase("Update"))
                {
                    strOperation ="U";
                }
                else  if(strOperation.equalsIgnoreCase("Delete"))
                {
                    strOperation ="D";
                }
                String email = edt_Email.getText().toString();
                String straction = spnTrxnType.getSelectedItem().toString();
              //  System.out.println(straction);
                if(straction.equalsIgnoreCase("Purchase"))
                {
                    transtype ="1";
                }
                else  if(straction.equalsIgnoreCase("Pre Auth"))
                {
                    transtype ="4";
                }
                else  if(straction.equalsIgnoreCase("Tokenization"))
                {
                    transtype ="12";
                }
                else  if(straction.equalsIgnoreCase("Refund"))
                {
                    transtype ="2";
                }
                else  if(straction.equalsIgnoreCase("Void Purchase"))
                {
                    transtype ="3";
                }
                else  if(straction.equalsIgnoreCase("Void Auth"))
                {
                    transtype ="9";
                }
                else  if(straction.equalsIgnoreCase("Void Refund"))
                {
                    transtype ="6";
                }
                else  if(straction.equalsIgnoreCase("Transaction Enquiry"))
                {
                    transtype ="10";
                }
//                else  if(straction.equalsIgnoreCase("NetBanking"))
//                {
//                    transtype ="NETBANK";
//                }
//                else  if(straction.equalsIgnoreCase("UPI"))
//                {
//                    transtype ="UPI";
//                }
//                else  if(straction.equalsIgnoreCase("Wallet"))
//                {
//                    transtype ="WALLET";
//                }

                try
                {
//                       Toast.makeText(Context, edtmetadata.getText().toString(), Toast.LENGTH_SHORT).show();

                    trxnPayments.makepaymentService
                            (edt_Amount.getText().toString(),MainActivity.this,
                                    transtype, currency.getText().toString(),
                                    email, address.getText().toString(),
                                    city.getText().toString(), state.getText().toString(),
                                    zip.getText().toString(),  contrycode.getText().toString(),
                                    edtTrackID.getText().toString(),strOperation,
                                    cardToken.getText().toString(),"0",
                                    edt_transId.getText().toString(),
                                    edtmetadata.getText().toString() );
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
    });



    }


    private void init() {
        edt_Amount = (EditText) findViewById(R.id.edt_Amount);
//        edt_Amount.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(10,2)});

        edt_Email = (EditText) findViewById(R.id.edt_Email);
        btn_Pay =  (Button)  findViewById(R.id.btn_Pay);
        currency = (EditText) findViewById(R.id.edt_Currency);
        address =  (EditText) findViewById(R.id.edt_Add);
        city = (EditText) findViewById(R.id.edt_City);
        state =(EditText) findViewById(R.id.edt_State);
        zip =  (EditText) findViewById(R.id.edt_ZIP);
        contrycode =(EditText) findViewById(R.id.edt_CountryCode);
//        udf1 = (EditText) findViewById(R.id.edtudf1);
//        udf2 = (EditText) findViewById(R.id.edtudf2);
//        udf3 = (EditText) findViewById(R.id.edtudf3);
//        udf4 = (EditText) findViewById(R.id.edtudf4);
//        udf5 = (EditText) findViewById(R.id.edtudf5);
        edtTrackID=findViewById(R.id.edtTrackID);
        spnCardOpertaion=findViewById(R.id.spncardOperation);
        cardToken=findViewById(R.id.edtcardToken);
        edt_transId=findViewById(R.id.edttransid);
        spnTrxnType=findViewById(R.id.spnTransType);
        edtmetadata=findViewById(R.id.edtmetadata);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data  !=null) {
            if (requestCode == 2)
            {
//              String message = data.getStringExtra("MESSAGE");
                try
                {
                    JSONObject jsonObj = new JSONObject(data.getStringExtra("MESSAGE"));
                    Intent intent = new Intent(MainActivity.this, ReceiptPage.class);
                    intent.putExtra("jsonObject", jsonObj.toString());
                    startActivity(intent);
                    finish();
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}