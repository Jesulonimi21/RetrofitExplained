package com.jesulonimi.user.retrofitexplained;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
TextView textView;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text_view);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
       //  getComments();
        //getPosts();
        createPost();
    }

    private void getPosts(){
        Map<String,String> parameters=new HashMap<>();
        parameters.put("userId","1");
        parameters.put("_sort","id");
        parameters.put("_order","desc");

        Call<List<Post>> call=jsonPlaceHolderApi.getPosts(parameters);

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

    private void getComments(){
        Call<List<Comments>> call=jsonPlaceHolderApi.getComments("posts/3/comments");

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(!response.isSuccessful()){
                    textView.setText("the response code is "+response.code());
                    return;
                }

                List<Comments> comments=response.body();
                for(Comments comment:comments){

                    String content="id : "+comment.getId() +"\n"+ "postId : "+comment.getPostId()+"\n"+
                            "email : "+comment.getEmail()+ "\n" +"name : "+comment.getName()+"\n"+"body : "+comment.getText()+"\n\n";
                            textView.append(content);

                }


            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    public void createPost(){
        Post post=new Post(1009,"new title","new text");
Map<String,String> field=new HashMap<>();
field.put("userId","4");
field.put("title","newest title");
        Call<Post> call=jsonPlaceHolderApi.createPost(field);
        call.enqueue(
                new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            textView.setText("response code "+response.code() );
                                    return;
                        }

                        Post postResponse=response.body();
                        String content="";
                        content+="Code : "+response.code()+"\n";
                        content+="ID : "+postResponse.getId()+"\n"+"userId: "+postResponse.getUserId()+"\n"+"title: "+postResponse.getTitle()+"\n"+
                                "body: "+postResponse.getText()+ "\n\n";
                        textView.append(content);
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                    textView.setText(t.getMessage());
                    }
                }
        );
    }
}
