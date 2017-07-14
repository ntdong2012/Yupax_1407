package vsec.com.yupax.ui.screen.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.HistoryTransactionContract;
import vsec.com.yupax.model.http.response.TransactionItem;
import vsec.com.yupax.presenter.HistoryTransactionPresenter;
import vsec.com.yupax.ui.view.adapter.TransactionAdapter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class TransactionFg extends BaseFragment<HistoryTransactionPresenter> implements HistoryTransactionContract.View {


    private ArrayList<TransactionItem> transactionItems;
    private TransactionAdapter transactionAdapter;
    @BindView(R.id.history_transaction_recycleview)
    RecyclerView transactionRv;
    RecyclerView.LayoutManager layoutManager;
    @BindView(R.id.process)
    ProgressBar progressBar;


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetTransactionSuccess() {

    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_transaction_layout;
    }

    @Override
    protected void initEventAndData() {
        initTransactionRecycleView();
        onStopLoading();
    }

    void initTransactionRecycleView() {
        transactionItems = new ArrayList<>();
        transactionAdapter = new TransactionAdapter(getActivity(), transactionItems);
        layoutManager = new LinearLayoutManager(getActivity());
        transactionRv.setLayoutManager(layoutManager);

        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));
        transactionItems.add(new TransactionItem("Bạn đã giao dịch 15000 tại Vietjet Cargo, thành viên của Vietjet air", "17:29 23/05/2017"));

        transactionRv.setAdapter(transactionAdapter);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
