package com.example.android.justjava2;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void increment(View view) {
        if (quantity != 100) {
            quantity = quantity + 1;
            display(quantity);
        }
        return;
    }
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.minimum_message), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);

    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox haschantilly = (CheckBox) findViewById(R.id.chantilly_checkbox);
        boolean isChantillyChecked = haschantilly.isChecked();

        CheckBox haschocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean isChocolateChecked = haschocolate.isChecked();

        int priceOrder = calculatePrice(isChantillyChecked,isChocolateChecked);

        EditText orderName = (EditText) findViewById(R.id.nome_text_view);
        String name = orderName.getText().toString();

//        String priceMessage = createOrderSummary(name,priceOrder,isChantillyChecked,isChocolateChecked);
  //      displayMessage(priceMessage);

        createOrderSummary(name,priceOrder,isChantillyChecked,isChocolateChecked);


    }
    private void createOrderSummary (String name,int price, boolean isChantillyChecked, boolean isChocolateChecked){
        String orderSummary = getString(R.string.order_summary_1,name);
        orderSummary += "\n" + getString(R.string.order_summary_2,isChantillyChecked);
        orderSummary += "\n" + getString(R.string.order_summary_3,isChocolateChecked);
        orderSummary += "\n" + getString(R.string.order_summary_4,quantity);
        orderSummary += "\n" + getString(R.string.order_summary_5, price);
        orderSummary += "\n" + getString(R.string.order_summary_6);
        //return (orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_email,name));
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private int calculatePrice(boolean adChantillyChecked, boolean adChocolateChecked){

        int priceBase = 5;

        if (adChantillyChecked) {
            priceBase += 1;
        }
        if (adChocolateChecked) {
            priceBase += 2;
        }
        return (quantity * priceBase );
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_Summary_text_view);
//        orderSummaryTextView.setText(message);
//
//    }

}
