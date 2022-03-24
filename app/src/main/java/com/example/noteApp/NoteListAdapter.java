package com.example.noteApp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    //private String[] localDataSet;
    private ArrayList<Note> localDataSet;

    //1
    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public NoteListAdapter(ArrayList<Note> dataSet) {
        localDataSet = dataSet;
    }

    //2 crearea unui card
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_object, viewGroup, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView noteObject;
        private final TextView textView;
        //private int code;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            noteObject = view.findViewById(R.id.card);
            textView = view.findViewById(R.id.textTitle);

            /*noteObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int viewId = v.getId();
                    Intent intent = new Intent(view.getContext(), NoteActivity.class);
                    //ntent.putExtra(MainActivity.INTENT_NOTE_OPERATION, "access");
                    intent.putExtra(MainActivity.INTENT_ID, viewId);
                    view.getContext().startActivity(intent);
                }
            });*/
        }

        public MaterialCardView getMaterialCardView() {
            return noteObject;
        }
        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        //viewHolder.getMaterialCardView();
        //setText(localDataSet[position]);

        viewHolder.getTextView().setText(localDataSet.get(position).noteTitle);
        viewHolder.noteObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.itemView.getContext(), NoteActivity.class);
                intent.putExtra(MainActivity.INTENT_ID, localDataSet.get(viewHolder.getAdapterPosition()).code);
                intent.putExtra(MainActivity.INTENT_NOTE_OPERATION, "access");
                viewHolder.itemView.getContext().startActivity(intent);
            }
        });
        //viewHolder.getMaterialCardView().setId(localDataSet.get(position).code);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}