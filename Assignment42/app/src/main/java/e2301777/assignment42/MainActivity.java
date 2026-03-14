package e2301777.assignment42;

import android.content.res.Configuration;
import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.security.PublicKey;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText productId, productName, productPrice, productAmount;
    TextView allProducts;
    private ScrollView lsScrollView;

    private int orientation;
    LayoutParams viewLayoutParams = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 50;
        viewLayoutParams.bottomMargin = 10;
// Here we create the layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
// Here we define a text view
        TextView idLabel = new TextView(this);
        idLabel.setText(R.string.productId);
        idLabel.setLayoutParams(viewLayoutParams);
        idLabel.setTextSize(15);
        linearLayout.addView(idLabel);
// Here we define the edit text
        productId= new EditText(this);
        productId.setLayoutParams(viewLayoutParams);
        productId.setInputType(InputType.TYPE_CLASS_NUMBER);
        productId.setTextSize(15);
        linearLayout.addView(productId);

        TextView nameLabel = new TextView(this);
        nameLabel.setText(R.string.productName);
        nameLabel.setLayoutParams(viewLayoutParams);
        nameLabel.setTextSize(15);
        linearLayout.addView(nameLabel);
// Here we define the edit text
        productName= new EditText(this);
        productName.setLayoutParams(viewLayoutParams);
        productName.setInputType(InputType.TYPE_CLASS_TEXT);
        productName.setTextSize(15);
        linearLayout.addView(productName);

        TextView priceLabel = new TextView(this);
        priceLabel.setText(R.string.unitPrice);
        priceLabel.setLayoutParams(viewLayoutParams);
        priceLabel.setTextSize(15);
        linearLayout.addView(priceLabel);
// Here we define the edit text
        productPrice= new EditText(this);
        productPrice.setLayoutParams(viewLayoutParams);
        productPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        productPrice.setTextSize(15);
        linearLayout.addView(productPrice);

        TextView amountLabel = new TextView(this);
        amountLabel.setText(R.string.totalAmount);
        amountLabel.setLayoutParams(viewLayoutParams);
        amountLabel.setTextSize(15);
        linearLayout.addView(amountLabel);
// Here we define the edit text
        productAmount= new EditText(this);
        productAmount.setLayoutParams(viewLayoutParams);
        productAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        productAmount.setTextSize(15);
        linearLayout.addView(productAmount);
// Here we define a text view
        Button addButton = new Button(this);
        addButton.setLayoutParams(viewLayoutParams);
        addButton.setInputType(InputType.TYPE_CLASS_TEXT);
        addButton.setText(R.string.add);
        addButton.setTextSize(15);
        linearLayout.addView(addButton);

        Button resetButton = new Button(this);
        resetButton.setLayoutParams(viewLayoutParams);
        resetButton.setInputType(InputType.TYPE_CLASS_TEXT);
        resetButton.setText(R.string.reset);
        resetButton.setTextSize(15);
        linearLayout.addView(resetButton);

        ScrollView scrollView = new ScrollView(this);
        allProducts = new TextView(this);
        allProducts.setPadding(20,5,0,5);
        scrollView.addView(allProducts);
        linearLayout.addView(scrollView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProduct();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                productId.setText("");
                productName.setText("");
                productPrice.setText("");
                productAmount.setText("");
            }
        });

        orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            lsScrollView = new ScrollView(this);
            lsScrollView.addView(linearLayout);
            setContentView(lsScrollView);
        }
        else{
            setContentView(linearLayout);
        }

        updateProductList();
    }

    private void addNewProduct(){
        if(productId.getText().toString().isEmpty() ||
                productName.getText().toString().isEmpty() ||
                productPrice.getText().toString().isEmpty() ||
                productAmount.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
            return;
        }
        String id = productId.getText().toString();
        if(ProductHandler.checkDuplicate(id)){
            String errorMsg = getString(R.string.errorMsg, id);
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
            productId.requestFocus();
            return;
        }
        String name = productName.getText().toString();
        double price = Double.parseDouble(productPrice.getText().toString());
        int amount = Integer.parseInt(productAmount.getText().toString());

        Product product = new Product(id, name, price, amount);
        ProductHandler.addProduct(product);

        updateProductList();

    };

    private void updateProductList(){
        allProducts.setText(ProductHandler.getAllProducts());

    }
}