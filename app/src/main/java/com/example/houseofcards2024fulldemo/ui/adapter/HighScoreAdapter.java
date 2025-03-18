package com.example.houseofcards2024fulldemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.houseofcards2024fulldemo.R;
import com.example.houseofcards2024fulldemo.db.HighScoreEntity;

import java.util.List;

public class HighScoreAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<HighScoreEntity> scores;

    public HighScoreAdapter(Context initialContext, List<HighScoreEntity> initialScores) {
        if (initialContext == null) {
            throw new NullPointerException("Initial context can't be null.");
        }

        if (initialScores == null) {
            throw new NullPointerException("Initial scores can't be null.");
        }

        inflater = LayoutInflater.from(initialContext);
        scores = initialScores;
    }

    @Override
    public int getCount() {
        return scores.size();
    }

    @Override
    public HighScoreEntity getItem(int i) {
        return scores.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_high_score, viewGroup, false);
        }

        TextView tvIndex = view.findViewById(R.id.tv_index);
        TextView tvScore = view.findViewById(R.id.tv_score);

        Integer score = getItem(i).getScore();

        tvIndex.setText(String.valueOf(i + 1) + ".");
        tvScore.setText(String.valueOf(score));

        return view;
    }
}
