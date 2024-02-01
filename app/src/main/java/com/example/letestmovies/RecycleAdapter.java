package com.example.letestmovies;

import static androidx.core.content.res.TypedArrayUtils.getResourceId;

import android.content.Context;
import android.net.Uri;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    Context context;
    ArrayList<list> arr;
    click1 l;
    private GestureDetector gestureDetector;
    String chack="O";


    public RecycleAdapter(Context context, ArrayList<list> arr, click1 l) {
        this.context = context;
        this.arr = arr;
        this.l=l;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.poster,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final list array=arr.get(position);
        //holder.poster.setImageURI(Uri.parse(String.valueOf(array.getPoster())));
        // holder.poster.setImageURI(Uri.parse(array.getPoster()));
       Glide.with(context)
               .load("https://image.tmdb.org/t/p/original"+array.getPoster())// "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg"
               .into(holder.poster);

//        if (array.getPoster() != null) {
//            // Assuming array.getPoster() returns "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg"
//            String imageUrl = "https://image.tmdb.org/t/p/original" + array.getPoster(); // Replace with your base URL
//
//            Glide.with(context)
//                    .load(imageUrl)
//                    .placeholder(R.drawable.xyz) // Placeholder image while loading
//                    .error(R.drawable.error) // Image to display if there's an error loading the original image
//                    .into(holder.poster);
//        }
//
//        Glide.with(holder.itemView.getContext())
//                .load(getResourceId( "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg",holder)) // Assuming array.getPoster() returns "/qhb1qOilapbapxWQn9jtRCMwXJF.jpg"
//                .into(holder.poster);
        gestureDetector = new GestureDetector(context, new DoubleTapListener());

        holder.title.setText(array.getTitle());
     //   holder.click.setOnTouchListener(new MyTouchListener(gestureDetector));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"1 click", Toast.LENGTH_SHORT).show();
               // gestureDetector = new GestureDetector(context, new DoubleTapListener(array));
                holder.click.setOnTouchListener(new MyTouchListener(gestureDetector));
                if(chack=="Double Clicked!"){

                    chack="O";
                    l.onclick(array);
                }else{
                    Toast.makeText(context,"1 more click", Toast.LENGTH_SHORT).show();
                }
            }
       });
    }

//    private int getResourceId(String imageName,ViewHolder holder) {
//        return holder.itemView.getContext().getResources().getIdentifier(
//                imageName, "drawable", holder.itemView.getContext().getPackageName());
//    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView title;
        CardView click;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            poster=itemView.findViewById(R.id.imageView);
            title=itemView.findViewById(R.id.textView);
            click=itemView.findViewById(R.id.click);
        }

    }

    private class MyTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public MyTouchListener(GestureDetector gestureDetector) {
            this.gestureDetector = gestureDetector;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
    }


    private class DoubleTapListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            chack="Double Clicked!";

            return true;
        }
    }

//    private void showToast(String message) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//        chack=message;
//    }
 }

