package com.amrdroid.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCompleteItemData extends AppCompatActivity {
    TextView notesTitle;
    TextView notesContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complete_item_data);
        notesTitle = findViewById(R.id.NotesTitle);
        notesContent = findViewById(R.id.NotesContent);




        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        //Toast.makeText(this, title + " " + content, Toast.LENGTH_LONG).show();
        if(!title.equals("") && !content.equals("")) {
            notesTitle.setText(title);
            notesContent.setText(content);
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }
}