package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hungntph19080_assignment_mob403.Activity.Main.MainActivity;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Favorite.FavoriteFragment;
import com.example.hungntph19080_assignment_mob403.Adapter.CommentAdapter;
import com.example.hungntph19080_assignment_mob403.Model.Comment;
import com.example.hungntph19080_assignment_mob403.R;
import com.example.hungntph19080_assignment_mob403.databinding.FragmentShowComicBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShowComicFragment extends Fragment {
    FragmentShowComicBinding binding;
    ImageView coverImage;
    TextView tvName, tvDescription, tvAuthor, tvPublishYear;
    EditText txtContentComment;
    Button btnRead, btnFavorite, btnSendComment;
    DetailsComicFragment detailsComicFragment;
    String id;
    ArrayList list;
    RecyclerView rcvListComment;
    String url_comment = "https://f21oe9-8080.csb.app/comments";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShowComicBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        init();
        VolleyGetComment();

        Bundle bundle = this.getArguments();
        id = String.valueOf(bundle.get("Id"));
        String name = bundle.getString("Name");
        String description = bundle.getString("Description");
        String author = bundle.getString("Author");
        String publishYear = bundle.getString("PublishYear");
        String image = bundle.getString("CoverImage");
        List listImage = bundle.getParcelableArrayList("ListImage");
        detailsComicFragment.setArguments(bundle);

        Glide.with(getContext())
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background).into(coverImage);

        Log.d("okeee", "onCreateView: " + listImage);

        tvName.setText("Tên truyện: " + name);
        tvDescription.setText("Mô tả: " + description);
        tvAuthor.setText("Tác giả: " + author);
        tvPublishYear.setText("Năm phát hành: " + publishYear);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), listImage.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("ListImage", (ArrayList<? extends Parcelable>) listImage);
                detailsComicFragment.setArguments(bundle1);
                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_show_comic, detailsComicFragment)
                        .commit();

            }
        });

        btnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtContentComment.getText().toString().equals(null) || txtContentComment.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Vui lòng nhập bình luận",Toast.LENGTH_SHORT).show();
                }else {
                    VolleyPostComment(1,1);
//                    VolleyGetComment();
                    Toast.makeText(getContext(), "Bạn đã thêm bình luận",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void init() {
        coverImage = binding.imgCoverImage;
        tvName = binding.tvNameComic;
        tvDescription = binding.tvDescription;
        tvAuthor = binding.tvAuthor;
        tvPublishYear = binding.tvPublishYear;
        txtContentComment = binding.txtContentComment;
        btnSendComment = binding.btnSendComment;
        btnRead = binding.btnReadComic;
        btnFavorite = binding.btnFavorite;
        list = new ArrayList<>();
        rcvListComment = binding.recycleViewListComment;
        detailsComicFragment = new DetailsComicFragment();
    }

    void VolleyGetComment() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url_comment, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<Comment>>() {
                }.getType();

                ArrayList<Comment> listComments = gson.fromJson(response, listType);

                for (Comment comment : listComments) {
                    if(comment.getIdComic()==Integer.parseInt(id)){
                        list.add(comment);
                        Log.d("checkComment", "onResponse: "+comment);
                    }
                }

                LinearLayoutManager linearLayoutManager
                        = new LinearLayoutManager(getContext());
                rcvListComment.setLayoutManager(linearLayoutManager);

                CommentAdapter adapter = new CommentAdapter(getContext(), list);
                rcvListComment.setAdapter(adapter);

//                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//                rcvListComment.addItemDecoration(itemDecoration);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("zzzzzz", "onErrorResponse: " + error);

            }
        });
        queue.add(request);
    }

    void VolleyPostComment(int id_Comic, int id_User) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            JSONObject object = new JSONObject();
            object.put("idUser", id_User);
            object.put("idComic", id);
            object.put("nameUserComment", "admin");
            object.put("contentComment", txtContentComment.getText().toString());
            object.put("dateComment", format);

            final String reqBody = object.toString();

            StringRequest req = new StringRequest(Request.Method.POST, url_comment, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
//                    ed_content.setText(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//                            ed_content.setText(error.getMessage());
                        }
                    }) {

                // Phần gửi đi
                @Override //Khai báo kiểu dữ liệu gửi lên API
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override // Chuyển nội dung thành mã byte
                public byte[] getBody() throws AuthFailureError {
                    if (reqBody == null) {
                        return null;
                    } else {
                        return reqBody.getBytes(StandardCharsets.UTF_8);
                    }
//                    return super.getBody();
                }

                @Override // Bắt lỗi kết nối internet
                protected VolleyError parseNetworkError(VolleyError volleyError) {
                    String response = "";
                    if (volleyError != null) {
//                        response = String.valueOf(volleyError.getMessage());
                    }
                    return super.parseNetworkError(volleyError);
                }
            };
            requestQueue.add(req);
        } catch (JSONException e) {
            e.printStackTrace(); // Ghi ra log cấu trúc lỗi
        }
    }

}