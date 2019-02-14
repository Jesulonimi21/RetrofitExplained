package com.jesulonimi.user.retrofitexplained;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_view);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call=jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textView.setText(response.code());
                    return;
                }
                List<Post> postList=response.body();
                for(Post post:postList){
                    String content="";
                    content+="ID : "+post.getId()+"\n"+"userId: "+post.getUserId()+"\n"+"title: "+post.getTitle()+"\n"+
                            "body: "+post.getText()+ "\n\n";
                    textView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
