package com.nimit.todo.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nimit.todo.R;
import com.nimit.todo.database.DatabseColumns;
import com.nimit.todo.database.QuoteProvider;
import com.nimit.todo.fragment.AddItem;
import com.nimit.todo.helper.UpdateHelper;
import com.nimit.todo.model.Todo;

public class ItemListCursorAdapter extends CursorRecyclerViewAdapter<ItemListCursorAdapter.ViewHolder> {

    static private Context context;
    private UpdateHelper updateHelper;
    private Cursor cursor;
    public ItemListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
        updateHelper = (UpdateHelper)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView headline;
        private CardView singleItem;
        TextView dueDate;
        TextView todoDescription;
        private ImageView deleteItem;
        public ViewHolder(View view) {
            super(view);
            headline = (TextView) view.findViewById(R.id.item_view);
            singleItem = (CardView) view.findViewById(R.id.single_item);
            dueDate = (TextView) view.findViewById(R.id.due_date);
            todoDescription = (TextView) view.findViewById(R.id.todo_content);
            deleteItem = (ImageView) view.findViewById(R.id.delete);
          /*  image = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(this);
           */


        }
        /*
        @Override
        public void onClick(View view) {

            /*Cursor c = view.getContext().getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
            c.moveToFirst();
            c.moveToPosition(getAdapterPosition());

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_VIDEO_URL + c.getString(c.getColumnIndex("videoId"))));
            context.startActivity(intent);

        }*/
    }
    /*private void showAlertDialog() {
        FragmentManager fm = context.
        AddItem alertDialog = AddItem.newInstance("new",null);
        alertDialog.show(fm, "fragment_alert");
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        ViewHolder vh = new ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, Cursor c) {
        viewHolder.headline.setText(c.getString(c.getColumnIndex("item")));
        viewHolder.todoDescription.setText(c.getString(c.getColumnIndex(DatabseColumns.DESCRIPTION)));
        viewHolder.dueDate.setText(c.getString(c.getColumnIndex(DatabseColumns.DUE)));
        switch (c.getInt(c.getColumnIndex(DatabseColumns.PRIORITY))){
            case 0:
                viewHolder.singleItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.priorityLow));
                break;
            case 1:
                viewHolder.singleItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.priorityMedium));
                break;
            case 2:
                viewHolder.singleItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.priorityHigh));
                break;
        }
        /*Picasso.with(context)
                .load(cursor.getString(cursor.getColumnIndex("url")))
                .into(viewHolder.image);*/
        viewHolder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                new AlertDialog.Builder(context).setTitle("Delete").setMessage("Are you sure you want to Delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });*/
                cursor = context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
                cursor.moveToPosition(viewHolder.getAdapterPosition());
                context.getContentResolver().delete(QuoteProvider.Quotes.CONTENT_URI, DatabseColumns.ID+"=?",
                        new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex(DatabseColumns.ID)))});
                cursor.close();
            }
        });
        Log.e("pos",viewHolder.getAdapterPosition()+"");

        viewHolder.singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
                cursor.moveToPosition(viewHolder.getAdapterPosition());
                updateHelper.showFragment(new Todo(cursor.getString(cursor.getColumnIndex(DatabseColumns.ITEM)),
                                                    cursor.getString(cursor.getColumnIndex(DatabseColumns.DESCRIPTION)),
                                                    cursor.getString(cursor.getColumnIndex(DatabseColumns.DUE)),
                                                    cursor.getInt(cursor.getColumnIndex(DatabseColumns.PRIORITY)),
                                                    0),cursor.getInt(cursor.getColumnIndex(DatabseColumns.ID)));
                Log.e("in Adapter", cursor.getString(cursor.getColumnIndex(DatabseColumns.ITEM)));
                cursor.close();
            }
        });
       //cursor.close();
    }


}