package com.example.module_challenge.logic.data;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;


public class challenge_DBEngine {
    private challenge_data_dao challenge_data_dao;
    private static challenge_data data1;
    private static List<challenge_data> list1;

    //引擎的构造方法，创建数据库单例
    //从数据库单例中获取Dao
    public challenge_DBEngine(Context context){
        challenge_Database challengeDatabase = challenge_Database.getInstance(context);
        challenge_data_dao = challengeDatabase.getChallenge_data_dao();
    }

    //对外的数据库执行操作
    public void insertChallenge(challenge_data... data){
        data1 = new challenge_data(-1,0,-1,0);
        new InsertAsyncTask(challenge_data_dao).execute(data);
        Log.d("Ning_Room" , "InsertAsyncTask execute");
    }
    public void deleteChallenge(challenge_data... data){
        data1 = new challenge_data(-1,0,-1,0);
        new DeleteAsyncTask(challenge_data_dao).execute(data);
    }
    public void updateChallenge(challenge_data... data){
        data1 = new challenge_data(-1,0,-1,0);
        new UpdateAsyncTask(challenge_data_dao).execute(data);
    }
    public List<challenge_data> queryAllChallenge(){
        new UpdateAsyncTask(challenge_data_dao).execute();
        if(list1 != null){
            for(challenge_data data : list1){
                Log.d("Ning_Room" , "return list1 : " + data.toString());
            }
        }
        return list1;
    }
    public challenge_data queryByIdChallenge(Integer... id){
        data1 = new challenge_data(-1,0,-1,0);
        new QueryByIdAsyncTask(challenge_data_dao).execute(id);
        Log.d("Ning_Room","return queryByIdChallenge data1 : " + data1.id);
        Log.d("Ning_Room","return queryByIdChallenge id : " + id);
        return data1;
    }


    //异步操作的封装

    //异步添加挑战的操作
    static class InsertAsyncTask extends AsyncTask<challenge_data,Void,Void>{
        private challenge_data_dao dao;
        public InsertAsyncTask(challenge_data_dao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(challenge_data... data) {
            dao.insertChallenge_data(data);
            Log.d("Ning_Room","InsertAsyncTask doInBackground");
            return null;
        }
    }

    //异步删除挑战的操作
    static class DeleteAsyncTask extends AsyncTask<challenge_data ,Void ,Void>{

        private challenge_data_dao dao;
        public DeleteAsyncTask(challenge_data_dao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(challenge_data... data) {
            dao.deleteChallenge_data(data);
            return null;
        }
    }

    //异步更新挑战操作
    static class UpdateAsyncTask extends AsyncTask<challenge_data , Void , Void>{
        private challenge_data_dao dao;
        public UpdateAsyncTask(challenge_data_dao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(challenge_data... data) {
            dao.updateChallenge_data(data);
            return null;
        }
    }

    //异步查询操作
    static class QueryAllAsyncTask extends AsyncTask< Void, Void ,Void>{
        private challenge_data_dao dao;
        public  QueryAllAsyncTask(challenge_data_dao dao){
            this.dao = dao;
        }
        List<challenge_data> list = null;

        @Override
        protected Void doInBackground(Void... voids) {
            list = dao.getChallengeList();
            list1 = dao.getChallengeList();
            for(challenge_data data : list){
                Log.d("Ning_Room" , "Query : " + data.toString());
            }
            return null;
        }

        public List<challenge_data> getList(){
            return list;
        }
    }

    //异步查询操作
    static class QueryByIdAsyncTask extends AsyncTask<Integer, Void ,Void >{
        private challenge_data_dao dao;
        public QueryByIdAsyncTask(challenge_data_dao dao){
            this.dao = dao;
        }


        @Override
        protected Void doInBackground(Integer... id) {
            challenge_data data = dao.getChallengeById(id);
            if(data != null){
                data1.id = data.id;
                data1.challenge = data.challenge;
                data1.progress = data.progress;
                data1.complete = data.complete;
                Log.d("Ning_Room", "QueryById " + data1.id);
                Log.d("Ning_Room","QueryById" + data1.challenge);
                Log.d("Ning_Room","QueryById" + data1.progress);
                Log.d("Ning_Room","QueryById" + data1.complete);
                //错误：我查到了1，data1被赋值成了1，也就是说get到了1

            }else{
                Log.d("Ning_Room","QueryById " + "null");
            }

            return null;
        }
    }

}
