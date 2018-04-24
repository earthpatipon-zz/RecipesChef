/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_username) EditText usernameInput;
    @BindView(R.id.input_password) EditText passwordInput;
    @BindView(R.id.button_login) Button loginButton;
    @BindView(R.id.button_signup) Button signupButton;
    @BindView(R.id.button_bypass) Button bypassButton;
    @BindView(R.id.button_bypass2) Button bypassButton2;

    private ProgressDialog progressDialog;
    private String userName;
    private String passWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Bypass to wherever I want
        // TODO; remove from production code
        bypassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        // TODO; remove from production code
        bypassButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecipeActivity.class));
            }
        });

        // Create Dialog (like pop-up) object
        progressDialog = new ProgressDialog(this, R.style.AppTheme_White_Dialog);

        // Waiting to do an action after user click on login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Waiting to do an action after user click on sign-up button
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

        // Set Dialog object to show message
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

        // Disable going back to the MainActivity as we started from MainActivity
        moveTaskToBack(true);
    }

    // This method is called when user is validated as passed
    public void onLoginSuccess() {

        progressDialog.dismiss();
        loginButton.setEnabled(true);
        // *** must be fix to send value to HomeActivity i,e user.
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish(); // this method is to call the rest of android lifecycle component i.e, onDestroy
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    // This method is called when user is validated as not passed
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

    // A thread that will retrieve database to get data and further send it to validate
    private class loginAsyncTask extends AsyncTask<String, Void, Boolean> {

        private boolean exist;
        private boolean correctPass;
        private String userName;
        private String passWord;

        loginAsyncTask(String username, String password) {
            this.userName = username;
            this.passWord = password;
        }

        // It is a declaration of thread function
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

        // The result from doInBackground will be arrived here
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
