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

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.username_text) EditText usernameText;
    @BindView(R.id.edit_newpassword) EditText passwordEdit;
    @BindView(R.id.edit_reenter_newpassword) EditText confirmPasswordEdit;
    @BindView(R.id.button_confirm) Button confirmButton;

    private ProgressDialog progressDialog;
    private String userName;
    private String passWord;
    private String confirmPassword;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        ButterKnife.bind(this);

        getCurrentUser();

        usernameText.setText(user.getUserName());
        progressDialog = new ProgressDialog(this, R.style.AppTheme_White_Dialog);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    private void edit() {

        if (!validate()) {
            onEditFailed();
            return;
        }

        confirmButton.setEnabled(false);

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Editing Account...");
        progressDialog.show();

        this.userName = usernameText.getText().toString();
        this.passWord = passwordEdit.getText().toString();

        // Call thread to access DAO and edit password
        new editAsyncTask(this.userName, this.passWord).execute();
    }


    private void onEditSuccess() {
        confirmButton.setEnabled(true);

        Toast.makeText(getApplicationContext(), "Edit Profile Successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void onEditFailed() {
        Toast.makeText(getApplicationContext(), "Edit Profile failed", Toast.LENGTH_SHORT).show();
        confirmButton.setEnabled(true);
    }

    private boolean validate() {

        boolean valid = true;

        passWord = passwordEdit.getText().toString();
        confirmPassword = confirmPasswordEdit.getText().toString();

        if (passWord.isEmpty() || passWord.length() < 4 || passWord.length() > 10) {
            passwordEdit.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordEdit.setError(null);
        }

        if (confirmPassword.isEmpty() || confirmPassword.length() < 4 || confirmPassword.length() > 10 || !(confirmPassword.equals(passWord))) {
            confirmPasswordEdit.setError("Password Do not match");
            valid = false;
        } else {
            confirmPasswordEdit.setError(null);
        }

        return valid;
    }

    private void getCurrentUser(){

        String userName = "";

        final String sender = this.getIntent().getExtras().getString("SENDER_KEY");
        if (sender != null) {
            Intent intent = getIntent();
            userName = intent.getStringExtra("NAME_KEY");
        }

        user = AppDatabase.getInstance(getApplicationContext()).userDao().findByName(userName);
    }

    private class editAsyncTask extends AsyncTask<String, Void, Void> {

        private int userID;
        private String userName;
        private String passWord;

        editAsyncTask(String username, String password) {
            this.userID = user.getUserID();
            this.userName = username;
            this.passWord = password;
        }

        @Override
        protected Void doInBackground(String... params) {
            AppDatabase.getInstance(getApplicationContext()).userDao().updateUser(userID, passWord);
            return null;
        }

        @Override
        protected void onPostExecute(Void voids) {
            progressDialog.dismiss();
            onEditSuccess();
        }
    }
}
