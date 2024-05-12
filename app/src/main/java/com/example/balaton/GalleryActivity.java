package com.example.balaton;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GalleryActivity extends AppCompatActivity {

    ImageView imageView;
    TextView imageName;
    String toImageName;
    TextView number;
    String toNumber;
    Button button_next;
    Button button_prev;

    Integer counter;
    Integer currentImage;
    Integer length;
    int[] ids;
    String[] names;
    private static final String LOG_TAG = GalleryActivity.class.getName();
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});

        mAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null) {
            Log.d(LOG_TAG, "Authenticated user!");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user!");
            finish();
        }

        imageView = findViewById(R.id.imageView);
        imageName = findViewById(R.id.textView2);
        number = findViewById(R.id.textView);
        button_next = findViewById(R.id.button_next);
        button_prev = findViewById(R.id.button_prev);

        ids = new int[]{
                R.drawable.bfured,
                R.drawable.bfured2,
                R.drawable.hegyestu,
                R.drawable.tihany,
                R.drawable.tihany_levendula,
                R.drawable.revfulop,
                R.drawable.langos
        };

        names = new String[]{
                "Balatonfüred",
                "Balatonfüred naplementekor",
                "Hegyestű",
                "Tihany",
                "Tihanyi levendulák",
                "Révfülöp",
                "Lángos."
        };

        counter = 0;
        currentImage = counter + 1;
        length = ids.length;

        imageView.setImageResource(ids[0]);

        toNumber = currentImage + " / " + length;
        number.setText(toNumber);


        imageName.setText(names[0]);
//        AnimationUtils.loadAnimation(this,)

        //buttonclicks
        View.OnClickListener btnClick_next = v -> {
            //imageView.setImageResource(ids[counter]);
            //Animation imgAnimation_out = AnimationUtils.loadAnimation(this,R.anim.fadeout);
            //imageView.startAnimation(imgAnimation_out);


            counter++;
            if(counter >= length) {
                counter = 0;
                currentImage = 0;
            }
            //tryanim
            //Animation imgAnimation_in = AnimationUtils.loadAnimation(this,R.anim.fadein);
            Animation zoomin = AnimationUtils.loadAnimation(this,R.anim.zoomin);
            imageView.startAnimation(zoomin);



            imageView.setImageResource(ids[counter]);
            currentImage++;
            toNumber = currentImage + " / " + length;
            number.setText(toNumber);
            imageName.setText(names[counter]);
        };

        View.OnClickListener btnClick_prev = v -> {
            counter--;
            if(counter < 0) {
                counter = length - 1;
                currentImage = length + 1;
            }


            Animation zoomout = AnimationUtils.loadAnimation(this,R.anim.zoomout);
            imageView.startAnimation(zoomout);

            imageView.setImageResource(ids[counter]);
            currentImage--;
            toNumber = currentImage + " / " + length;
            number.setText(toNumber);
            imageName.setText(names[counter]);

        };
        
        button_next.setOnClickListener(btnClick_next);
        button_prev.setOnClickListener(btnClick_prev);




    }
}