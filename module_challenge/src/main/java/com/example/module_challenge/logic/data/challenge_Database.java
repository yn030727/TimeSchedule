package com.example.module_challenge.logic.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {challenge_data.class} , version = 1 , exportSchema = false)
public abstract class challenge_Database extends RoomDatabase {
    //数据库的名称
    private static final String DATABASE_NAME = "challenge_db";
    private static challenge_Database databaseInstance;
    //对外暴露Dao
    public abstract challenge_data_dao getChallenge_data_dao();

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //执行升级的相关操作
        }
    };

    public static synchronized challenge_Database getInstance(Context context){
        if(databaseInstance == null){
            //创建数据库实例
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),challenge_Database.class,DATABASE_NAME).allowMainThreadQueries().build();
        }
        Room.databaseBuilder(context.getApplicationContext(),challenge_Database.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build();

        return databaseInstance;
    }
}
