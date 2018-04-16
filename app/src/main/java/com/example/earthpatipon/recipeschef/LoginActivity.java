package com.example.earthpatipon.recipeschef;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_username) EditText usernameInput;
    @BindView(R.id.input_password) EditText passwordInput;
    @BindView(R.id.button_login) Button loginButton;
    @BindView(R.id.button_signup) TextView signupButton;

    public String userName;
    public String passWord;
    public ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        userName = usernameInput.getText().toString();
        passWord = passwordInput.getText().toString();

        // Call thread to access DAO and check user exists or not
        new loginAsyncTask(this.userName, this.passWord).execute();
    }

    @Override
    public void onBackPressed() {

        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {

        progressDialog.dismiss();
        loginButton.setEnabled(true);
        // *** must be fix to send value to HomeActivity i,e user.
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void onLoginFailed() {

        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {

        boolean valid = true;

        userName = usernameInput.getText().toString();
        passWord = passwordInput.getText().toString();

        if (userName.isEmpty() || userName.length() < 3) {
            usernameInput.setError("at least 3 characters");
            valid = false;
        } else {
            usernameInput.setError(null);
        }

        if (passWord.isEmpty() || passWord.length() < 4 || passWord.length() > 10) {
            passwordInput.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        return valid;
    }

    private class loginAsyncTask extends AsyncTask<String, Void, Boolean> {

        private boolean exist;
        private boolean correctPass;
        private String userName;
        private String passWord;

        loginAsyncTask(String username, String password) {
            this.userName = username;
            this.passWord = password;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            User temp = AppDatabase.getInstance(getApplicationContext()).userDao().findByName(userName);
            if(temp == null) exist = false;
            else{
                exist = true;
                if(temp.getPassWord().equals(this.passWord)){
                    correctPass = true;
                }
                else {
                    correctPass = false;
                }
            }
            return exist;
        }

        @Override
        protected void onPostExecute(Boolean exist) {
            progressDialog.dismiss();
            if(exist){
                if(correctPass){
                    onLoginSuccess();
                }
                else{
                    Toast.makeText(getBaseContext(), "Password isn't correct", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getBaseContext(),  "This username doesn't exist, try to signup first!", Toast.LENGTH_LONG).show();
                //can set some interval
                progressDialog.dismiss();
            }
            loginButton.setEnabled(true);
        }
    }
}
