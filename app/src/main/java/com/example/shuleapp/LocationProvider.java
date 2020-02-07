package com.example.shuleapp;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class LocationProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shuleapp.LocationProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/locations";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String ID = "_id";
    static final String COURSENAME = "coursename";
    static final String COURSECODE = "coursecode";
    static final String COURSEDEPARTMENT = "department";
    static  final String PRIORITY = "priority";
    static  final  String CREDIT = "credits";
    //    static final String GRADE = "grade";

    private static HashMap<String, String> COURSE_PROJECTION_MAP;

    static final int COURSES = 1;
    static final int COURSE_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "courses", COURSES);
        uriMatcher.addURI(PROVIDER_NAME, "course/#", COURSE_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "SCHOOL APP";
    static final String COURSES_TABLE_NAME = "courses";
    static final int DATABASE_VERSION = 8;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + COURSES_TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "coursename TEXT NOT NULL, " +
                    " coursecode TEXT NOT NULL," +
                    "department TEXT NOT NULL,"  +
                    "priority TEXT NOT NULL ,"  +
                    "credits  INTEGER NOT NULL);";

    /**
     * Helper class that actually creates and manages
     * the provider's underlying data repository.
     */

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Location location = new Location();
            System.out.println("Course database created");
            db.execSQL(location.createRegionTable);
            db.execSQL(location.createDistrictTable);
            db.execSQL(location.createWardTable);
            db.execSQL(location.insertRegions);
            db.execSQL(location.insertDistricts);
            db.execSQL(location.insertWardsGroup1);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  COURSES_TABLE_NAME);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
//        System.out.println("Course database insert value");
//        long rowID = db.insert(	COURSES_TABLE_NAME, "", values);
//
//        /**
//         * If record is added successfully
//         */
//        System.out.println("Course record is addedd");
//        if (rowID > 0) {
//            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
//            getContext().getContentResolver().notifyChange(_uri, null);
//            return _uri;
//        }
//
//        throw new SQLException("Failed to add a record into " + uri);
//    }
        return null;
    }
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Location location =new Location();
        qb.setTables("regions");


        switch (uriMatcher.match(uri)) {
            case COURSES:
                qb.setProjectionMap(COURSE_PROJECTION_MAP);
                break;

            case COURSE_ID:
                qb.appendWhere( ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = COURSECODE;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    //    @Override
//    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)){
//            case STUDENTS:
//                count = db.delete(STUDENTS_TABLE_NAME, selection, selectionArgs);
//                break;
//
//            case STUDENT_ID:
//                String id = uri.getPathSegments().get(1);
//                count = db.delete( STUDENTS_TABLE_NAME, _ID +  " = " + id +
//                                (!TextUtils.isEmpty(selection) ? "
//                        AND (" + selection + ')' : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }

//    @Override
//    public int update(Uri uri, ContentValues values,
//                      String selection, String[] selectionArgs) {
//        int count = 0;
//        switch (uriMatcher.match(uri)) {
//            case STUDENTS:
//                count = db.update(STUDENTS_TABLE_NAME, values, selection, selectionArgs);
//                break;
//
//            case STUDENT_ID:
//                count = db.update(STUDENTS_TABLE_NAME, values,
//                        _ID + " = " + uri.getPathSegments().get(1) +
//                                (!TextUtils.isEmpty(selection) ? "
//                        AND (" +selection + ')' : ""), selectionArgs);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URI " + uri );
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//        return count;
//    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case COURSES:
                return "vnd.android.cursor.dir/vnd.example.courses";
            /**
             * Get a particular student
             */
            case COURSE_ID:
                return "vnd.android.cursor.item/vnd.example.courses";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}