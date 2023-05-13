package com.amrdroid.assignment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
   // ArrayList title, date, content;
    ArrayList<NotesModel> arrayList;
    int id;
    NotesRVItem notesRVItem;

    public NotesAdapter(Context context, ArrayList<NotesModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /*    public NotesAdapter(Context context, ArrayList title, ArrayList date, ArrayList content) {
        this.context = context;
        this.title = title;
        this.date = date;
        this.content = content;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NotesModel model = arrayList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDate = sdf.format(new Date());
       // id = arrayList.get(position).id;




        holder.title_txt.setText(arrayList.get(position).title);
        holder.date_txt.setText(currentDate);
        holder.content_txt.setText(arrayList.get(position).content);



     /*   holder.title_txt.setText(arrayList.get(position).title);
        holder.date_txt.setText(arrayList.get(position).date);
        holder.content_txt.setText(arrayList.get(position).content);*/
/*        holder.title_txt.setText(String.valueOf(title.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.content_txt.setText(String.valueOf(content.get(position)));

*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowCompleteItemData.class);
              /*  intent.putExtra("title",holder.title_txt.getText());
                intent.putExtra("content",holder.content_txt.getText());*/
                intent.putExtra("title",arrayList.get(position).title);
                intent.putExtra("content",arrayList.get(position).content);
                v.getContext().startActivity(intent);
            }
        });

         Bundle myBundle = new Bundle();

        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",arrayList.get(position).id);
                bundle.putString("title",arrayList.get(position).title);
                bundle.putString("content",arrayList.get(position).content);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                UpdateFragment updateFragment = new UpdateFragment();
                activity.getSupportFragmentManager().setFragmentResult("dataFrom1",bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rl,updateFragment).addToBackStack(null).commit();

            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NotesDbHelper dbHelper = new NotesDbHelper(context);

                                if(dbHelper.deleteOrder(model.getId()) > 0) {
                                    Toast.makeText(context, "Deleted.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Error.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                notifyDataSetChanged();


                return false;
            }
        });





    }

    @Override
    public int getItemCount() {
       // return arrayList.size();
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView title_txt;
        TextView date_txt;
        TextView content_txt;
        ImageView editItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt = itemView.findViewById(R.id.title_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            content_txt = itemView.findViewById(R.id.content_txt);
            editItem = itemView.findViewById(R.id.editItem_img);

           // moreItem.setOnClickListener(this);
        }

  /*      @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.getMenuInflater().inflate(R.menu.notesrv_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }*/

   /*     @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if(id == R.id.edit_item) {
               // Toast.makeText(context, "Edit ", Toast.LENGTH_SHORT).show();

                AppCompatActivity activity  = (AppCompatActivity) context;
                UpdateFragment updateFragment = new UpdateFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rl,updateFragment).addToBackStack(null).commit();



            }else {
                Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
            }

            return false;
        }*/

     /*   @Override
        public void onClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
            popupMenu.getMenuInflater().inflate(R.menu.notesrv_menu,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();


        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            notesRVItem.onRvMenuItem(position,item,content);
            return false;
        }*/
    }

    public void updateAdapter(ArrayList<NotesModel> mDataList) {
        this.arrayList = mDataList;
        notifyDataSetChanged();
    }
}
