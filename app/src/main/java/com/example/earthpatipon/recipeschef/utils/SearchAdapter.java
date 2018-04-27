package com.example.earthpatipon.recipeschef.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.earthpatipon.recipeschef.R;
import com.example.earthpatipon.recipeschef.RecipeActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable{

    private Context context;
    private List<RecipeCard> recipeList;
    private List<RecipeCard> recipeListFiltered;


    public SearchAdapter(Context context, List<RecipeCard> list) {

        this.context = context;
        this.recipeList = list;
        this.recipeListFiltered = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_search, parent, false);
        SearchViewHolder holder = new SearchViewHolder(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {

        String cardName = recipeList.get(position).getCardName();
        File file = new File(context.getFilesDir().getPath() + File.separator + "RecipeImages",cardName + ".png");
        Uri imageUri = Uri.fromFile(file);
        Glide.with(context).load(imageUri).into(holder.thumbnailView);

        holder.titleTextView.setText(cardName);
    }

    @Override
    public int getItemCount() {
        return recipeListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence != null ? charSequence.toString() : "";
                List<RecipeCard> filteredList = new ArrayList<>();

                    for (RecipeCard row : recipeList) {
                        // TODO: implement search logic here
                        if (row.getCardName().toLowerCase().contains(charString.toLowerCase())) {
                            //Log.d("cardName",row.getCardName());
                            filteredList.add(row);
                        }
                    }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                filterResults.count = filteredList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                recipeListFiltered = (ArrayList<RecipeCard>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView thumbnailView;
        public RelativeLayout semiCardView;

        public SearchViewHolder(final Context context, View v) {
            super(v);
            titleTextView = v.findViewById(R.id.titleTextView);
            thumbnailView = v.findViewById(R.id.thumbnailView);
            semiCardView = v.findViewById(R.id.semiCardView);

            semiCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), RecipeActivity.class);
                    //PACK DATA
                    intent.putExtra("SENDER_KEY", "HomeFragment");
                    intent.putExtra("NAME_KEY", titleTextView.getText().toString());
                    //START ACTIVITY
                    context.getApplicationContext().startActivity(intent);
                    //context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            });
        }


    }
}
