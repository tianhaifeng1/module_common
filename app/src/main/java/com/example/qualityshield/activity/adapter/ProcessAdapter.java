package com.example.qualityshield.activity.adapter;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.qualityshield.R;
import com.example.qualityshield.bean.ProcessListBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProcessAdapter extends BaseNodeAdapter {
    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;

    public ProcessAdapter() {
        addFullSpanNodeProvider(new FirstProvider());
        addFullSpanNodeProvider(new SecondProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof ProcessListBean) {
            return 0;
        } else if (node instanceof ProcessListBean.ProcessChildBean) {
            return 1;
        }
        return -1;
    }

    public class FirstProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 0;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_node_process;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseNode baseNode) {
            ProcessListBean bean = (ProcessListBean) baseNode;
            baseViewHolder.setText(R.id.item_node_process_name, bean.getName());
//            ImageView ivTag = baseViewHolder.getView(R.id.iv_tag);
//            if (bean.getChildNode() == null || bean.getChildNode().size() == 0) {
//                ivTag.setImageResource(R.drawable.ic_txl_jg);
//            } else {
//                ivTag.setImageResource(R.drawable.ic_dfp_bm_zk);
//            }
        }

        @Override
        public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data, @NotNull List<?> payloads) {
            ProcessListBean bean = (ProcessListBean) data;
            if (bean.getChildNode() == null || bean.getChildNode().size() == 0) {
                return;
            }
            for (Object payload : payloads) {
                if (payload instanceof Integer && (int) payload == EXPAND_COLLAPSE_PAYLOAD) {
                    // 增量刷新，使用动画变化箭头
                    setArrowSpin(helper, data, true);
                }
            }
        }

        @Override
        public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
            getAdapter().expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD);
        }

        private void setArrowSpin(BaseViewHolder helper, BaseNode data, boolean isAnimate) {
            ProcessListBean bean = (ProcessListBean) data;
            ImageView imageView = helper.getView(R.id.iv_tag);
            if (bean.isExpanded()) {
                if (isAnimate) {
                    ViewCompat.animate(imageView).setDuration(200)
                            .setInterpolator(new DecelerateInterpolator())
                            .rotation(90f)
                            .start();
                } else {
                    imageView.setRotation(90f);
                }
            } else {
                if (isAnimate) {
                    ViewCompat.animate(imageView).setDuration(200)
                            .setInterpolator(new DecelerateInterpolator())
                            .rotation(0f)
                            .start();
                } else {
                    imageView.setRotation(0f);
                }
            }
        }
    }

    public class SecondProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 1;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_node_process_child;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseNode baseNode) {
            ProcessListBean.ProcessChildBean bean = (ProcessListBean.ProcessChildBean) baseNode;
            baseViewHolder.setText(R.id.item_node_process_child_name, bean.getName());
            if (bean.getName().equals("当前流程") || bean.getName().equals("备注")) {
                SpanUtils.with(baseViewHolder.getView(R.id.item_node_process_child_content)).append(bean.getValue()).setForegroundColor(ColorUtils.getColor(R.color.color_red)).create();
            } else {
                baseViewHolder.setText(R.id.item_node_process_child_content, bean.getValue());
            }
        }

        @Override
        public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data, @NotNull List<?> payloads) {

        }

        @Override
        public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
            getAdapter().expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD);
        }
    }


}
