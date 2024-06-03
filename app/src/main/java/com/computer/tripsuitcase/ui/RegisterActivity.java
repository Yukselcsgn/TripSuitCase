package com.computer.tripsuitcase.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.computer.tripsuitcase.R;
import com.computer.tripsuitcase.data.Users;
import com.computer.tripsuitcase.databinding.ActivityRegisterBinding;
import com.computer.tripsuitcase.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    public TextView signInLink;
    ActivityRegisterBinding binding;
    public FirebaseAuth mAuth;
    public Button register;
    public EditText firstNameEditText, lastNameEditText, usernameEditText, emailEditText, passwordEditText;
    public DatabaseReference usersDatabaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        usersDatabaseRef = FirebaseDatabase.getInstance("https://tripsuitcase-5faab-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Users");

        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId = mAuth.getCurrentUser().getUid();

                                    Users newUser = new Users(firstName, lastName, username, email,password);
                                    usersDatabaseRef.child(userId).setValue(newUser);

                                    Toast.makeText(RegisterActivity.this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Kayıt Başarısız! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        signInLink = findViewById(R.id.signInLink);

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}