package com.example.nti.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.Visibility;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nti.R;
import com.example.nti.SessionManager;
import com.example.nti.activities.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {


    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignin;
    private CheckBox checkBox;
    private TextView register;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private SessionManager sessionManager;
    private DatabaseReference databaseReference;
    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        sessionManager = new SessionManager(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnSignin = view.findViewById(R.id.btn_signin);
        register=view.findViewById(R.id.tv_register);
        checkBox = view.findViewById(R.id.check_gender);

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnSignin.setEnabled(true);

                    //btnSignin.setBackgroundColor(R.color.colorAccent);
                }
                else{
                    btnSignin.setEnabled(false);
                    //btnSignin.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_registeration_view,new SignupFragment());
                fragmentTransaction.commit();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if(!isEmailValid(etEmail.getText().toString()))
                {
                    etEmail.setError("Invalid Email Address");
                }
                else {
                    dialog.show();
                    signInFirebase(email, password);





                }
            }
        });

    }

    private void signInFirebase(final String email, final String password) {



        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final String userId = mAuth.getCurrentUser().getUid();
                            String userName = null;

                            //databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference mostafa = ref.child("users").child(mAuth.getCurrentUser().getUid());


                            mostafa.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String userName = dataSnapshot.getValue(String.class);
                                    sessionManager.setUserName(userName);
                                    Log.println(Log.ERROR,"username", userName);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            sessionManager.createUserSession(email,password,userName);
                            dialog.dismiss();
                            Intent intent = new Intent(getContext(), HomeActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
