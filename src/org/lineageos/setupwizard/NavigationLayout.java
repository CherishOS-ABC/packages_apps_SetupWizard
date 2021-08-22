/*
 * Copyright (C) 2021 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.setupwizard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import android.util.Log;

import org.lineageos.setupwizard.R;

public class NavigationLayout extends RelativeLayout
        implements View.OnClickListener {
    /*
     * An interface to listen to events of the navigation bar,
     * namely when the user clicks on the back or next button.
     */
    public interface NavigationBarListener {
        void onNavigateBack();
        void onNavigateNext();
        void onSkip();
    }

    private NavigationBarListener mListener;

    private Button mNextButton;
    private Button mSkipButton;

    public NavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.navigation_layout, this);
        mNextButton = (Button) findViewById(R.id.navbar_next);
        mSkipButton = (Button) findViewById(R.id.navbar_skip);
    }

    public void enableSkipButton() {
        mSkipButton.setVisibility(View.VISIBLE);
    }

    public void disableSkipButton() {
        mSkipButton.setVisibility(View.GONE);
    }

    public Button getSkipButton() {
        return mSkipButton;
    }

    public Button getNextButton() {
        return mNextButton;
    }

    public void setNavigationBarListener(NavigationBarListener listener) {
        this.mListener = listener;
        if (this.mListener != null) {
            getSkipButton().setOnClickListener(this);
            getNextButton().setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            if (view == getNextButton()) {
                mListener.onNavigateNext();
            } else if (view == getSkipButton()) {
                mListener.onSkip();
            }
        }
    }
}
