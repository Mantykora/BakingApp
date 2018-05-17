package com.example.roza.bakingapp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roza.bakingapp.models.Recipe;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hiddenpik on 15.05.2018.
 */

public class RecipeDetailFragment extends Fragment {

    // Saved instance state keys.
    private static final String KEY_TRACK_SELECTOR_PARAMETERS = "track_selector_parameters";
    private static final String KEY_WINDOW = "window";
    private static final String KEY_POSITION = "position";
    private static final String KEY_AUTO_PLAY = "auto_play";

    private DefaultTrackSelector.Parameters trackSelectorParameters;
    private boolean startAutoPlay;
    private int startWindow;
    private long startPosition;


//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//      //updateTrackSelectorParameters();
//        //updateStartPosition();
//       // outState.putParcelable(KEY_TRACK_SELECTOR_PARAMETERS, trackSelectorParameters);
//        outState.putBoolean(KEY_AUTO_PLAY, startAutoPlay);
//        outState.putInt(KEY_WINDOW, startWindow);
//        outState.putLong(KEY_POSITION, startPosition);
//        super.onSaveInstanceState(outState);
//    }

    @BindView(R.id.player_view)
    PlayerView playerView;

    public ExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private Recipe.Steps step;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this, view);
        step = getArguments().getParcelable("step");

        Log.d("RecipeDetailFragment", step.getStepDescription());



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23 || player == null) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
                initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }


    }



    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    //ExoPlayer from https://codelabs.developers.google.com/codelabs/exoplayer-intro/#2
    public void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()), new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);


        Uri uri = Uri.parse(step.getStepVideoUrl());
        MediaSource mediaSource = buildMediaSource(uri);
        //player.prepare(mediaSource, true, false);

        player.prepare(mediaSource, true, false);


    }
//
//    private void updateTrackSelectorParameters() {
//        if (trackSelector != null) {
//            trackSelectorParameters = trackSelector.getParameters();
//        }
//    }

    private void updateStartPosition() {
        if (player != null) {
            startAutoPlay = player.getPlayWhenReady();
            startWindow = player.getCurrentWindowIndex();
            startPosition = Math.max(0, player.getContentPosition());
        }
    }
    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            //player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoPlayer"))
                .createMediaSource(uri);
    }
}
