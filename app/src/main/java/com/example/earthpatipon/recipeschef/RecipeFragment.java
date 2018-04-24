package com.example.earthpatipon.recipeschef;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.Recipe;
import com.example.earthpatipon.recipeschef.utils.RecipeAdapter;
import com.example.earthpatipon.recipeschef.utils.RecipeCard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    private List<RecipeCard> cardList = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;


    public RecipeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        initializeCard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = view.findViewById(R.id.cardView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (cardList.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(new RecipeAdapter(context, cardList));
        }
        recyclerView.setLayoutManager(MyLayoutManager);
        return view;
    }

    public void initializeCard() {
        List<Recipe> recipeList = AppDatabase.getInstance(context).recipeDao().getAllRecipe();

        for (int i = 0; i < recipeList.size(); i++) {
            RecipeCard item = new RecipeCard();
            item.setCardName(recipeList.get(i).getRecipeName());
            item.setIsFav(0);
            item.setIsTurned(0);
            cardList.add(item);
        }
    }

//    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//
//        private List<RecipeCard> list;
//
//        public MyAdapter(ArrayList<RecipeCard> data) {
//            list = data;
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            // create a new view
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items, parent, false);
//            MyViewHolder holder = new MyViewHolder(view);
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(final MyViewHolder holder, int position) {
//            String cardName = list.get(position).getCardName();
//            holder.titleTextView.setText(cardName);
//            Bitmap bitmap;
//            try {
//                // TODO: use (much, much) faster image loading library like Glide
//                bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(context.getFilesDir().getPath() + File.separator + "RecipeImages", cardName + ".png")));
//                holder.coverImageView.setImageBitmap(bitmap); //
//                holder.coverImageView.setTag(cardName.hashCode());
//                holder.likeImageView.setTag(R.drawable.ic_like);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return list.size();
//        }
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView coverImageView;
//        public ImageView likeImageView;
//        public ImageView shareImageView;
//        public TextView titleTextView;
//
//        public MyViewHolder(View v) {
//            super(v);
//            titleTextView = v.findViewById(R.id.titleTextView);
//            coverImageView = v.findViewById(R.id.coverImageView);
//            likeImageView = v.findViewById(R.id.likeImageView);
//            shareImageView = v.findViewById(R.id.shareImageView);
//
//            likeImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int id = (int) likeImageView.getTag();
//                    if (id == R.drawable.ic_like) {
//                        likeImageView.setTag(R.drawable.ic_liked);
//                        likeImageView.setImageResource(R.drawable.ic_liked);
//                        Toast.makeText(getActivity(), titleTextView.getText() + " added to favourites", Toast.LENGTH_SHORT).show();
//                    } else {
//                        likeImageView.setTag(R.drawable.ic_like);
//                        likeImageView.setImageResource(R.drawable.ic_like);
//                        Toast.makeText(getActivity(), titleTextView.getText() + " removed from favourites", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//            shareImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Uri imageUri = Uri.parse(context.getFilesDir().getPath() + File.separator + "RecipeImages" + File.separator + titleTextView.getText() + ".png");
//                    Intent shareIntent = new Intent();
//                    shareIntent.setAction(Intent.ACTION_SEND);
//                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    shareIntent.setType("image/jpeg");
//                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
//                }
//            });
//        }
//    }
}
