/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.Adapter.ProfileAdapter;
import com.example.earthpatipon.recipeschef.MainActivity;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    private EditText userName;
    private EditText newPassword;
    private EditText reenterPassword;
    private Button confirmButton;
    private String updateUsername;
    private String updatePassword;
    private String updateReenterPassword;
    private Boolean valid;
    private ProgressDialog progressDialog;
    private User user;
    private ProfileAdapter profileAdapter;


    public EditProfileFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        MainActivity activity = (MainActivity) getActivity();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change fragment to edit profile
                userName = getView().findViewById(R.id.edit_username);
                newPassword = getView().findViewById(R.id.edit_newpassword);
                reenterPassword = getView().findViewById(R.id.edit_reenter_newpassword);
                confirmButton = getView().findViewById(R.id.confirm_button);

                updateUsername = userName.getText().toString();
                updatePassword = newPassword.getText().toString();
                updateReenterPassword = reenterPassword.getText().toString();

                validate();
            }
        });

        if (!validate()) {
            onUpdateFailed();
        }
        else {
            onUpdateSuccess();
        }

        return view;
    }

    private boolean validate() {
        valid = true;

        if (updateUsername.isEmpty() || updateUsername.length() < 3) {
            userName.setError("at least 3 characters");
            valid = false;
        } else {
            userName.setError(null);
        }

        if (updatePassword.isEmpty() || updatePassword.length() < 4 || updatePassword.length() > 10) {
            newPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            newPassword.setError(null);
        }

        if (updateReenterPassword.isEmpty() || updateReenterPassword.length() < 4 || updateReenterPassword.length() > 10 || !(updateReenterPassword.equals(updatePassword))) {
            reenterPassword.setError("Password Do not match");
            valid = false;
        } else {
            reenterPassword.setError(null);
        }

        new insertAsyncTask(this.updateUsername, this.updatePassword).execute();

        return valid;
    }

    private void onUpdateFailed() {

        Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_LONG).show();
        confirmButton.setEnabled(true);
    }

    private void onUpdateSuccess() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getActivity().getApplicationContext()).userDao().updateUser(user.getUserID(), updateUsername, updatePassword);
            }
        }).start();

        confirmButton.setEnabled(true);

        Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_LONG).show();

//        EditProfileFragment editProfileFragment = new EditProfileFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.profile_fragment, ProfileFragment, ProfileFragment.getTag());
    }

    private class insertAsyncTask extends AsyncTask<String, Void, Boolean> {

        private boolean exist;
        private String userName;
        private String passWord;

        insertAsyncTask(String username, String password) {
            this.userName = username;
            this.passWord = password;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            User temp = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao().findByName(userName);
            if(temp == null)
                exist = false;
            else
                exist = true;
            return exist;
        }

        @Override
        protected void onPostExecute(Boolean exist) {
            progressDialog.dismiss();
            if(!exist){
                onUpdateSuccess();
            }
            else{
                Toast.makeText(getActivity().getBaseContext(), "Username already exist", Toast.LENGTH_LONG).show();
                //can set some interval
                confirmButton.setEnabled(true);
            }
        }
    }
}

//package com.example.earthpatipon.recipeschef.Fragment;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.earthpatipon.recipeschef.MainActivity;
//import com.example.earthpatipon.recipeschef.R;
//import com.example.earthpatipon.recipeschef.database.AppDatabase;
//import com.example.earthpatipon.recipeschef.entity.User;
//
//public class EditProfileFragment extends Fragment {
//
//    EditText userName;
//    EditText newPassword;
//    EditText reenterPassword;
//    Button confirmButton;
//    String updateUsername;
//    String updatePassword;
//    String updateReenterPassword;
//    Boolean valid;
//    private ProgressDialog progressDialog;
//    User user;
//
//    public EditProfileFragment() { }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
//        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
//        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        MainActivity activity = (MainActivity) getActivity();
//
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Change fragment to edit profile
//                userName = getView().findViewById(R.id.edit_username);
//                newPassword = getView().findViewById(R.id.edit_newpassword);
//                reenterPassword = getView().findViewById(R.id.edit_reenter_newpassword);
//                confirmButton = getView().findViewById(R.id.confirm_button);
//
//                updateUsername = userName.getText().toString();
//                updatePassword = newPassword.getText().toString();
//                updateReenterPassword = reenterPassword.getText().toString();
//
//                validate();
//            }
//        });
//
//        if (!validate()) {
//            onUpdateFailed();
//        }
//        else {
//            onUpdateSuccess();
//        }
//
//        return view;
//    }
//
//    private boolean validate() {
//        valid = true;
//
//        if (updateUsername.isEmpty() || updateUsername.length() < 3) {
//            userName.setError("at least 3 characters");
//            valid = false;
//        } else {
//            userName.setError(null);
//        }
//
//        if (updatePassword.isEmpty() || updatePassword.length() < 4 || updatePassword.length() > 10) {
//            newPassword.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            newPassword.setError(null);
//        }
//
//        if (updateReenterPassword.isEmpty() || updateReenterPassword.length() < 4 || updateReenterPassword.length() > 10 || !(updateReenterPassword.equals(updatePassword))) {
//            reenterPassword.setError("Password Do not match");
//            valid = false;
//        } else {
//            reenterPassword.setError(null);
//        }
//
//        new insertAsyncTask(this.updateUsername, this.updatePassword).execute();
//
//        return valid;
//    }
//
//    private void onUpdateFailed() {
//
//        Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_LONG).show();
//        confirmButton.setEnabled(true);
//    }
//
//    private void onUpdateSuccess() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                AppDatabase.getInstance(getActivity().getApplicationContext()).userDao().updateUser(user.getUserID(), updateUsername, updatePassword);
//            }
//        }).start();
//
//        confirmButton.setEnabled(true);
//
//        Toast.makeText(getActivity(), "Update Successfully", Toast.LENGTH_LONG).show();
//
//        EditProfileFragment editProfileFragment = new EditProfileFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.profile_fragment, ProfileFragment, ProfileFragment.getTag());
//    }
//
//    private class insertAsyncTask extends AsyncTask<String, Void, Boolean> {
//
//        private boolean exist;
//        private String userName;
//        private String passWord;
//
//        insertAsyncTask(String username, String password) {
//            this.userName = username;
//            this.passWord = password;
//        }
//
//        @Override
//        protected Boolean doInBackground(String... params) {
//            User temp = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao().findByName(userName);
//            if(temp == null)
//                exist = false;
//            else
//                exist = true;
//            return exist;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean exist) {
//            progressDialog.dismiss();
//            if(!exist){
//                onUpdateSuccess();
//            }
//            else{
//                Toast.makeText(getActivity().getBaseContext(), "Username already exist", Toast.LENGTH_LONG).show();
//                //can set some interval
//                confirmButton.setEnabled(true);
//            }
//        }
//    }
//}
