package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView quantityTextView;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);

        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for: " + getEditTextName());
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void decrement(View view) {
        if (quantity == 1) {

        } else {
            quantity -= 1;
            displayQuantity(quantity);
        }
    }

    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    private void displayQuantity(int number) {
        quantityTextView.setText("" + number);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean chocolate) {
        int price = quantity * 5;
        if (hasWhippedCream) {
            price += 1;
        }
        if (chocolate) {
            price += 2;
        }
        return price;
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Nome: " + getEditTextName();
        priceMessage += "\nDeseja Adicionar chantily? " + addWhippedCream;
        priceMessage += "\nDeseja Adicionar chocolate? " + addChocolate;
        priceMessage += "\nQuantidade: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nObrigado!";
        return priceMessage;
    }

    private String getEditTextName() {
        EditText nameEditText = (EditText) findViewById(R.id.name_text_view);
        return nameEditText.getText().toString();
    }
}
