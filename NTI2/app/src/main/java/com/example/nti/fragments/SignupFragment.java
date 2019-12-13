package com.example.nti.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nti.R;
import com.example.nti.SessionManager;
import com.example.nti.activities.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private EditText etName;
    private EditText etPassword;
    private Button btnSignup;

    private String email;
    private String password;

    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private SessionManager sessionManager;



    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        setUpProgressDialog();


    }

    private void setUpProgressDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        etName = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnSignup = view.findViewById(R.id.btn_signup);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                email = etName.getText().toString().trim();

                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+") || email == null) {

                    etName.setError("Invalid Email Address");

                }
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                email = etName.getText().toString().trim();
                password = etPassword.getText().toString().trim();


                signUpNewUser();
            }
        });

    }

    private void signUpNewUser() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            dialog.dismiss();
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                            sessionManager.createLoginSession(email,password);

                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            intent.putExtra("email",email);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            dialog.dismiss();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "SignUp failed.",
                                    Toast.LENGTH_SHORT).show();
//                                    updateUI(null);

                        }

                        // ...
                    }
                });
    }
}
