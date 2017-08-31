/*
 *  Copyright 2015 Eros Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.eros.soundtrack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eros.soundtrack.R;
import com.eros.soundtrack.helper.PlayerContent;
import com.eros.soundtrack.interfaces.Parameters;

/**
 * Created by eroschen on 2017/8/31.
 */

public class MediaPlayerFragment extends BaseFragment {
    private RelativeLayout rlFull, rlMini;
    private ImageView ivCover;
    private TextView tvArtist,tvTitle;
    private PlayerContent playerContent = PlayerContent.getInstance();
    private String Mode = playerContent.getMode();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_mediaplayer, container, false);
        rlFull = (RelativeLayout)view.findViewById(R.id.rl_full_mode);
        rlMini = (RelativeLayout)view.findViewById(R.id.rl_mini_mode);
        ivCover = (ImageView) view.findViewById(R.id.iv_cover);
        tvArtist = (TextView) view.findViewById(R.id.tv_artist);
        tvTitle =  (TextView) view.findViewById(R.id.tv_title);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setView();

    }

    private void setView() {
        if(Mode.equalsIgnoreCase(Parameters.Full)){

        } else {
            rlMini.setVisibility(View.VISIBLE);
            rlFull.setVisibility(View.GONE);
            tvArtist.setText(playerContent.getTrack().getArtistName());
            tvTitle.setText(playerContent.getTrack().getTitle());
            Glide.with(mAct)
                    .load(playerContent.getCover())
                    .centerCrop()
                    .placeholder(R.color.colorAccent)
                    .error(R.color.white50Transparent)
                    .into(ivCover);

        }
    }

}
