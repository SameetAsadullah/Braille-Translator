package com.sameetasadullah.i180479_i180531.presentationLayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sameetasadullah.i180479_i180531.R;
import com.sameetasadullah.i180479_i180531.logicLayer.HRS;

public class Register_Screen extends AppCompatActivity {

    String Page = getIntent().getStringExtra("Page");
    ImageView backButton;
    EditText name,email,contact,card,cnic,address,password;
    RelativeLayout signup_Button;
    HRS hrs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        backButton = findViewById(R.id.back_button);
        name = findViewById(R.id.Name_text);
        email = findViewById(R.id.Email_text);
        contact = findViewById(R.id.Contact_text);
        cnic = findViewById(R.id.CNIC_text);
        address = findViewById(R.id.Address_text);
        password = findViewById(R.id.Password_text);
        card = findViewById(R.id.Card_text);
        hrs = new HRS(Register_Screen.this);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Screen.this,Login_Screen.class);
                startActivity(intent);
            }
        });

        signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString();
                String Cnic=cnic.getText().toString();
                String Email=email.getText().toString();
                String Contact=contact.getText().toString();
                String Address=address.getText().toString();
                String Password=name.getText().toString();
                String Card=name.getText().toString();

                if(Name.equals("") ||Cnic.equals("") ||Password.equals("") ||Card.equals("") ||Address.equals("") ||Contact.equals("") ||Email.equals("")  ){
                    Toast.makeText(Register_Screen.this,"Please Fill All Blocks",Toast.LENGTH_LONG).show();
                }
                else{
                    if(Page.equals("Customer")){
                        if(hrs.validateCustomerEmail(Email)==Boolean.TRUE){
                            Toast.makeText(Register_Screen.this,"Account with this Email Already Exists",Toast.LENGTH_LONG).show();
                        }
                        else{
                            hrs.registerCustomer(Name,Email,Password,Address,Contact,Cnic,Card);
                            Intent intent=new Intent(Register_Screen.this,Customer_Choose_Option_Screen.class);
                            intent.putExtra("Email", Email);
                            startActivity(intent);
                        }
                    }
                    else{
                        if(hrs.validateVendorEmail(Email)==Boolean.TRUE){
                            Toast.makeText(Register_Screen.this,"Account with this Email Already Exists",Toast.LENGTH_LONG).show();
                        }
                        else{
                            hrs.registerVendor(Name,Email,Password,Address,Contact,Cnic,Card);
                            Intent intent=new Intent(Register_Screen.this,Vendor_Choose_Option_Screen.class);
                            intent.putExtra("Email", Email);
                            startActivity(intent);
                        }

                    }
                }
            }
        });
    }
}