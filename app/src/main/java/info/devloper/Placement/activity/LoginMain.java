package info.devloper.Placement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import info.devloper.Placement.R;

/**
 * Created by Shivam Patel on 11-02-2017.
 */

public class LoginMain extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        findViewById(R.id.ripple1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(getApplicationContext(),LoginActivity.class)));
            }
        });

        findViewById(R.id.ripple2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new Intent(getApplicationContext(),CompanyLogin.class)));
            }
        });

        findViewById(R.id.ripple3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });
    }

    boolean twice;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(twice==true) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();
            System.exit(0);
        }

        Toast.makeText(LoginMain.this,"PRESS BACK TO EXIT",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice=false;
            }
        },2000);
        twice=true;
    }

}
