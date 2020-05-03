package com.example.bcs421homework2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MessageActivity extends AppCompatActivity {

    private Button mGreetingButton;
    private Button mGoodbyeButton;
    private Button mShowCityButton;
    private EditText mNameEditText;
    private EditText mCityEditText;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        //setContentView(R.layout.main);

        mGreetingButton = findViewById(R.id.buttonGreeting);
        mGoodbyeButton = findViewById(R.id.buttonGoodbye);
        mShowCityButton = findViewById(R.id.buttonShowCity);
        mNameEditText = findViewById(R.id.editTextName);
        mCityEditText = findViewById(R.id.editTextCity);
        db = FirebaseFirestore.getInstance();

        mGreetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greeting = getResources().getString(R.string.greetingToast);
                Toast.makeText(getApplicationContext(), greeting, Toast.LENGTH_SHORT).show();
            }
        });

        mGoodbyeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goodbye = getResources().getString(R.string.goodbyeToast);
                Toast.makeText(getApplicationContext(), goodbye, Toast.LENGTH_SHORT).show();
            }
        });

        mShowCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();

                db.collection("Hwk2Addresses").whereEqualTo("name", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                String city = document.getString("city");
                                mCityEditText.setText(city);
                            }
                        }

                    }
                });

            }
        });

    }
}
