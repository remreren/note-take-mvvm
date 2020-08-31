package com.bukonudakonusalim.takenotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bukonudakonusalim.takenotes.R;

import java.util.Collections;
import java.util.List;

import io.mattcarroll.hover.Content;
import io.mattcarroll.hover.HoverMenu;

public class HoverLogService extends HoverMenu {

    private Context mContext;
    private Section mSection;

    public HoverLogService(Context context) {
        mContext = context;
        ImageView view = new ImageView(mContext);
        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        view.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_hover_menu));
        mSection = new Section(
                new SectionId("1"),
                view,
                new Content() {
                    @NonNull
                    @Override
                    public View getView() {
                        return LayoutInflater.from(context).inflate(R.layout.hover_test, null);
                    }

                    @Override
                    public boolean isFullscreen() {
                        return false;
                    }

                    @Override
                    public void onShown() {

                    }

                    @Override
                    public void onHidden() {

                    }
                }
        );
    }

    @Override
    public String getId() {
        return mSection.getId().toString();
    }

    @Override
    public int getSectionCount() {
        return 1;
    }

    @Nullable
    @Override
    public Section getSection(int index) {
        return mSection;
    }

    @Nullable
    @Override
    public Section getSection(@NonNull SectionId sectionId) {
        return mSection;
    }

    @NonNull
    @Override
    public List<Section> getSections() {
        return Collections.singletonList(mSection);
    }
}
