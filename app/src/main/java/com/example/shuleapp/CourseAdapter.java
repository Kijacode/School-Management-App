package com.example.shuleapp;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public  class CourseAdapter extends CursorAdapter{

    public CourseAdapter(Context context, Cursor c, int flags) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tv1 = (TextView) view.findViewById(R.id.coursetitle);
        TextView tv2 = (TextView) view.findViewById(R.id.courseCode);
        String coursename = cursor.getString(cursor.getColumnIndex(CourseProvider.COURSENAME));
        String coursecode = cursor.getString(cursor.getColumnIndex(CourseProvider.COURSECODE));
        tv1.setText(coursename);
        tv2.setText(coursecode);
        System.out.println("OUT put are "+coursecode+coursename);
    }
}



