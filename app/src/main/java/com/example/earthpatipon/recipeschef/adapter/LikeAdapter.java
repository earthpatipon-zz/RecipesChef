/* Group: Aoong Aoong
 * Members: Tanaporn 5888124, Kanjanaporn 5888178, Patipon 5888218
 */
package com.example.earthpatipon.recipeschef.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.RecipeActivity;
import com.example.earthpatipon.recipeschef.database.AppDatabase;
import com.example.earthpatipon.recipeschef.entity.RecipeCard;
import com.example.earthpatipon.recipeschef.entity.User;
import com.example.earthpatipon.recipeschef.entity.UserLike;

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
        holder.titleTextView.setText(cardName);

        if(likeCardList.get(position).getIsLiked() == 1) { // Liked
            holder.likeImageView.setImageResource(R.drawable.ic_liked);
            holder.likeImageView.setTag(R.drawable.ic_liked);
        } else { // Not Liked
            holder.likeImageView.setImageResource(R.drawable.ic_like);
            holder.likeImageView.setTag(R.drawable.ic_like);
        }
    }

    @Override
    public int getItemCount() {
        return likeCardList.size();
    }

    public class LikeViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public LikeViewHolder(final Context context, View v) {

            super(v);
            titleTextView = v.findViewById(R.id.titleTextView);
            coverImageView = v.findViewById(R.id.coverImageView);
            likeImageView = v.findViewById(R.id.likeImageView);
            shareImageView = v.findViewById(R.id.shareImageView);

            coverImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), RecipeActivity.class);
                    //PACK DATA
                    intent.putExtra("SENDER_KEY", "HomeFragment");
                    intent.putExtra("NAME_KEY", titleTextView.getText().toString());

                    context.getApplicationContext().startActivity(intent);
                }
            });

            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int userID = user.getUserID();
                    int recipeID = AppDatabase.getInstance(context).recipeDao().getRecipe(titleTextView.getText().toString()).getRecipeID();
                    Log.d("recipeName: ", titleTextView.getText().toString());
                    Log.d("recipeID: ", Integer.toString(recipeID));
                    RecipeCard card = null;
                    for (RecipeCard c : likeCardList) {
                        if (c.getId() == recipeID) {
                            card = c;
                            break;
                        }
                    }
                    if (likeImageView.getTag().equals(R.drawable.ic_like)) {
                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);
                        Toast.makeText(context, titleTextView.getText() + " added to favourites", Toast.LENGTH_SHORT).show();

                        // Record user like
                        UserLike userLike = new UserLike(userID, recipeID);
                        AppDatabase.getInstance(context).userLikeDao().insert(userLike);
                        if (card != null) { card.setIsLiked(1); }
                    }
                    else {
                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(context, titleTextView.getText() + " removed from favourites", Toast.LENGTH_SHORT).show();

                        // De-Record user like
                        AppDatabase.getInstance(context).userLikeDao().delete(userID, recipeID);
                        if (card != null) { card.setIsLiked(0); }
                    }
                }
            });

            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri imageUri = Uri.parse(context.getFilesDir().getPath() + File.separator + "RecipeImages" + File.separator + titleTextView.getText() + ".png");
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("image/jpeg");
                    context.startActivity(Intent.createChooser(shareIntent, context.getResources().getText(R.string.action_share)));
                }
            });
        }
    }
}



