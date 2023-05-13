package com.amrdroid.assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class UpdateFragment extends Fragment {

  View v;
  EditText updateTitle;
  EditText updateContent;
  Button update_btn;
  NotesDbHelper dbHelper;
  Cursor cursor;

  ArrayList<NotesModel> arrayList;
  int id;
    public UpdateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_update, container, false);

        updateTitle = v.findViewById(R.id.updateTitle_edt);
        updateContent = v.findViewById(R.id.updateContents_edt);
        update_btn = v.findViewById(R.id.update_btn);
        dbHelper = new NotesDbHelper(getContext());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        //UpdateFragment updateFragment = new UpdateFragment();
        activity.getSupportFragmentManager().setFragmentResultListener("dataFrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                id = result.getInt("id");
                String title = result.getString("title");
                String content = result.getString("content");
                updateTitle.setText(title);
                updateContent.setText(content);
            }
        });



        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "";
                String content = "";



                if(!updateTitle.getText().toString().equals("") && !updateContent.getText().toString().equals("")) {


                    cursor = dbHelper.getNotesById(id);
                    Toast.makeText(activity, cursor.getString(1), Toast.LENGTH_LONG).show();

                    title = updateTitle.getText().toString();
                    content = updateContent.getText().toString();

                    boolean isUpdated = dbHelper.updateData(title,currentDate,content,id);

                    if(isUpdated) {
                        Toast.makeText(activity, "Updated Successfully!", Toast.LENGTH_SHORT).show();

                        AppCompatActivity activity  = (AppCompatActivity) v.getContext();
                        NotesFragment notesFragment = new NotesFragment();
                        activity.getSupportFragmentManager()
                                .beginTransaction()
                                .detach(notesFragment)
                                .attach(notesFragment)
                                .commit();

                        getActivity().onBackPressed();
                    }
                    else {
                        Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                    }
/*

        updateTitle.setText(cursor.getString(1));
        updateContent.setText(cursor.getString(3));*/



                }else {
                    Toast.makeText(getContext(), "Please enter details!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}