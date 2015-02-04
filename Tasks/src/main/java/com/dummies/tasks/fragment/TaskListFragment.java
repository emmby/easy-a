package com.dummies.tasks.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.dummies.tasks.R;
import com.dummies.tasks.activity.PreferencesActivity;
import com.dummies.tasks.adapter.TaskListAdapter;
import com.dummies.tasks.interfaces.OnEditTask;

import rx.Observable;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

public class TaskListFragment extends Fragment
{
    private static final String DATABASE_NAME = "/data/data/com.dummies.tasks/databases/data";
    private static final String DATABASE_TABLE = "tasks";

    TaskListAdapter adapter;
    RecyclerView recyclerView;
    SQLiteDatabase db;
    Subscription dbSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new TaskListAdapter();
        db = SQLiteDatabase.openDatabase(
            DATABASE_NAME, null,
            SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        dbSubscription = AppObservable.bindFragment(
            this,
            Observable.defer(
                () -> query(db,false,DATABASE_TABLE,null,null,null,null,
                        null,null,null))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
            )
            .subscribe(
                Subscribers.create(
                    cursor -> adapter.swapCursor(cursor)
                ));
    }
    
    Observable<Cursor> query( SQLiteDatabase db,
                              boolean distinct,
                              String table,
                              String[]columns,
                              String selection,
                              String[] selectionArgs,
                              String groupBy,
                              String having,
                              String orderBy,
                              String limit) 
    {
        return Observable.just(db.query(distinct, table, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit));
    }

    @Override
    public void onDestroy() {
        dbSubscription.unsubscribe();
        super.onDestroy();        
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_task_list,
                container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getActivity()));
        return v;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_insert:
                ((OnEditTask) getActivity()).editTask(0);
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(getActivity(),
                        PreferencesActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

