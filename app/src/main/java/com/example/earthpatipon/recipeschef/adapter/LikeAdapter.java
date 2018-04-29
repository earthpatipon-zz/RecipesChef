/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.RecipeActivity;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;
import com.example.earthpatipon.recipeschef.entity.User;

import java.io.File;
import java.util.List;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeViewHolder> {

    private Context context;
    private List<RecipeCard> likeCardList;
    private User user;

    public LikeAdapter(Context context, List<RecipeCard> list, User user) {

        this.context = context;
        this.likeCardList = list;
        this.user = user;
    }

    @Override
    public LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_like, parent, false);
        return new LikeViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(LikeViewHolder holder, int position) {

        String cardName = likeCardList.get(position).getCardName();
        File file = new File(context.getFilesDir().getPath() + File.separator + "RecipeImages",cardName + ".png");
        Uri imageUri = Uri.fromFile(file);

        Glide.with(context).load(imageUri).into(holder.coverImageView);
        holder.coverImageView.setTag(cardName);
    }

    @Override
    public int getItemCount() {
        return likeCardList.size();
    }

    public class LikeViewHolder extends RecyclerView.ViewHolder {

        public String titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public LikeViewHolder(final Context context, View v) {

            super(v);
            coverImageView = v.findViewById(R.id.coverImageView);
            likeImageView = v.findViewById(R.id.likeImageView);
            shareImageView = v.findViewById(R.id.shareImageView);

            coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), RecipeActivity.class);
                    //PACK DATA
                    intent.putExtra("SENDER_KEY", "HomeFragment");
                    intent.putExtra("NAME_KEY", titleTextView);

                    intent.putExtra("NAME_KEY", coverImageView.getTag().toString());

                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
    }
}



