package com.nimit.todo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nimit.todo.adapter.ItemListCursorAdapter;
import com.nimit.todo.database.DatabseColumns;
import com.nimit.todo.database.QuoteProvider;
import com.nimit.todo.fragment.AddItem;
import com.nimit.todo.helper.UpdateHelper;
import com.nimit.todo.model.Todo;

public class MainActivity extends AppCompatActivity implements UpdateHelper, LoaderManager.LoaderCallbacks<Cursor>{
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private ItemListCursorAdapter itemListCursorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (FloatingActionButton) findViewById(R.id.add);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //recyclerView.setHasFixedSize(true);
        itemListCursorAdapter = new ItemListCursorAdapter(getApplicationContext(),
                getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI, null, null, null, null));
        recyclerView.setAdapter(itemListCursorAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddItem alertDialog = AddItem.newInstance("Some title",null);
        alertDialog.show(fm, "fragment_alert");
    }

    @Override
    public void UpdateItem(Todo todo, int index) {
        ContentValues values = new ContentValues();
        values.put(DatabseColumns.ITEM, todo.getTitle());
        values.put(DatabseColumns.DESCRIPTION,todo.getDescription());
        values.put(DatabseColumns.DUE, todo.getDate());
        values.put(DatabseColumns.PRIORITY,todo.getPriority());
        getContentResolver().insert(QuoteProvider.Quotes.CONTENT_URI,values);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getApplicationContext(), QuoteProvider.Quotes.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        itemListCursorAdapter.swapCursor(null);
    }
}
