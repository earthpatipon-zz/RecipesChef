package com.example.earthpatipon.recipeschef;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_username) EditText userNameInput;
    @BindView(R.id.input_password) EditText passWordInput;
    @BindView(R.id.button_signup) Button signupButton;
    //@BindView(R.id.button_back) Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Finish the registration screen and return to the Login activity
//                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//            }
//        });

    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String userName = userNameInput.getText().toString();
        final String password = passWordInput.getText().toString();
        //final boolean check;

//      TODO: Implement your own signup logic here.
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User(userName, password);
                AppDatabase.getInstance(getApplicationContext()).userDao().insert(user);
                //onSignupSuccess();
            }
        }).start();



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

//        String userName = userNameInput.getText().toString();
//        String password = passWordInput.getText().toString();
//
//        if (userName.isEmpty() || userName.length() < 3) {
//            userNameInput.setError("at least 3 characters");
//            valid = false;
//        } else {
//            userNameInput.setError(null);
//        }
//
//        if (birthDate.isEmpty()) {
//            birthDateInput.setError("Enter Valid Birth Date");
//            valid = false;
//        } else {
//            birthDateInput.setError(null);
//        }
//
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailInput.setError("enter a valid email address");
//            valid = false;
//        } else {
//            emailInput.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            passWordInput.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            passWordInput.setError(null);
//        }
//
//        if (confirmPassword.isEmpty() || confirmPassword.length() < 4 || confirmPassword.length() > 10 || !(confirmPassword.equals(password))) {
//            confirmPasswordInput.setError("Password Do not match");
//            valid = false;
//        } else {
//            confirmPasswordInput.setError(null);
//        }

        return valid;
    }

}
