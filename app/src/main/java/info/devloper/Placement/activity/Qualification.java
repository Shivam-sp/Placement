package info.devloper.Placement.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;
import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.Degree_Connection_Task;
import info.devloper.Placement.connectivity_classes.QualificationTask;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;


/**
 * Created by Shivam Patel on 31-01-2017.
 */
public class Qualification extends AppCompatActivity implements Validator.ValidationListener {

    private Spinner spinner_tab;
    private Button submitform;
    Intent myIntent;
    Validator validator;
    @NotEmpty
    @Length(min = 3,message = "Length should be 3 or more")
    private EditText Brdorclg,Yop;
    private EditText Percent,Grade;
    private MaterialSpinner Degree;
    String degree,brdorclg,yop,percent,grade;
    String url,spinnerurl,IP;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qualification);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url="http://"+IP+":8080/AppConnectivity/Qualification_Server";
            Log.d("IP --- ",url);
        }

        //Url of Servlet
        url ="http://"+IP+":8080/AppConnectivity/Qualification_Server";
        spinnerurl ="http://"+IP+":8080/AppConnectivity/Degree_Data";
        Degree=(MaterialSpinner) findViewById(R.id.degreespinner);
        Brdorclg=(EditText)findViewById(R.id.txtclg);
        Yop=(EditText)findViewById(R.id.txtypassing);
        Percent=(EditText)findViewById(R.id.txtpercnt);
        Grade=(EditText)findViewById(R.id.txtgrd);

        //All String Variables

       Degree_Connection_Task dct=new Degree_Connection_Task(getApplicationContext(),Degree);
        dct.execute(spinnerurl);
        validator = new Validator(this);
        validator.setValidationListener(this);

        findViewById(R.id.btndone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degree=Degree.getSelectedItem().toString();
                validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded()
    {
        Toast.makeText(this,degree,Toast.LENGTH_LONG).show();
        brdorclg=Brdorclg.getText().toString();
        yop=Yop.getText().toString();
        percent=Percent.getText().toString();
        grade=Grade.getText().toString();

        Intent intent=getIntent();
        String candidate_id=intent.getStringExtra("Candidate_ID");
        degree=degree.replace(" ","_");
        url=url+"?candidate_id="+candidate_id+"&degree="+degree+"&brdorclg="+brdorclg+"&yop="+yop+"&percent="+percent+"&grade="+grade;

        QualificationTask qt=new QualificationTask(getApplicationContext());
        qt.execute(url);

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }



    /*public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), "Selected  : " + item,
                Toast.LENGTH_LONG).show();

        switch(item)
        {
            //case "Home": myIntent=new Intent(getApplicationContext(),activity.MainActivity.class);
                //startActivity(myIntent);
              //  break;
            case "SignUp": myIntent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(myIntent);
                break;
            case "Login": myIntent=new Intent(getApplicationContext(),Login.class);
                startActivity(myIntent);
                break;
            case "Candidate Form": myIntent=new Intent(getApplicationContext(),Candidate_Form.class);
                startActivity(myIntent);
                break;
            case "Company Form": myIntent=new Intent(getApplicationContext(),Company_Form.class);
                startActivity(myIntent);
                break;
            case "Services": myIntent=new Intent(getApplicationContext(),Services.class);
                startActivity(myIntent);
                break;
            case "AboutUs": myIntent=new Intent(getApplicationContext(),AboutUs.class);
                startActivity(myIntent);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
