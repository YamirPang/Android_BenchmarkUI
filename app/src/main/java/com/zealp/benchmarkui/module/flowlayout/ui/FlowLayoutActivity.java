package com.zealp.benchmarkui.module.flowlayout.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zealp.benchmark_ui.utils.ResUtils;
import com.zealp.benchmark_ui.widget.flowlayout.FlowTagLayout;
import com.zealp.benchmarkui.R;
import com.zealp.benchmarkui.module.flowlayout.adapter.FlowTagAdapter;

import java.util.List;

import static com.zealp.benchmark_ui.common.BenchmarkUI.getContext;

/**
 * Author: lianxiang
 * Created On: 2019/5/10
 */
public class FlowLayoutActivity extends AppCompatActivity {
    FlowTagLayout mNormalFlowTagLayout;

    FlowTagLayout mSingleFlowTagLayout;

    FlowTagLayout mMultiFlowTagLayout;

    FlowTagLayout mDisplayFlowTagLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowtag_layout_fra);

        mNormalFlowTagLayout = findViewById(R.id.flowlayout_normal_select);
        mSingleFlowTagLayout = findViewById(R.id.flowlayout_single_select);
        mMultiFlowTagLayout = findViewById(R.id.flowlayout_multi_select);
        mDisplayFlowTagLayout = findViewById(R.id.flowlayout_display);

        initNormalFlowTagLayout();
        initSingleFlowTagLayout();
        initMultiFlowTagLayout();
    }

    private void initNormalFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mNormalFlowTagLayout.setAdapter(tagAdapter);
        mNormalFlowTagLayout.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
//                ToastUtils.toast("点击了：" + parent.getAdapter().getItem(position));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
    }

    private void initSingleFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(this);
        mSingleFlowTagLayout.setAdapter(tagAdapter);
        mSingleFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mSingleFlowTagLayout.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
//                ToastUtils.toast(getSelectedText(parent, selectedList));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
        tagAdapter.setSelectedPositions(2, 3, 4);

    }

    private void initMultiFlowTagLayout() {
        FlowTagAdapter tagAdapter = new FlowTagAdapter(getContext());
        mMultiFlowTagLayout.setAdapter(tagAdapter);
        mMultiFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMultiFlowTagLayout.setOnTagSelectListener(new FlowTagLayout.OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int position, List<Integer> selectedList) {
//                ToastUtils.toast(getSelectedText(parent, selectedList));
            }
        });
        tagAdapter.addTags(ResUtils.getStringArray(R.array.tags_values));
        tagAdapter.setSelectedPositions(2, 3, 4);

//        mMultiFlowTagLayout.setItems("1111", "2222", "3333", "4444");
//        mMultiFlowTagLayout.setSelectedItems("3333", "4444");

    }

    private String getSelectedText(FlowTagLayout parent, List<Integer> selectedList) {
        StringBuilder sb = new StringBuilder("选中的内容：\n");
        for (int index : selectedList) {
            sb.append(parent.getAdapter().getItem(index));
            sb.append(";");
        }
        return sb.toString();
    }

}
