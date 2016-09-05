package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView quantityTextView;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        display(quantity);


    }

    public void submitOrder(View view) {
        displayPrice(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            return;
        } else {
            quantity -= 1;
            display(quantity);
        }
    }

    public void increment(View view) {
        quantity += 1;
        display(quantity);

    }

    private void display(int number) {
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        int price = number * 10;
        priceTextView.setText(messageTotal(price));
    }

    public String messageTotal(int total){
        String message = "R$ " + total + "\nObrigado!";
        return message;
    }
}
