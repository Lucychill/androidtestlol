package com.example.twoactivites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public int someScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            someScore = savedInstanceState.getInt("HIGH_SCORE", 0);
            this.doTextView();
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Displays the second activity
     * @param aView
     */
    public void displaySecondActivity(View aView)
    {
        Intent myIntent = new Intent(this, SecondActivity.class); // Constructs an object of the intent class - passes this activity as first argument and the second activity as second argument

        // adds a key-value pair to the intent
        myIntent.putExtra("HELLO_MESSAGE", "First activity says hello!");

        startActivity(myIntent);

    }

    /**
     * Changes the score
     * @param aView
     */
    public void incrementScore(View aView)
    {
        someScore++; // someScore = someScore + 1;
        this.doTextView();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle aState)
    {
        super.onSaveInstanceState(aState);
        aState.putInt("HIGH_SCORE", someScore);
    }

    public void doTextView()
    {
        TextView myTextView = (TextView) findViewById(R.id.textView3);
        myTextView.setText(String.format("%s", someScore));
    }
}