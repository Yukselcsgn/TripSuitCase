package com.computer.tripsuitcase;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.computer.tripsuitcase.data.Users;
import com.computer.tripsuitcase.ui.RegisterActivity;
import com.computer.tripsuitcase.ui.login.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

//@RunWith(RobolectricTestRunner.class)
//@Config(sdk = 28)
public class RegisterActivityTest {

    @Mock
    private FirebaseAuth mockAuth;
    @Mock
    private DatabaseReference mockDatabaseRef;
    @Mock
    private EditText mockFirstNameEditText;
    @Mock
    private EditText mockLastNameEditText;
    @Mock
    private EditText mockUsernameEditText;
    @Mock
    private EditText mockEmailEditText;
    @Mock
    private EditText mockPasswordEditText;
    @Mock
    private Button mockRegisterButton;
    @Mock
    private TextView mockSignInLink;

    private RegisterActivity registerActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        registerActivity = spy(new RegisterActivity());
        doReturn(mockAuth).when(registerActivity).getAuthInstance();
        doReturn(mockDatabaseRef).when(registerActivity).getDatabaseReference();
        doReturn(mockFirstNameEditText).when(registerActivity).findViewById(R.id.firstName);
        doReturn(mockLastNameEditText).when(registerActivity).findViewById(R.id.lastName);
        doReturn(mockUsernameEditText).when(registerActivity).findViewById(R.id.username);
        doReturn(mockEmailEditText).when(registerActivity).findViewById(R.id.email);
        doReturn(mockPasswordEditText).when(registerActivity).findViewById(R.id.password);
        doReturn(mockRegisterButton).when(registerActivity).findViewById(R.id.register);
        doReturn(mockSignInLink).when(registerActivity).findViewById(R.id.signInLink);
    }

    @Test
    public void testRegisterButtonClick() {
        // Kullanıcı girişi taklit edelim
        String email = "test@example.com";
        String password = "password";
        when(mockEmailEditText.getText().toString()).thenReturn(email);
        when(mockPasswordEditText.getText().toString()).thenReturn(password);

        // Başarılı kayıt işlemini taklit edelim
        when(mockAuth.createUserWithEmailAndPassword(email, password))
                .thenReturn(mock(Task.class));
        when(mockAuth.getCurrentUser()).thenReturn(mock(FirebaseAuth.class));

        // Buton tıklamasını taklit edelim
        View.OnClickListener clickListener = mock(View.OnClickListener.class);
        doAnswer(invocation -> {
            clickListener.onClick(mockRegisterButton);
            return null;
        }).when(mockRegisterButton).setOnClickListener(any());

        // finish() yönteminin çağrıldığını doğrulayalım
        registerActivity.onCreate(mock(Bundle.class));
        verify(registerActivity).finish();
    }
}
