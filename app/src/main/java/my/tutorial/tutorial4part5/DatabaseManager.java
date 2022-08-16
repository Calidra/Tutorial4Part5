package my.tutorial.tutorial4part5;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseManager {
    public static final String DB_NAME = "studentsdatabase";
    public static final String DB_TABLE = "StudentInfo";
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_FIRSTNAME = "FirstName";
    public static final String COLUMN_LASTNAME = "LastName";
    public static final String COLUMN_YEAROFBIRTH = "YearOfBirth";
    public static final String COLUMN_GENDER = "Gender";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " ( " + COLUMN_ID + " INTEGER, " + COLUMN_FIRSTNAME + " TEXT, "
            + COLUMN_LASTNAME + " TEXT, " + COLUMN_YEAROFBIRTH + " TEXT, " + COLUMN_GENDER + " TEXT);";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DatabaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DatabaseManager openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public boolean addRow(Integer ID, String firstname, String lastname, String yearofbirth, String gender) {
        synchronized(this.db) {

            ContentValues newStudent = new ContentValues();
            newStudent.put(COLUMN_ID, ID);
            newStudent.put(COLUMN_FIRSTNAME, firstname);
            newStudent.put(COLUMN_LASTNAME, lastname);
            newStudent.put(COLUMN_YEAROFBIRTH, yearofbirth);
            newStudent.put(COLUMN_GENDER, gender);

            try {
                db.insertOrThrow(DB_TABLE, null, newStudent);
            } catch (Exception e) {
                Log.e("Error in inserting rows", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }
    }

    public ArrayList<StudentInfo> retrieveRows() { //query the database and return records as a text

        ArrayList<StudentInfo> studentReturn = new ArrayList<StudentInfo>();
        String[] columns = new String[] {COLUMN_ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_YEAROFBIRTH, COLUMN_GENDER};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            StudentInfo studentRows = new StudentInfo();
            studentRows.studentID = String.valueOf(cursor.getInt(0));
            studentRows.firstName = cursor.getString(1);
            studentRows.lastName = cursor.getString(2);
            studentRows.yearOfBirth = cursor.getString(3);
            studentRows.gender = cursor.getString(4);
            studentReturn.add(studentRows);
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return studentReturn;
    }

    public void clearRecords()
    {
        db = helper.getWritableDatabase();
        db.delete(DB_TABLE, null, null);
    }

    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper (Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Products table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }
}
