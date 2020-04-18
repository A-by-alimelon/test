package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PlaceSearchActivity extends AppCompatActivity {


    private int planetData;
    private ImageView search_icon;
    private EditText editSearch;
    private RecyclerView recyclerView;
    private ArrayList<Place> searchList;
    private ArrayList<Place> placeArrayList;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);

        search_icon = findViewById(R.id.search_btn);
        recyclerView = findViewById(R.id.recycler_view);
        placeArrayList = new ArrayList<>();
        Intent intent = getIntent();
        planetData = intent.getIntExtra("planetKey",0);
        Log.d("testt",""+planetData);

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editSearch.getVisibility() == view.INVISIBLE){

                    Animation animation = new AlphaAnimation(0,1);
                    animation.setDuration(1000);
                    editSearch.setVisibility(view.VISIBLE);
                    editSearch.setAnimation(animation);

                }
                else{
                    editSearch.setVisibility(view.INVISIBLE);
                }
            }
        });


        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTEyMTFfMjk1/MDAxNTc2MDQxMjM5MzIz.uCh8-YpBGq0mbMWeyrgTqmuGSWq4MuemWOWhJymSamkg.Ha0eDUL_9UHaDCYggy17P39HKNVVYWFdMZfRpDbJOtQg.JPEG.crypearl/" +
                "P20191205_123216007_CD3DED03-371F-4826-9EC9-C5329F44CFF7.JPG","유정식당",02,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTA0MDVfMjIg/MDAxNTU0NDI5MjIyNjcx.PBkBlb-N7sfxOSqivS3zB" +
                "6ZCgoDFYuUBspc-ViQSBdkg.HxvEgsNdbFM0yGYyqfBEJBqjzc0ZpqNKxAwOw3aVT9wg.JPEG.wntjr8/20190405_105659.jpg","엔게더",02,"카페"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAyMDAyMDZfNDkg/MDAxNTgwOTU4NDIyODkz.FU-8NHfdkwTwls-hCeBhysOdHObkb8mu6QajnC662Zog.Fs9UGzyL" +
                "IuT-oHZGcOFw7Ns6jSWQXWb8QogbpPQ7RpAg.JPEG.dreammer91/KakaoTalk_20200206_110454896.jpg","금돼지식당",02,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAyMDAxMjFfMjAx/MDAxNTc5NjA3Mjc0Nzc2.RNcYu_yLRaW51fuNdkKgU-Qm2w0zYYrQp6C" +
                "aAE8LIMIg.Uf_ykW95ncqPag9f-N3SE4-sR9CGlYJJZVqszc1W1Agg.JPEG.oneandonly___/IMG_5583.jpg","고수포차",01,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTA3MjlfMTgy/MDAxNTY0NDA5NjQ0MzIx.l4y7GvGH4oaN0Gg1EvEiyWlAdSHtZb31Zp" +
                "8cIB17PW4g.wHCHkBDA-gLchekNuXKL156s2bIDIKZLZMJ4SNi8IcUg.JPEG.gamjaaaaa/IMG_0951.JPG","용곱창",01,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxNzEwMDZfMjA5/MDAxNTA3MjY1NzExMTgy.m6-SYW53BAHB7copRqHdeL3nY_sasdQduHv" +
                "xS-FMm9wg.M3DgVDG8L4qsdY0JXAxiD7fUmQHXelUgf3aU3q4nNUIg.JPEG.hyeonpeach/20171006_132628.jpg","애월해",01,"식당"));

        //세븐틴 (20200418)
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20170123_107/1485150489262F5S3J_JPEG/186073547828504_0.jpeg","하남돼지",01,"식당"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTEwMDFfMjEz/MDAxNTY5ODYzNzM1ODI1.tYJJjzwLV5TXUyJcAHG_uvHNEEKx52g3hMLghbbsvrAg.JNvyW3NzuCX3tQRhz7HSISFKfkTlu-PI5dZlmlNEy-Yg.JPEG.bella890717/20190505_183330709.jpg",
                "잔비워",01,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20190718_194/1563459993984vo9bu_JPEG/ZXzAq4w5wseZJXpUmsQgP4SJ.jpg","탐앤탐스 신사역점",01,"카페"));
        placeArrayList.add(new Place("http://blogfiles.naver.net/MjAxOTA4MjNfMTA5/MDAxNTY2NTUwOTU3NTc1.g6-6cXgcFHqNDCAMyG0OeY0HhqqWc4mqj1uzwQ9yPLog.827jYa_UMG2BNWLmWcF3CZvQ1DhSYbqnu" +
                "4i9MSJtthQg.JPEG.sh880830/IMG_9975.jpg","설빙 청담공원점",01,"카페"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net//20161211_19/1481440107571nBo5G_JPEG/IMG_5441.JPG","쏭스클럽",01,"식당"));
        placeArrayList.add(new Place("https://search.pstatic.net/common/?src=http%3A%2F%2Fldb.phinf.naver.net%2F20180823_197%2F1535025751634Ffozo_JPEG%2FZ2umkAGS13BMaB2N5-qkTUD4.jpg","펄세이플라워",01,"기타"));
        placeArrayList.add(new Place("http://imgnews.naver.net/image/5359/2018/06/12/0000256010_001_20180612163646305.jpg","플레디스 엔터테인먼트",01,"기타"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20181005_118/1538721395489joq5I_JPEG/u7Q7iMqE6rLPaDzCi_4K3Dt-.jpg","스윗솔키친",01,"카페"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20150901_181/1441045700876iITNN_JPEG/13491910_0.jpg","영랑호",01,"기타"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20150901_95/1441078771849uenzR_JPEG/SUBMIT_1391920867381_34016121.jpg","베이힐풀앤빌라",01,"기타"));

        //방탄 (20200418)
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20151104_101/144662517972580gvO_JPEG/167054574958075_0.jpg","갯벌의 진주",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20190303_145/1551589663886nrNxj_JPEG/5zuNPeGNczvcsGIlpMyWtncM.jpg","열봉부엌",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20180313_288/1520919133666tHLVw_JPEG/v8_zyHLRYktmgUFBhjPo9oH3.jpg","그집",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20170104_54/1483517605097DJop7_JPEG/177179546622622_0.jpeg","NY BnB",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20170829_175/150397030460530SfP_JPEG/mREqxnsW05Mck4kI7yr6IqUG.jpg","듀자미",02,"카페"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20190903_26/1567475209775u1pg0_JPEG/P32u69x0gnwSmHbAHfCEDPGb.jpg","멜로워",02,"카페"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20150831_62/1440992706193nR2Iy_JPEG/11880458_2.jpg","목란",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20200107_145/1578360528515PIDOW_JPEG/43TuJK2XBYZ4_E7xIwg3ew59.jpg","밸뷰",02,"주점"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20200404_53/1585946257851xAvBq_JPEG/LVcAX1OA68zSo6E3NiZ5-0Bn.jpg","삼보청국장",02,"식당"));
        placeArrayList.add(new Place("http://ldb.phinf.naver.net/20180712_216/1531407502930DBQzY_JPEG/AXu0GIGhqugsCyVIxo395EJC.jpg","오쓰세이로무시",02,"식당"));

        searchList = new ArrayList<>();

        for (int i = 0; i < placeArrayList.size(); i++){
            if(placeArrayList.get(i).getPlanetId() == planetData){
                searchList.add(placeArrayList.get(i));
            }
        }

        //searchList.addAll(placeArrayList);



        editSearch = findViewById(R.id.edit_search);


//        LayoutInflater layoutInflater = LayoutInflater.from(this);
////        View header = layoutInflater.inflate(R.layout.place_list_header,null,false);
////        myAdapter = new MyAdapter(PlaceSearchActivity.this,searchList);
////        listView.addHeaderView(header);
////        listView.setAdapter(myAdapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(searchList);
        recyclerView.setAdapter(myAdapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });



    }


    public void search(String charText){
        searchList.clear();
        if(charText.length() == 0){
            searchList.addAll(placeArrayList);
        }else{
            for(int i = 0; i <placeArrayList.size(); i++){

                if(placeArrayList.get(i).getName().contains(charText)){
                    searchList.add(placeArrayList.get(i));
                }
            }
        }
        myAdapter.notifyDataSetChanged();

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        private ArrayList<Place> arrayList;

        public MyAdapter(ArrayList<Place> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.place_list_tem_view,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.textView.setText(arrayList.get(position).getName());
            RequestOptions centerOption = new RequestOptions().centerCrop();
            Glide.with(PlaceSearchActivity.this).load(arrayList.get(position).getImage()).apply(centerOption)
                   .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.place_text_view);
                imageView = itemView.findViewById(R.id.place_image_view);
            }
        }

    }
