package com.example.modelclassandadpter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private  final static  String name="student_db";
    private  final static int version=1;

    private  final static String TABLE_STUDENT="Student";
    private  final static String ID="id";
    private  static final String NAME="name";
    private  static final String AGE="age";
    private  static final String GRADE="grade";

    private  static final  String CREATE_STUDENT_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_STUDENT
            +"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
            +NAME+" TEXT DEFAULT '',"
            +AGE+" TEXT DEFAULT '',"
            +GRADE+" TEXT DEFAULT '' "
            +")";



    public Database(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL(CREATE_STUDENT_TABLE);//creat table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//update  student database
    public  boolean insertStudent(student_model studentM){
    boolean isInsert = false;
        SQLiteDatabase db=this.getWritableDatabase();//write databse
        ContentValues contentValues=new ContentValues();//execute query
        contentValues.put(NAME,studentM.name);
        contentValues.put(AGE,studentM.age);
        contentValues.put(GRADE,studentM.grade);
         isInsert=db.insert(TABLE_STUDENT,null,contentValues)>0;//0 is use to conver long value to boolean value/true or fales
         return isInsert;
    }
//retreving database values

    public List<student_model> getStudents(){

        List<student_model> studentslist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();//read databse
        String query="SELECT * FROM "+TABLE_STUDENT;
        Cursor cursor=db.rawQuery(query,null);//run query
        if(cursor.getCount()>0){
            cursor.moveToFirst();//get first value set
            do{
                student_model student=new student_model();
                student.name=cursor.getString(cursor.getColumnIndex(NAME));
                student.age=cursor.getInt(cursor.getColumnIndex(AGE));
                student.grade=cursor.getInt(cursor.getColumnIndex(GRADE));
                studentslist.add(student);//add to studentlist arraylist
            }while (cursor.moveToNext());//continue geting value set

        }
      return  studentslist;

    }



}
