package com.amrdroid.assignment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class NotesFragment extends Fragment {

    View v;
    RecyclerView notes_rv;
    ArrayList<NotesModel> arrayList;
    NotesAdapter notesAdapter;
    FloatingActionButton addItem_btn;

   // ArrayList<String> title,date,content;
    NotesDbHelper dbHelper;

    public NotesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_notes, container, false);
        notes_rv = v.findViewById(R.id.notes_rv);
        addItem_btn =  v.findViewById(R.id.addItem_btn);





 /*       title = new ArrayList<>();
        date = new ArrayList<>();
        content = new ArrayList<>();*/

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

       // notesAdapter = new NotesAdapter(getContext(),title,date,content);


        notesAdapter = new NotesAdapter(getContext(),arrayList);
        notesAdapter.notifyDataSetChanged();
        notesAdapter.updateAdapter(arrayList);
        notes_rv.setAdapter(notesAdapter);
        notes_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //displayData();

        addNotesItem();



        //notesAdapter.updateAdapter(arrayList);


        return v;
    }

    private void displayData() {
        Cursor cursor = dbHelper.getData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No entry exists!", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()) {
              /*  title.add(cursor.getString(1));
                date.add(cursor.getString(2));
                content.add(cursor.getString(3));*/
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());

        dbHelper = new NotesDbHelper(getContext());
        arrayList = dbHelper.getNotesData();


/*

        arrayList.add(new NotesModel("Television",currentDate,"Television sets transmit signals over which we can listen to and view audio and visual content. People use television to communicate important messages, advertisements, entertainment and more."));
        arrayList.add(new NotesModel("Internet",currentDate,"Many people consider the internet to be the most popular and powerful communication technology. It allows people from around the world to interact through written messages, as well as audio and video messages."));
        arrayList.add(new NotesModel("Computers",currentDate,"Computers operate through a rapid pulsing of electrical currents. Their core foundation of computers is binary code, which determines whether there is an electrical current."));
        arrayList.add(new NotesModel("Circuitry",currentDate,"A collection of electrical components that perform a particular function is a circuit. An example of an electrical circuit is a computer processor, which is a small component that translates electrical signals into computer code."));
        arrayList.add(new NotesModel("Artificial intelligence",currentDate,"Artificial intelligence is a computer system designed to make decisions and perform actions autonomously. There are varying degrees of artificial intelligence sophistication in many of our daily tasks."));
        arrayList.add(new NotesModel("Software",currentDate,"Software includes the programs a computer uses to function properly. Most software aims to provide entertainment to users or make tasks more efficient."));
        arrayList.add(new NotesModel("Solar panels",currentDate,"Solar panels use energy from the sun's rays to generate power. People use these panels to power things such as buildings, homes, outdoor lighting systems, water heating systems and more."));
        arrayList.add(new NotesModel("Wind turbines",currentDate,"Wind turbines use propellers to generate wind energy. They are typically tall pillars located in open plains or within the ocean where winds are strongest and generate the most energy."));
        arrayList.add(new NotesModel("Batteries",currentDate,"Batteries store energy for later consumption, and people use these to power other forms of technology such as a television remote. They range in size—from small batteries in items such as watches to larger batteries."));
*/

    }
    public void addNotesItem() {

        addItem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity  = (AppCompatActivity) v.getContext();
                AddNotesFragment addNotesFragment = new AddNotesFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rl,addNotesFragment).addToBackStack(null).commit();


            }
        });

    }

   /* @Override
    public void onRvMenuItem(int position, MenuItem menuItem, ArrayList list) {
        switch (menuItem.getItemId()) {
            case R.id.edit_item:
                Toast.makeText(getContext(), "edit Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete_item:
                title.remove(position);
                notesAdapter.notifyItemRemoved(position);
                break;
        }
    }*/
}