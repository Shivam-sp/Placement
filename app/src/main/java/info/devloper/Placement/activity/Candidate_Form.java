package info.devloper.Placement.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.ConnectionTask;
import info.devloper.Placement.connectivity_classes.ImageByteArray_Task;
import pub.devrel.easypermissions.EasyPermissions;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;


/**
 * Created by Shivam Patel on 30-01-2017.
 */
public class Candidate_Form extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Validator.ValidationListener {

    private Spinner spinner_tab;
    Intent myIntent;
    Validator validator;
    //image upload----
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //image upload----end
    //date picker ----start----
    private TextView Output;
    private EditText changeDate;
    private ImageView imageView;

    private int year;
    private int month;
    private int day;

    static final int DATE_PICKER_ID = 1111;
    //date picer end ----End----
    @NotEmpty
    @Length(min = 3,message = "Length should be 3 or more")
    private EditText Firstname,Middlename,Lastname,Address;

    @NotEmpty
    @Length(min = 10,max = 11,message = "Invalid Contact no. ")
    private EditText Contactno;

    @NotEmpty
    @Length(min = 7,max = 11,message = "Invalid Contact no.")
    private EditText Alternativeno;

    @NotEmpty
    @Length(min = 6,max = 6,message = "Length Must be of 6 digits")
    @Digits(integer = 6, message = "Should be Digits")
    private EditText Pincode;

    @NotEmpty
    @Email
    private EditText Emailid;

    @NotEmpty
    @Length(min = 3,message = "Length should be 3 or more")
    private EditText City;

    @NotEmpty
    @Password(min = 6,scheme = com.mobsandgeeks.saripaar.annotation.Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText Password;

   // Button btnsubmit;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    //All Variables of form
    String candidate_id,firstname,middlename,lastname,gender,dob,email,address,city,password;
    String contactno,alternativeno,pincode;
    String IP;

    //Url of Servlet
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_form);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/AppConnectivity";
            Log.d("IP --- ",url);
        }
        changeDate = (EditText) findViewById(R.id.datepicker);

        // Get current date by calender

        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);


        // Show current date

        changeDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        // Button listener to show date picker dialog

        changeDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // On button click show datepicker dialog
                showDialog(DATE_PICKER_ID);

            }

        });

       // Candidate_ID=(EditText)findViewById(R.id.txtcnid);
        Firstname=(EditText)findViewById(R.id.txtfname);
        Middlename=(EditText)findViewById(R.id.txtmname);
        Lastname=(EditText)findViewById(R.id.txtlname);
        Contactno=(EditText)findViewById(R.id.txtcno);
        Alternativeno=(EditText)findViewById(R.id.txtano);
        Emailid=(EditText)findViewById(R.id.txtem);
        Address=(EditText)findViewById(R.id.txtaddr);

        Pincode=(EditText)findViewById(R.id.txtpin);
        City=(EditText)findViewById(R.id.txtcty);
        Password=(EditText)findViewById(R.id.txtpass);
        radioGroup=(RadioGroup)findViewById(R.id.gen);



        validator = new Validator(this);
        validator.setValidationListener(this);

        findViewById(R.id.browseripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("-------","-------");
                loadImagefromGallery();
            }
        });

        findViewById(R.id.submitcndripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                gender=radioButton.getText().toString();
                validator.validate();
            }
        });


    }

    //load image from android to servlet all methods start
    public void loadImagefromGallery() {
        Log.d("-------","00000");
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Log.d("iiiiii",galleryIntent.toString()+"");
        Log.d("-------","1111");
        // Start the Intent
        if (EasyPermissions.hasPermissions(this,galleryPermissions))
        {
            Log.d("-------","22222");
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        }
        else {
            Log.d("-------","3333");
            EasyPermissions.requestPermissions(this, "Access for storage",
                    101, galleryPermissions);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            Log.d("----","----");
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Log.e("file_path",filePathColumn+"");
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);

                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                // get the base 64 string
                String imgString = Base64.encodeToString(getBytesFromBitmap(BitmapFactory.decodeFile(imgDecodableString)),Base64.NO_WRAP);

                byte[] imgdata=getBytesFromBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
                /*for (int i=0;i<=imgdata.length;i++)
                {
                    byte d=imgdata[i];
                    Log.d("Byte Array :-", ""+d);
                }*/
               /* JSONArray imgjsonarray=new JSONArray();
                imgjsonarray.put(imgString);*/
                Log.d("jsonarray",imgString);

                ImageByteArray_Task ibt=new ImageByteArray_Task();
                String url="http://"+IP+":8080/AppConnectivity/imageload";

                ibt.execute(url,imgString);
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }


    }
    // convert from bitmap to byte array
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    //load image from android to servlet all methods end
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, year, month,day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;

            // Show selected date
            changeDate.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year));

        }
    };

    /*public void additemstospinner() {

            ArrayList<String> list = new ArrayList<String>();
            list.add("Home");
            list.add("SignupActivity");
            list.add("Login");
            list.add("Candidate Form");
            list.add("Company Form");
            list.add("Services");
            list.add("AboutUs");
            CustomSpinnerAdepter spinnerAdepter = new CustomSpinnerAdepter(getApplicationContext(), list);
            spinner_tab.setAdapter(spinnerAdepter);

        }
    */
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onValidationSucceeded() {
        firstname=Firstname.getText().toString();
        middlename=Middlename.getText().toString();
        lastname=Lastname.getText().toString();
        dob=changeDate.getText().toString();
        contactno=Contactno.getText().toString();
        alternativeno=Alternativeno.getText().toString();
        email=Emailid.getText().toString();
        address=Address.getText().toString();
        pincode=Pincode.getText().toString();
        city=City.getText().toString();
        password=Password.getText().toString();

        String[] dateob=dob.split("-");
        String mob=contactno.substring(0,2);
        String first=firstname.substring(0,1);
        String secnd=middlename.substring(0,1);
        String last=lastname.substring(0,1);

        Bundle b=new Bundle();
        String imagepath=b.getString("imgpath");

        candidate_id=dateob[2]+mob+first+secnd+last;
        url=url+"?candidate_id="+candidate_id+"&firstname="+firstname+"&middlename="+middlename+"&lastname="+lastname+"&gender="+gender+"&dob="+dob+"&contactno="+contactno+"&alternativeno="+alternativeno+"&email="+email+"&address="+address+"&pincode="+pincode+"&city="+city+"&imagepath="+imagepath+"&password="+password;
        Log.d("candidatw form url",url);

        ConnectionTask ct=new ConnectionTask(getApplicationContext());
        ct.execute(url);
        Toast.makeText(this,candidate_id,Toast.LENGTH_LONG).show();

        Intent intent=new Intent(Candidate_Form.this,Qualification.class);
        Toast.makeText(getApplicationContext(),"Next page",Toast.LENGTH_LONG).show();
        intent.putExtra("Candidate_ID",candidate_id);
        startActivity(intent);
}

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    //@Override
   /* public void onDrawerItemSelected(View view, int position) {

    }

    @Override
    public void onNavFragmentInteraction(Uri uri) {

    }*/

    /*@Override
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

}
