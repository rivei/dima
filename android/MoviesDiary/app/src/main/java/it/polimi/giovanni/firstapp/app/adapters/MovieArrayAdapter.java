package it.polimi.giovanni.firstapp.app.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import it.polimi.giovanni.firstapp.R;
import it.polimi.giovanni.firstapp.app.model.Movie;

/**
 * Created by giovanniquattrocchi on 26/10/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {


    public MovieArrayAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){

        Movie movie = getItem(position);

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.movie_item, null);

            convertView.setTag(new MovieViewHolder(convertView));
        }

        MovieViewHolder viewHolder = (MovieViewHolder) convertView.getTag();

        viewHolder.titleTV.setText(movie.getTitle());
        viewHolder.directorTV.setText(movie.getDirectorName());

        return convertView;
    }

    static class MovieViewHolder {

        TextView titleTV;
        TextView directorTV;

        public MovieViewHolder(View view){
            titleTV = (TextView) view.
                    findViewById(R.id.movieTitleTextView);
            directorTV = (TextView) view.
                    findViewById(R.id.movieDirectorTextView);
        }

    }

}
