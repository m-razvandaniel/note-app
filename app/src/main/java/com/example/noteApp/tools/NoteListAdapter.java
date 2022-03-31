package com.example.noteApp.tools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.noteApp.MainActivity;
import com.example.noteApp.Note;
import com.example.noteApp.NoteActivity;
import com.example.noteApp.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    private ArrayList<Note> localDataSet;

    /** 1
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public NoteListAdapter(ArrayList<Note> dataSet) {
        localDataSet = dataSet;
    }

    /**2
     * creating the view: MaterialCardView, TextView
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_object, viewGroup, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView materialCardView;
        private final TextView titleText;
        private final TextView bodyText;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            materialCardView = view.findViewById(R.id.card);
            titleText = view.findViewById(R.id.textTitle);
            bodyText = view.findViewById(R.id.bodyText);
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
            return materialCardView;
        }
        public TextView getTitleText() {
            return titleText;
        }
        public TextView getBodyText() {
            return bodyText;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Context currentContext = viewHolder.itemView.getContext();

        viewHolder.getTitleText().setText(localDataSet.get(position).noteTitle);    //setting the title text
        viewHolder.getBodyText().setText(localDataSet.get(position).noteText.split("\n", 2)[0]);    //setting the description text

        viewHolder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(currentContext, NoteActivity.class);
                intent.putExtra(MainActivity.INTENT_ID, localDataSet.get(viewHolder.getAdapterPosition()).code);
                intent.putExtra(MainActivity.INTENT_NOTE_OPERATION, "access");
                currentContext.startActivity(intent);
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