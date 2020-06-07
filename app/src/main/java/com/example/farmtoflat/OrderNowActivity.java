package com.example.farmtoflat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderNowActivity extends AppCompatActivity {

    Button callOrder;
    LinearLayout whatsappOrder;
    TextView whatsappNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);

        final String WhatsappNumber = "+91"+"9999999999";

        callOrder = findViewById(R.id.call_order);
        whatsappOrder = findViewById(R.id.whatsapp_order);
        whatsappNo = findViewById(R.id.whatsapp_no);


        callOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial = "+919999999999";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
            }
        });



        whatsappNo.setText(WhatsappNumber);
        whatsappOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri uri = Uri.parse("smsto:" + WhatsappNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.setPackage("com.whatsapp");
                startActivity(intent);*/


                String url = "https://api.whatsapp.com/send?phone="+WhatsappNumber+"&text=Start writing your Order:-";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}