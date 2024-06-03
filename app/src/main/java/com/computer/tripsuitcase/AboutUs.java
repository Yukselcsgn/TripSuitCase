package com.computer.tripsuitcase;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


//FRONT END SORUNLARI
public class AboutUs extends AppCompatActivity {

    ImageView imgLinkedin,imgInstagram,imgTwitter;
    TextView txtEmail, txtWebsiteUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        imgLinkedin = findViewById(R.id.imgLinkedin);
        imgInstagram = findViewById(R.id.imgInstagram);
        imgTwitter = findViewById(R.id.imgTwitter);
        txtEmail = findViewById(R.id.txtEmail);
        txtWebsiteUrl = findViewById(R.id.txtWebsiteUrl);

        imgLinkedin.setOnClickListener(view -> navigateToUrl("https://www.linkedin.com/in/y%C3%BCksel-co%C5%9Fgun/"));

        txtEmail.setOnClickListener(view -> {
            try {
                Intent intent = new Intent( Intent.ACTION_VIEW,Uri.parse("mailto: "+"yukselcosgun1@gmail.com") );
                intent.putExtra(Intent.EXTRA_SUBJECT,"From Trip Suit Case");
                startActivity(intent);
            }catch (ActivityNotFoundException e ){
                System.out.println( e );
            }
        });

        txtWebsiteUrl.setOnClickListener(view -> navigateToUrl("https://github.com/Yukselcsgn"));

        imgInstagram.setOnClickListener(view -> navigateToUrl("https://instagram.com/yuksel_csgn?igshid=YmMyMTA2M2Y="));

        imgTwitter.setOnClickListener(view -> navigateToUrl("https://twitter.com/birbuyukpanda"));
    }

    private void navigateToUrl(String url){
        Intent intent = new Intent(  );
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}