//    class MyAdapter extends BaseAdapter{
//        private ViewHolder viewHolder;
//        private LayoutInflater layoutInflater;
//        private ArrayList<Place> itemList;
//
//        public MyAdapter(Context context,ArrayList<Place> itemList) {
//            this.itemList = itemList;
//            this.layoutInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return itemList.size();
//        }
//
//        @Override
//        public Place getItem(int i) {
//            return itemList.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View convertView, ViewGroup viewGroup) {
//            View view = convertView;
//            if(view == null){
//                viewHolder = new ViewHolder();
//                view = layoutInflater.inflate(R.layout.place_list_tem_view,null);
//                viewHolder.textView = view.findViewById(R.id.place_text_view);
//                viewHolder.imageView = view.findViewById(R.id.place_image_view);
//                view.setTag(viewHolder);
//            }else{
//                viewHolder = (ViewHolder)view.getTag();
//            }
//
//            viewHolder.textView.setText(getItem(i).getName());
//            RequestOptions centerOption = new RequestOptions().centerCrop();
//            Glide.with(PlaceSearchActivity.this).load(getItem(i).getImage()).apply(centerOption)
//                   .into(viewHolder.imageView);
//            return view;
//        }
//
//        class ViewHolder{
//            public TextView textView = null;
//            public ImageView imageView = null;
//        }
//
//    }


}
