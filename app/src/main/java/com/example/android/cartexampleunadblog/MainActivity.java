package com.example.android.cartexampleunadblog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ModelProducts productsObject;
    int productsize;
    Controller ct=new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         addAvailableProducts();

        final LinearLayout layout = (LinearLayout)findViewById(R.id.linearMain);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        for (int j=0;j< productsize;j++)
        {
            addProductToPage(layout, params, j);
        }

        CheckOutOnClick();
        }

    private void addProductToPage(LinearLayout layout, LayoutParams params, int j) {
        productsObject = ct.getProducts(j);

        String pName = productsObject.getProductName();
        int pPrice = productsObject.getProductPrice();

        LinearLayout la = new LinearLayout(this);
        la.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv = new TextView(this);
        tv.setText(" "+pName+" ");
        la.addView(tv);
        TextView tv1 = new TextView(this);
        tv1.setText("$"+pPrice+" ");
        la.addView(tv1);
        final Button btn1 = new Button(this);
        btn1.setId(j+1);
        btn1.setText("Add to Cart");
        btn1.setLayoutParams(params);
        final int index = j;

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(!ct.getCart().CheckProductInCart(productsObject)){
                    btn1.setText("Item Added");
                    ct.getCart().setProducts(productsObject);
                    Toast.makeText(getApplicationContext(), "New CartSize:" +ct.getCart().getCartsize(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Products"+(index+1)+"Already Added",Toast.LENGTH_LONG ).show();
                }
            }
        });
        la.addView(btn1);
        layout.addView(la);
    }

    private void addAvailableProducts() {
        ModelProducts products = null;
        for(int i= 1; i<=7;i++){
            int Price = 15+ i;
            products = new ModelProducts("Product Item" +i, "Description"+i, Price);
            ct.setProducts(products);
        }
        productsize = ct.getProductArraylistsize();
    }

    private void CheckOutOnClick() {
        //final Context context=this;
        final Button btn = (Button)findViewById(R.id.second);
        btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent in = new Intent(getBaseContext(),Screen1.class);
            startActivity(in);
        }
    });
    }
}

