package com.shady191997.app1.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shady191997.app1.R;
import com.shady191997.app1.activites.HomeActivity;
import com.shady191997.app1.sessionManager.SessionManager;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginFragment extends Fragment{

    Button btnLogin;

    TextView tvRegister;

    private EditText etEmail;
    private EditText etPassword;

    private String email;
    private String password;

    ProgressDialog dialog;

    private FirebaseAuth mAuth;

    SessionManager sessionManager;

public LoginFragment(){
        // Required empty public constructor
        }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setUpPrgressDialog();
    }

    private void setUpPrgressDialog() {
        dialog=new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new SignupFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fm, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();            }
        });

        sessionManager=new SessionManager(getActivity());

            btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.show();

            email=etEmail.getText().toString().trim();
            password=etPassword.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                // Sign in success, update UI with the signed-in user's information
                               //Log.d(TAG, "signInWithEmail:success");

                                FirebaseUser user = mAuth.getCurrentUser();

                                sessionManager.createLoginSession(email,password);

                                Intent intent=new Intent(getContext(), HomeActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                                //updateUI(user);
                            } else {
                                dialog.dismiss();
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                               // updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin=view.findViewById(R.id.btn_login);
        etEmail=view.findViewById(R.id.et_email);
        etPassword=view.findViewById(R.id.et_password);

        tvRegister=view.findViewById(R.id.tv_register);


        return view;
    }


}