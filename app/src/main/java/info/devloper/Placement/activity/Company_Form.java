package info.devloper.Placement.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.ConnectionTask;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by Shivam Patel on 30-01-2017.
 */
public class Company_Form extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Validator.ValidationListener {

    String IP;
    private Spinner spinner_tab;
    private Button submitform;
    Validator validator;

    @NotEmpty
    @Length(min = 3,message = "Length should be 3 or more")
    private EditText Companyname,Address;

    @NotEmpty
    @Length(min = 6,max = 6,message = "Length Must be of 6 digits")
    @Digits(integer = 6, message = "Should be Digits")
    private EditText Pincode;

    @NotEmpty
    @Length(min = 3,message = "Length should be 3 or more")
    private EditText City;

    @NotEmpty
    @Length(min = 10,max = 11,message = "Invalid Contact no. ")
    private EditText Contactno;

    @NotEmpty
    @Email
    private EditText Emailid;
    private EditText Url;
    private EditText Briefprofile;

    @NotEmpty
    @com.mobsandgeeks.saripaar.annotation.Password
    private EditText Password;

    //All Variables of form
    String cid,companyname,address,email,city,password,briefprofile,cmpurl;
    String contactno,pincode;

    //Url of Servlet
    String url;

    Intent myIntent;

    public Company_Form() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_form);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Company_Form_server";
            Log.d("IP --- ",url);
        }

        Companyname=(EditText)findViewById(R.id.txtcname);
        Address=(EditText)findViewById(R.id.txtcmpaddr);
        Pincode=(EditText)findViewById(R.id.txtcmppin);
        City=(EditText)findViewById(R.id.txtccity);
        Contactno=(EditText)findViewById(R.id.txtcmpcno);
        Emailid=(EditText)findViewById(R.id.txtcmpemail);
        Url=(EditText)findViewById(R.id.txtcmpurl);
        Password=(EditText)findViewById(R.id.txtcmppass);
        Briefprofile=(EditText)findViewById(R.id.txtbprofile);

        validator = new Validator(this);
        validator.setValidationListener(this);

        findViewById(R.id.submitcmpripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
       /* submitform=(Button)findViewById(R.id.submitcmp);
        submitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Submitted",Toast.LENGTH_SHORT).show();
            }
        });
*/
    }

    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000)
        {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press again to go back",
                    Toast.LENGTH_SHORT).show();


        }
        else
        {
            super.onBackPressed();
            startActivity(new Intent(Company_Form.this, LoginActivity.class));
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

   /* public void additemstospinner()
    {
        ArrayList<String> list=new ArrayList<String>();
        list.add("Home");
        list.add("SignupActivity");
        list.add("Login");
        list.add("Candidate Form");
        list.add("Company Form");
        list.add("Services");
        list.add("AboutUs");

        CustomSpinnerAdepter spinnerAdepter = new CustomSpinnerAdepter(getApplicationContext(),list);
        spinner_tab.setAdapter(spinnerAdepter);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public void onDrawerItemSelected(View view, int position) {

    }

    @Override
    public void onNavFragmentInteraction(Uri uri) {

    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), "Selected  : " + item,
                Toast.LENGTH_LONG).show();

        switch (item) {
            // case "Home": myIntent=new Intent(getApplicationContext(),activity.MainActivity.class);
            //  startActivity(myIntent);
            // break;
            case "SignupActivity":
                myIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(myIntent);
                break;
            case "Login":
                myIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(myIntent);
                break;
            case "Candidate Form":
                myIntent = new Intent(getApplicationContext(), Candidate_Form.class);
                startActivity(myIntent);
                break;
            case "Company Form":
                myIntent = new Intent(getApplicationContext(), Company_Form.class);
                startActivity(myIntent);
                break;
            case "Services":
                myIntent = new Intent(getApplicationContext(), Services.class);
                startActivity(myIntent);
                break;
            case "AboutUs":
                myIntent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(myIntent);
                break;
        }

    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onValidationSucceeded()
    {
        Toast.makeText(getApplicationContext(),"Submit Button",Toast.LENGTH_LONG);
        companyname=Companyname.getText().toString();
        contactno=Contactno.getText().toString();
        email=Emailid.getText().toString();
        address=Address.getText().toString();
        pincode=Pincode.getText().toString();
        city=City.getText().toString();
        cmpurl=Url.getText().toString();
        briefprofile=Briefprofile.getText().toString();
        password=Password.getText().toString();

        cid="1"+pincode.substring(3,5);

        url=url+"?cid="+cid+"&companyname="+companyname+"&address="+address+"&pincode="+pincode+"&city="+city+"&contactno="+contactno+"&email="+email+"&cmpurl="+cmpurl+"&briefprofile="+briefprofile+"&password="+password;

        Toast.makeText(this,"Succsessfully Inserted",Toast.LENGTH_LONG).show();
        ConnectionTask ct=new ConnectionTask(getApplicationContext());
        ct.execute(url);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Toast.makeText(getApplicationContext(),"Enter valid details",Toast.LENGTH_LONG).show();
    }
}
