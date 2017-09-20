package info.devloper.Placement.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import info.devloper.Placement.R;
import info.devloper.Placement.connectivity_classes.Company_Login_Task;

import static info.devloper.Placement.admin.fragment.GetIP.MY_PREFS_NAME;

/**
 * Created by LUCIFER on 19-Jan-17.
 */
public class CompanyLogin extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    @Length(min = 3, message = "Enter atleast 3 character")
    private EditText musernameView;
    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    String url,IP;
    Validator validator;
    //  private UserLoginTask mAuthTask = null;

    private static final String[] DUMMY_CREDENTIALS = new String[]
            {
                    "foo@example.com:hello", "bar@example.com:world"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up the login form.
        musernameView = (EditText) findViewById(R.id.txtuser);
        mPasswordView = (EditText) findViewById(R.id.txtpass);

        validator = new Validator(this);
        validator.setValidationListener(this);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("IP", null);
        if (restoredText != null) {
            IP = restoredText;
            url = "http://"+IP+":8080/AppConnectivity/Company_Login";
            Log.d("IP --- ",url);
        }

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login_form || id == EditorInfo.IME_NULL) {
                    //attemptLogin();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.loginripple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        mLoginFormView = findViewById(R.id.loginscrall);
        //  mProgressView = findViewById(R.id.login_progress);


        Button signupbutton = (Button) findViewById(R.id.signupbtn);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });


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
            startActivity(new Intent(CompanyLogin.this, LoginActivity.class));
            finish();
        }
    }




     // Shows the progress UI and hides the login form.


    private void showProgress(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onValidationSucceeded() {
        String un=musernameView.getText().toString();
        String up=mPasswordView.getText().toString();

        String nextUrl=url+"?username="+un+"&password="+up;
        Log.d("url :- ",nextUrl);
        Company_Login_Task clt=new Company_Login_Task(getApplicationContext());
        clt.execute(nextUrl);
        nextUrl="";
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


    // Represents an asynchronous login/registration task used to authenticate
     // the user.



}
