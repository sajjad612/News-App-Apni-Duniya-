package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class categoryMain extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {
    //88fd32f1a1aa4d9bb60f0764c777c3ea

    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModel> categoryRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRvCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModelArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();

    }

    private void getCategories(){
        categoryRVModelArrayList.add(new CategoryRVModel("All",R.drawable.all));
        categoryRVModelArrayList.add(new CategoryRVModel("Science",R.drawable.science));
        categoryRVModelArrayList.add(new CategoryRVModel("Sports",R.drawable.sport));
        categoryRVModelArrayList.add(new CategoryRVModel("General",R.drawable.general));
        categoryRVModelArrayList.add(new CategoryRVModel("Business",R.drawable.business));
        categoryRVModelArrayList.add(new CategoryRVModel("Technology",R.drawable.technology));
        categoryRVModelArrayList.add(new CategoryRVModel("Entertainment",R.drawable.entertainment));
        categoryRVModelArrayList.add(new CategoryRVModel("Health",R.drawable.health));
    }

    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apikey=88fd32f1a1aa4d9bb60f0764c777c3ea";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomain=stackoverflow.com&sortBy=publishedAt&language=en&apikey=88fd32f1a1aa4d9bb60f0764c777c3ea";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;
        if(category.equals("All")){
            call = retrofitApi.getAllNews(url);
        }else{
            call = retrofitApi.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for (int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }
                newsRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(categoryMain.this, "Failed to get news", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModelArrayList.get(position).getCategory();
        getNews(category);

    }
}
