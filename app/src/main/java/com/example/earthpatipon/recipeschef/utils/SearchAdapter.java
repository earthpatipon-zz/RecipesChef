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
import android.widget.TextView;
import android.widget.Toast;

import com.example.earthpatipon.recipeschef.R;

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
        holder.titleTextView.setText(cardName);
        Bitmap bitmap;
        try {
            // TODO: use (much, much) faster image loading library like Glide
            bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(context.getFilesDir().getPath() + File.separator + "RecipeImages", cardName + ".png")));
            holder.thumbnailView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence != null ? charSequence.toString() : "";
                if (charString.isEmpty()) {
                    recipeListFiltered = recipeList;
                } else {
                    List<RecipeCard> filteredList = new ArrayList<>();
                    for (RecipeCard row : recipeList) {
                        // TODO: implement search logic here
                        if (row.getCardName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    recipeListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = recipeListFiltered;
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

        public SearchViewHolder(final Context context, View v) {
            super(v);
            titleTextView = v.findViewById(R.id.titleTextView);
            thumbnailView = v.findViewById(R.id.thumbnailView);
        }
    }
}
