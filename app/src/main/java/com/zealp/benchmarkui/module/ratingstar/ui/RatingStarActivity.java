package com.zealp.benchmarkui.module.ratingstar.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zealp.benchmark_ui.widget.loading.ChrysanthemumLoadingDialog;
import com.zealp.benchmark_ui.widget.loading.LoadingDialog;
import com.zealp.benchmark_ui.widget.ratingstar.BUI_RatingStar;
import com.zealp.benchmarkui.R;

public class RatingStarActivity extends AppCompatActivity {

    BUI_RatingStar mRatingStar;
    TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_star);

        initRatingStar();
    }

    /**
     * 初始化加载页面
     */
    private void initRatingStar() {
        mRatingStar = findViewById(R.id.BenchmarkUI_RatingStar_RS);
        mResultText = findViewById(R.id.BenchmarkUI_RatingStar_TV);
        mRatingStar.setmOnRatingStarTouchListener(new BUI_RatingStar.OnRatingStarTouchListener() {
            @Override
            public void getRating(float ratingNum) {
                System.out.println("ratingNum = " + ratingNum);
                switch ((int) ratingNum) {
                    case 0:
                        mResultText.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        mResultText.setVisibility(View.VISIBLE);
                        mResultText.setText("差");
                        break;

                    case 2:
                        mResultText.setVisibility(View.VISIBLE);
                        mResultText.setText("一般");
                        break;

                    case 3:
                        mResultText.setVisibility(View.VISIBLE);
                        mResultText.setText("满意");
                        break;

                    case 4:
                        mResultText.setVisibility(View.VISIBLE);
                        mResultText.setText("很满意");
                        break;

                    case 5:
                        mResultText.setVisibility(View.VISIBLE);
                        mResultText.setText("强烈推荐");
                        break;

                    default:
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
