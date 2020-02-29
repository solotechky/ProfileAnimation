package com.tutorial.profileanim;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout mainLayout;
    private TextView profileText;
    private ImageView dp;
    private LinearLayout detailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        mainLayout = findViewById(R.id.mainLayout);
        profileText = findViewById(R.id.profileText);
        dp = findViewById(R.id.dp);
        detailLayout = findViewById(R.id.detailLayout);

        //for the zoom effect for image
        dp.setScaleX(1.4f);
        dp.setScaleY(1.4f);

        //once click profile text,need to animate
        //profile text click listener
        profileText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.beginDelayedTransition(mainLayout);

                //use Constraintset to here
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(mainLayout);

                //remove top constraint of detailLayout
                constraintSet.clear(R.id.detailLayout, ConstraintSet.TOP);
                //constraint bottom of detail layout to bottom of parent
                constraintSet.connect(R.id.detailLayout, ConstraintSet.BOTTOM, R.id.mainLayout, ConstraintSet.BOTTOM);

                //set scale value of image to normal
                dp.animate().scaleX(1f).scaleY(1f).start();

                constraintSet.applyTo(mainLayout);
            }
        });

    }
}
