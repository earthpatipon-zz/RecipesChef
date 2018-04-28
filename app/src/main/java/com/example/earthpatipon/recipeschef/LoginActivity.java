/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef;

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
import com.example.earthpatipon.recipeschef.utils.DatabaseInitializer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.input_username) EditText usernameInput;
    @BindView(R.id.input_password) EditText passwordInput;
    @BindView(R.id.button_login) Button loginButton;
    @BindView(R.id.button_signup) Button signupButton;
    @BindView(R.id.button_bypass) Button bypassButton;

    private ProgressDialog progressDialog;
    private String userName;
    private String passWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initDatabase();

        // Bypass to wherever I want
        // TODO; remove from production code
        bypassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = "bypass";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //PACK DATA
                intent.putExtra("SENDER_KEY", "Bypass");
                intent.putExtra("NAME_KEY", userName);
                startActivity(intent);
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

    @Override
    public void onBackPressed() {

        // Disable going back to the WelcomeActivity as we started from WelcomeActivity
        moveTaskToBack(true);
    }

    private void login() {

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

    // This method is called when user is validated as passed
    private void onLoginSuccess() {

        progressDialog.dismiss();
        loginButton.setEnabled(true);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // Pack data
        intent.putExtra("SENDER_KEY", "LoginActivity");
        intent.putExtra("NAME_KEY", userName);

        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    // This method is called when user is validated as not passed
    private void onLoginFailed() {

        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    private boolean validate() {

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

    private void initDatabase(){

        DatabaseInitializer di = new DatabaseInitializer(this);
        di.populateAsync(AppDatabase.getInstance(this)); // Call DatabaseInitializer class to init dataset into database
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
                    Toast.makeText(getApplicationContext(), "Password isn't correct", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),  "This username doesn't exist, try to signup first!", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            loginButton.setEnabled(true);
        }
    }
}
