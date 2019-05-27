package com.example.creation;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    TextView textView2;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3;
    Button button2;
    private List<QuestionModel> questionList = new ArrayList<QuestionModel>();


 //  private ArrayList<QuestionModel> questionList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        textView2=(TextView) findViewById(R.id.textView);
        radioGroup=(RadioGroup) findViewById(R.id.rg);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        button2=(Button) findViewById(R.id.button2);

        readQ();
        textView2.setText(questionList.get(0).getQuestion());
        rb1.setText(questionList.get(0).getOption1());
        rb2.setText(questionList.get(0).getOption2());
        rb3.setText(questionList.get(0).getOption3());
    }

    private void readQ() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference qRef = database.getReference("Sheet1");

        // Read from the database
        qRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    QuestionModel question = child.getValue(QuestionModel.class);
                    questionList.add(question);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              //  Log.w(TAG, "Failed to read value.", error.toException());
                Log.w("Load question.", databaseError.toException());//ye add kiya and question model mai default constructor bnaya ok wait dekhta hun

            }


        });


    }

}
