package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.Queries;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class QueriesAdapter extends FirebaseRecyclerAdapter<Queries,QueriesAdapter.myviewholder> {

    public QueriesAdapter(@NonNull FirebaseRecyclerOptions<Queries> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final QueriesAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Queries Queries)
    {


        holder.queriesFacultyName.setText(Queries.getFacultyName());
        holder.queriesStudentRollNumber.setText(Queries.getQueriesRollNumber());
        holder.queriesTitleAdapter.setText(Queries.getQueriesTitle());
        Glide.with(holder.img.getContext()).load(Queries.getFacultyImage())
                .placeholder(R.drawable.manimg)
                .circleCrop()
                .error(R.drawable.manimg)
                .into(holder.img);

        /*
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.delete.getContext());
                builder.setTitle("Warning");
                builder.setMessage("Are you sure want to delete Queries...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Queries").child(getRef(position).getKey())
                                .removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

         */

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public QueriesAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowqueries,parent,false);
        return new QueriesAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView queriesFacultyName, queriesStudentRollNumber, queriesTitleAdapter, queriesDelete;
        CircleImageView img;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            queriesFacultyName=itemView.findViewById(R.id.queriesFacultyName);
            queriesStudentRollNumber=itemView.findViewById(R.id.queriesStudentRollNumber);
            queriesTitleAdapter=itemView.findViewById(R.id.queriesTitleAdapter);
            img=itemView.findViewById(R.id.queriesFacultyImage);
            //delete=(ImageView)itemView.findViewById(R.id.queriesDelete);

        }
    }
}
