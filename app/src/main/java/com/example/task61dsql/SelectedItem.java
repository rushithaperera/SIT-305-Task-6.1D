package com.example.task61dsql;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SelectedItem extends AppCompatActivity {

    ImageView mainImageView;
    int myImage;

    ImageButton pay;

    public static final String TAG = "PaymentExample";
    public static final String PAYPAL_KEY = "AV7x4-OtWfXF3Zbj66jB4D5Fsh5IwNE3uvXqyT60aY9d7j2iL-GXVKHfEYIa4y6OPve4tv2hsDFNp3X3";

    public static final int REQUEST_CODE_PAYMENT = 1;
    public static final int REQUEST_CODE_FUTURE_PAYMENT = 2;

    public static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    public static PayPalConfiguration config;
    PayPalPayment itemToBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        mainImageView = findViewById(R.id.mainImageView);
        pay = findViewById(R.id.google_pay);

        getData();
        setData();

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment();
            }
        });
        configPayPal();
    }

    private void configPayPal() {
        config = new PayPalConfiguration()
                .environment(CONFIG_ENVIRONMENT)
                .clientId(PAYPAL_KEY)
                .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
                .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    }

    private void makePayment() {

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        itemToBuy = new PayPalPayment(new BigDecimal(String.valueOf("10.45")), "USD", "Payment", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent payment = new Intent(this, PaymentActivity.class);
        payment.putExtra(PaymentActivity.EXTRA_PAYMENT, itemToBuy);
        payment.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startActivityForResult(payment, REQUEST_CODE_PAYMENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if (confirmation != null) {
                    try {
                        System.out.println(confirmation.toJSONObject().toString(4));
                        System.out.println(confirmation.getPayment().toJSONObject().toString(4));
                        Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Payment has been canceled", Toast.LENGTH_LONG).show();
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == REQUEST_CODE_FUTURE_PAYMENT){
            if(resultCode == Activity.RESULT_OK){
                PayPalAuthorization authorization = data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if(authorization != null){
                    try{
                        Log.i("FuturePaymentExample",authorization.toJSONObject().toString(4));
                        String authorization_code = authorization.getAuthorizationCode();
                        Log.d("FuturePaymentExample", authorization_code);
                        Log.e("paypal","future payment code received from paypal :" + authorization_code);
                    }catch(Exception e){
                        Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show();
                        Log.e("FuturePaymentExample", "an unlikely event occurred", e);
                    }
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this,"payment has been canceled", Toast.LENGTH_LONG).show();
                Log.d("FuturePaymentExample", "The user canceled");
            }else if(resultCode == PayPalFuturePaymentActivity.RESULT_EXTRAS_INVALID){
                Toast.makeText(this,"Error occurred", Toast.LENGTH_LONG).show();
                Log.d("FuturePaymentExample", "Invalid paypal configuration");
            }
        }
    }

    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            myImage = getIntent().getIntExtra("myImage", 1);
        }else{
            Toast.makeText(this,"Data not Available", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        mainImageView.setImageResource(myImage);
    }
}