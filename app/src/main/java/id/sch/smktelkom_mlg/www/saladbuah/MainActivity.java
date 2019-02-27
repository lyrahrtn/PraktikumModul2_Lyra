package id.sch.smktelkom_mlg.www.saladbuah;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama: "+name);

        CheckBox whippedcreamChekBox= (CheckBox) findViewById(R.id.WhippedCream_checkbox);
        boolean haswhippedcream=whippedcreamChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haswhippedcream);

        CheckBox kejuChekBox= (CheckBox) findViewById(R.id.Keju_checkbox);
        boolean haskeju=kejuChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has whippedcream:"+haskeju);

        int price=calculateprice(haswhippedcream,haskeju);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,haswhippedcream,haskeju);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addwhipedcream,boolean addkeju){//jumlah pesanan * harga
        int harga=10000;

        if(addwhipedcream){
            harga=harga+3000;//harga tambahan toping
        }

        if (addkeju){
            harga=harga+3000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addkeju, boolean addWhippedCream) {//hasil pemesanan
        String pricemessage=" Nama = "+name;
        pricemessage+="\n Tambahkan Krim =" +addWhippedCream;
        pricemessage+="\n Tambahkan Keju =" +addkeju;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}