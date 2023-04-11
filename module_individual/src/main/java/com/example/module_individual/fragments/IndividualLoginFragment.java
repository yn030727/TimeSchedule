package com.example.module_individual.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_individual.IndividualActivity;
import com.example.module_individual.R;
import com.example.module_individual.adapter.IndividualLoginRecyclerAdapter;
import com.example.module_individual.ui.CircleView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

//个人界面模块
//1.定义变量
@Route(path = "/individual/IndividualLoginFragment")
public class IndividualLoginFragment extends Fragment {

    //1.定义变量
    private String TAG = "LoginFragment";
    private Uri imageUri;
    private static final int TAKE_PHOTO = 1;
    CircleImageView profit;
    ConstraintLayout individual_head_background;
    Typeface typeface;
    Dialog dialog;
    View inflate;

    public static IndividualLoginFragment getInstance(Bundle bundle) {
        IndividualLoginFragment individualLoginFragment = new IndividualLoginFragment();
        individualLoginFragment.setArguments(bundle);
        return individualLoginFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_individual_login, container, false);

        Log.d("FUFU:ILoginFragment", "IndividualLoginFragment");

        TextView login_users = view.findViewById(R.id.individual_login_user);
        //这里还差一个头像
        //暂时注销，使用测试账户
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/main_font_shoujin.ttf");
        login_users.setTypeface(typeface);
        login_users.setText(getArguments().getString("user"));
        login_users.setText("测试用户1");
        individual_head_background = (ConstraintLayout)view.findViewById(R.id.individual_head_layout);
        individual_head_background.getBackground().setAlpha(125);

//        LoginInformation loginInformation = new LoginInformation();
//        loginInformation.setUser(getArguments().getString("user"));
//        loginInformation.setAccount(getArguments().getString("account"));
//        loginInformation.isLogin = getArguments().getBoolean("isLogin");
//        EventBus.getDefault().postSticky(loginInformation);
//        EventBus.getDefault().postSticky(new EventLoginInformation(getArguments().getBoolean("isLogin"),getArguments().getString("account"),getArguments().getString("user") ));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.individual_login_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        IndividualLoginRecyclerAdapter adapter1 = new IndividualLoginRecyclerAdapter();
        recyclerView.setAdapter(adapter1);
//        MyListItemDecoration decoration = new MyListItemDecoration();
//        recyclerView.addItemDecoration(decoration);
        
        //更换头像的点击事件
        profit = view.findViewById(R.id.individual_login_profit);
        profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: profit clicked");
                show_dialog();
            }
        });
        
        return view;
    }

    private void show_dialog(){
        dialog = new Dialog(getContext(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate =LayoutInflater.from(getContext()).inflate(R.layout.individual_pop, null);
        //获取控件
        Button btn_photograph = inflate.findViewById(R.id.individual_pop_photograph);//取消
        Button btn_album = inflate.findViewById(R.id.individual_pop_album);//支付宝支付
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 0;//设置Dialog距离底部的距离
        //宽度填充当前布局文件宽度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框

        btn_photograph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btn_photograph");
                //使用outputImage来储存摄像头拍下的图片
                File outputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将File对象转换成Uri对象
                if(Build.VERSION.SDK_INT >= 24) {
                    Log.d(TAG, "onClick: getUriForFile");
                    imageUri = FileProvider.getUriForFile(getActivity(), "com.example.module_individual.fileProvider" , outputImage);
                    Log.d(TAG, "onClick: getUriForFile2");
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Log.d(TAG, "启动相机程序");
                //指定图片输出地址
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                Log.d(TAG, "startActivityForResult");
                startActivityForResult(intent, TAKE_PHOTO);
            }

        });

        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btn_album");
            }
        }); 

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        Log.d(TAG, "resultCode = " + resultCode);
        Log.d(TAG, "requestCode = " + requestCode);
        switch (1) {
            case TAKE_PHOTO:
                Log.d(TAG, "TAKE_PHOTO == 1");
                if (resultCode == RESULT_OK){
                    Log.d(TAG, "onActivityResult: requestCode");
                    try {
                        Log.d(TAG, "requestCode == RESULT_OK");
                        //将拍摄的照片显示出来（把拍摄的jpg照片解析成Bitmap对象，然后设置到profit中显示出来）
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        Log.d(TAG, "requestCode 172172172");
                        profit.setImageBitmap(bitmap);
                        Log.d(TAG, "requestCode 174174174");
                    } catch (FileNotFoundException e) {
                        Log.d(TAG, "onActivityResult: Exception");
                        e.printStackTrace();
                    }
                }else{
                    Log.d(TAG, "onActivityResult: notRequestCode");
                }
                break;
            default:
                break;
        }
    }
}

