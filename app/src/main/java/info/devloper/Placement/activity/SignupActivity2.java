package info.devloper.Placement.activity;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.devloper.Placement.R;


class MyAdapter implements SpinnerAdapter
{
    //String data[];
    ArrayList<String> list = new ArrayList<String>();


    Context ctx;

    public  MyAdapter(ArrayList<String> d, Context ctx)
    {
        list = d;
        this.ctx=ctx;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public View getDropDownView(int B, View cv, ViewGroup parent)
    {
        return getView(B,cv,parent);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView tv = new TextView(ctx);
        tv.setText(list.get(position));
        return tv;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}























public class SignupActivity2 extends AppCompatActivity
{




    private UserSignUpTask2 mAuthTask2 = null;

    // UI references.

    Spinner sp;
    private EditText mAnswerView;
    private EditText mHighSecurityPinView;
    private EditText mReEnterHighSecurityPinView;
    private View mProgressView2;
    private View mSignUpFormView2;

    ArrayList<String> list = new ArrayList<String>();
    String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);


        // Set up the signup form.
        mAnswerView=(EditText) findViewById(R.id.answer);
        mHighSecurityPinView= (EditText) findViewById(R.id.highsecuritypin);
        mReEnterHighSecurityPinView=(EditText)findViewById(R.id.rehighsecuritypin);

        list.add("What is your favourite holiday place?");
        list.add("What was the name of your first teacher?");
        list.add("What is your pet name?");
        list.add("What is your secret hiding place?");

        sp=(Spinner)findViewById(R.id.spinner);
        MyAdapter ad= new MyAdapter(list,this);
        sp.setAdapter(ad);


        mReEnterHighSecurityPinView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.sign_up2 || id == EditorInfo.IME_NULL) {
                    attemptSignup2();
                    return true;
                }
                return false;
            }
        });

        Button SignUpButton = (Button) findViewById(R.id.sign_up_button2);
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignup2();
            }
        });

        mSignUpFormView2 = findViewById(R.id.sign_up_form2);
        mProgressView2 = findViewById(R.id.sign_up_progress2);
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid username,invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSignup2() {
        if (mAuthTask2 != null) {
            return;
        }

        // Reset errors.
        mAnswerView.setError(null);
        mHighSecurityPinView.setError(null);
        mReEnterHighSecurityPinView.setError(null);


        // Store values at the time of the login attempt.

        String answer = mAnswerView.getText().toString();
        String highsecuritypin = mHighSecurityPinView.getText().toString();
        String rehighsecuritypin= mReEnterHighSecurityPinView.getText().toString();


        boolean cancel = false;
        View focusView = null;


        // Check for a valid answer, if the user entered one.
        if (TextUtils.isEmpty(answer)) {
            mAnswerView.setError("Enter Answer");
            focusView = mAnswerView;
            cancel = true;
        }



        // Check for a valid highsecuritypin.
        if (TextUtils.isEmpty(highsecuritypin)) {
            mHighSecurityPinView.setError(getString(R.string.error_field_required));
            focusView = mHighSecurityPinView;
            cancel = true;
        }

        if (!isPinValid(highsecuritypin)) {
            mHighSecurityPinView.setError("Invalid pin");
            focusView = mHighSecurityPinView;
            cancel = true;
        }


        // Check for a valid re entered pin, if the user entered one.
        if (TextUtils.isEmpty(rehighsecuritypin)) {
            mReEnterHighSecurityPinView.setError(getString(R.string.error_field_required));
            focusView = mReEnterHighSecurityPinView;
            cancel = true;
        }
        if (!isReEnterPinValid(highsecuritypin,rehighsecuritypin)) {
            mReEnterHighSecurityPinView.setError("Pins do not match");
            focusView = mReEnterHighSecurityPinView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            mAuthTask2 = new UserSignUpTask2(answer,highsecuritypin,rehighsecuritypin);
            mAuthTask2.onPostExecute(null);
        }

    }






    private boolean isPinValid(String highpin) {
        //TODO: Replace this with your own logic
        return highpin.length() > 4;
    }


    public boolean isReEnterPinValid(String pin,String confirmpin)
    {
        boolean pstatus = false;
        if (confirmpin != null && pin != null)
        {

            if (pin.equals(confirmpin))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignUpFormView2.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignUpFormView2.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignUpFormView2.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView2.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView2.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView2.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView2.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignUpFormView2.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserSignUpTask2 extends AsyncTask<Void, Void, Boolean> {

        private  String answer;
        private final String mpin;
        private final String mrepin;


        UserSignUpTask2(String ans,String pin, String repin) {
            answer=ans;
            mpin = pin;
            mrepin = repin;


        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask2 = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mHighSecurityPinView.setError("Incorrect Pin");
                mReEnterHighSecurityPinView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask2 = null;
            showProgress(false);
        }
    }
}
