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

public class StudentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.shuleapp.StudentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/students";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String REGNO = "regno";
    static final String FIRSTNAME = "firstname";
    static final String MIDDLENAME = "middlename";
    static final String SURNAME = "surname";
    static final String GENDER = "gender";
    static final String COURSEDEG ="course";
    static final String BIRTHDATE = "birthdate";
    static final String EMAIL = "email";
    static final String PASSWORD ="password";
    static final String REGIION = "region";
    static final String DISTRICT = "district";
    static final String WARD = "ward";
    static final String ROLE = "role";
//    static final String GRADE = "grade";

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int STUDENTS = 1;
    static final int STUDENT_ID = 2;
    static final int REGIONS =2;
    static  final int DISTRICTS = 3;
    static  final int WARDS = 4;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "students", STUDENTS);
        uriMatcher.addURI(PROVIDER_NAME, "students/#", STUDENT_ID);
        uriMatcher.addURI(PROVIDER_NAME,"regions",REGIONS);
        uriMatcher.addURI(PROVIDER_NAME,"districts",DISTRICTS);
    }

    /**
     * Database specific constant declarations
     */

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "SCHOOL APP";
    static final String STUDENTS_TABLE_NAME = "students";
    static final int DATABASE_VERSION = 11;
    static final String COURSES_TABLE_NAME2 = "courses";
    static final String STAFF_TABLE_NAME = "staff";
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + STUDENTS_TABLE_NAME +
                    " (regno TEXT PRIMARY KEY , " +
                    " firstname TEXT NOT NULL, " +
                    " middlename TEXT NOT NULL," +
                    "surname TEXT NOT NULL,"+
                    "gender TEXT NOT NULL,"+
                    "course TEXT NOT NULL,"+
                    "birthdate DATE NOT NULL,"+
                    "email TEXT NOT NULL,"+
                    "password TEXT NOT NULL," +
                    "region TXT NOT NULL,"+
                    "district TEXT NOT NULL,"+
                    "ward TEXT NOT NULL," +
                    "role TEXT );";


    static final String CREATE_DB_TABLE2 =
            " CREATE TABLE " + COURSES_TABLE_NAME2 +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "coursename TEXT NOT NULL, " +
                    " coursecode TEXT NOT NULL," +
                    "department TEXT NOT NULL,"  +
                    "priority TEXT NOT NULL ,"  +
                    "credits  INTEGER NOT NULL);";




    static final String CREATE_DB_TABLE3 =
            " CREATE TABLE " + STAFF_TABLE_NAME +
                    " (staffnumber TEXT PRIMARY KEY, " +
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
                    "ward TEXT NOT NULL,"+
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
Location b = new Location();
            db.execSQL(CREATE_DB_TABLE);
          db.execSQL(CREATE_DB_TABLE2);
          db.execSQL(CREATE_DB_TABLE3);
          db.execSQL(b.createRegionTable);
          db.execSQL(b.insertRegions);
          db.execSQL(b.createDistrictTable);
          db.execSQL(b.insertDistricts);
          db.execSQL(b.createWardTable);
//          db.execSQL(b.selectRegions);
//          db.execSQL(b.insertWardsGroup1);



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +  STUDENTS_TABLE_NAME);
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
        long rowID = db.insert(	STUDENTS_TABLE_NAME, "", values);

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
        qb.setTables(STUDENTS_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case STUDENTS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case STUDENT_ID:
                qb.appendWhere( REGNO + "=" + uri.getPathSegments().get(1));
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
            case STUDENTS:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}