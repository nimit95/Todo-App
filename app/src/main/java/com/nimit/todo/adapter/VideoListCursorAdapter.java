package com.nimit.todo.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimit.todo.R;

public class VideoListCursorAdapter extends CursorRecyclerViewAdapter<VideoListCursorAdapter.ViewHolder> {

    static private Context context;

    public VideoListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView headline;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
          /*  headline = (TextView) view.findViewById(R.id.heading);
            image = (ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(this);
           */

        }

        @Override
        public void onClick(View view) {

            /*Cursor c = view.getContext().getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
            c.moveToFirst();
            c.moveToPosition(getAdapterPosition());

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.YOUTUBE_VIDEO_URL + c.getString(c.getColumnIndex("videoId"))));
            context.startActivity(intent);*/
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        ViewHolder vh = new ViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        viewHolder.headline.setText(cursor.getString(cursor.getColumnIndex("title")));
        /*Picasso.with(context)
                .load(cursor.getString(cursor.getColumnIndex("url")))
                .into(viewHolder.image);*/
    }

}