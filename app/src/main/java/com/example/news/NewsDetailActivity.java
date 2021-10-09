package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.http.Url;

public class NewsDetailActivity extends AppCompatActivity {
    String url;
    ProgressBar loadingNewsPB;
//    String title,desc,content,imageURL;
//    private TextView titleTV,subDescTV,contentTV;
//    private ImageView newsIV;
//    private Button readNewsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        loadingNewsPB = findViewById(R.id.idPBLoadingNews);
        loadingNewsPB.setVisibility(View.VISIBLE);
//        title = getIntent().getStringExtra("title");
//        desc = getIntent().getStringExtra("desc");
//        content = getIntent().getStringExtra("content");
//        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
//        titleTV = findViewById(R.id.idTVTitle);
//        subDescTV = findViewById(R.id.idTVSubDesc);
//        contentTV = findViewById(R.id.idTVContent);
//        newsIV = findViewById(R.id.idIVNews);
//        readNewsBtn = findViewById(R.id.idBtnReadNews);
//        titleTV.setText(title);
//        subDescTV.setText(desc);
//        contentTV.setText(content);
        WebView webview = findViewById(R.id.WVContent);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewController());
        loadingNewsPB.setVisibility(View.GONE);
//        Picasso.get().load(imageURL).into(newsIV);
//        readNewsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });

    }
}