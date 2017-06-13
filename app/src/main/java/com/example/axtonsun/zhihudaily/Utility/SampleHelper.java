package com.example.axtonsun.zhihudaily.Utility;

import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;

import com.example.axtonsun.zhihudaily.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

/**
 * Created by Axton on 2017/6/10.
 */
public class SampleHelper{

    private Activity activity;
    private static int theme = R.style.AppThemeDark;

    private SampleHelper(Activity activity) {
        this.activity = activity;
    }

    public static SampleHelper with(Activity activity){
        return new SampleHelper(activity);
    }

    public SampleHelper init(){
        activity.setTheme(theme);
        return this;
    }

    public void loadAbout() {
        final FrameLayout flHolder = (FrameLayout) activity.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("孙国璋")
                .setSubTitle("2015212009")
                .setLinksColumnsCount(4)
                .setBrief("Java实验上机大作业")
                .addGitHubLink("axtonsun")
                .addInstagramLink("axtonsun")
                .addEmailLink("781024534@qq.com")
                .addWebsiteLink("site")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("vansuita.jr@gmail.com")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }

}
