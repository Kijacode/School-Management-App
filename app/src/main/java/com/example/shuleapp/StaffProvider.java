package com.example.shuleapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class StaffProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shuleapp.StaffProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/staff";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String STAFFNO = "staffnumber";
    static final String FIRSTNAME = "firstname";
    static final String MIDDLENAME = "middlename";
    static final String SURNAME = "surname";
    static final String GENDER = "gender";
    static final String DEPARTMENT ="department";
    static final String BIRTHDATE = "birthdate";
    static final String EMAIL = "email";
    static final String PASSWORD ="password";
    static final String REGIION = "region";
    static final String DISTRICT = "district";
    static final String WARD = "ward";
    static final String ROLE = "role";
    final String role2 = "staff";
//    static final String GRADE = "grade";

    private static HashMap<String, String> STAFFS_PROJECTION_MAP;

    static final int STAFF = 1;
    static final int STAFF_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "staffs", STAFF);
        uriMatcher.addURI(PROVIDER_NAME, "staffs/#", STAFF_ID);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "SCHOOL APP";
    static final String STAFF_TABLE_NAME = "staff";
    static final int DATABASE_VERSION = 11;
       static final String CREATE_DB_TABLE =
            " CREATE TABLE " + STAFF_TABLE_NAME +
                    " (staffnumber TEXT PRIMARY KEY , " +
                    " firstname TEXT NOT NULL, " +
                    " middlename TEXT NOT NULL," +
                    "surname TEXT NOT NULL,"+
                    "gender TEXT NOT NULL,"+
                    "department TEXT NOT NULL,"+
                    "birthdate DATE NOT NULL,"+
                    "email TEXT NOT NULL,"+
                    "password TEXT ," +
                    "region TXT NOT NULL,"+
                    "district TEXT NOT NULL,"+
                    "ward TEXT,"+
                   "role TEXT  );";



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

            db.execSQL(CREATE_DB_TABLE);




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  STAFF_TABLE_NAME);
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
        long rowID = db.insert(	STAFF_TABLE_NAME, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection,String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(STAFF_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case STAFF:
                qb.setProjectionMap(STAFFS_PROJECTION_MAP);
                break;

            case STAFF_ID:
                qb.appendWhere( STAFFNO + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = FIRSTNAME;
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
            case STAFF:
                return "vnd.android.cursor.dir/vnd.example.staff";
            /**
             * Get a particular student
             */
            case STAFF_ID:
                return "vnd.android.cursor.item/vnd.example.staff";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}