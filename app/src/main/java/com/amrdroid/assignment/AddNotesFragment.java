package com.amrdroid.assignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNotesFragment extends Fragment {

    View v;
    EditText title_edt;
    EditText content_edt;
    Button add_btn;

    NotesDbHelper dbHelper;
    

    public AddNotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        v = inflater.inflate(R.layout.fragment_add_notes, container, false);
        title_edt = v.findViewById(R.id.title_edt);
        content_edt = v.findViewById(R.id.Contents_edt);
        add_btn = v.findViewById(R.id.add_btn);

        dbHelper = new NotesDbHelper(getContext());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "";
                String content = "";
                if(!title_edt.getText().toString().equals("") && !content_edt.getText().toString().equals("")) {

                    title = title_edt.getText().toString();
                    content = content_edt.getText().toString();

                    Boolean checkInsertData = dbHelper.insertData(title,currentDate,content);
                    if(checkInsertData == true) {
                        Toast.makeText(getContext(), "Data inserted Successfully!", Toast.LENGTH_SHORT).show();

                        AppCompatActivity activity  = (AppCompatActivity) v.getContext();
                        NotesFragment notesFragment = new NotesFragment();
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .detach(notesFragment)
                                .attach(notesFragment)
                                .commit();


                        getActivity().onBackPressed();

                    }else {
                        Toast.makeText(getContext(), "Data not inserted!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(), "Please enter details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return v;
        
    }
}