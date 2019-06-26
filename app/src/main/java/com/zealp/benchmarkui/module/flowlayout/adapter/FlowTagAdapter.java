package com.zealp.benchmarkui.module.flowlayout.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.zealp.benchmark_ui.widget.flowlayout.BaseTagAdapter;
import com.zealp.benchmarkui.R;

public class FlowTagAdapter extends BaseTagAdapter<String, TextView> {

    public FlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        return (TextView) convertView.findViewById(R.id.tv_tag);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.flow_tag_adapter_item;
    }

    @Override
    protected void convert(TextView textView, String item, int position) {
        textView.setText(item);
    }
}
