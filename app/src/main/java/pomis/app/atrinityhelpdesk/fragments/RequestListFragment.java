package pomis.app.atrinityhelpdesk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.ResponseBody;
import pomis.app.atrinityhelpdesk.R;
import pomis.app.atrinityhelpdesk.activities.RequestActivity;
import pomis.app.atrinityhelpdesk.adapters.RequestsAdapter;
import pomis.app.atrinityhelpdesk.models.BodyModel;
import pomis.app.atrinityhelpdesk.models.RequestListItem;
import pomis.app.atrinityhelpdesk.models.RequestModel;
import pomis.app.atrinityhelpdesk.networking.APIFacade;
import pomis.app.atrinityhelpdesk.networking.AtrinityAPI;
import pomis.app.atrinityhelpdesk.networking.OnResponseCodeReceived;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestListFragment extends Fragment {

    private static final String DEBUG_TAG = "DEBUG_TAG";
    private AtrinityAPI api;
    private ArrayList<RequestListItem> requestList;
    private RequestsAdapter requestsAdapter;

    @BindView(R.id.lv_requests)
    ListView lvRequests;

    public RequestListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        requestList = new ArrayList<>();
        getRequests();
        super.onResume();
    }

    private void getRequests() {
        api = new APIFacade();
        api.getDefaultRequests(new OnResponseCodeReceived() {
            @Override
            public void onSuccess(String responceText) {
                Log.d("kek", responceText);
                requestList.addAll(RequestModel.listFromJson(responceText));
                bindViews();
            }

            @Override
            public void onFailure(int responseCode) {

            }
        });
    }

    private void bindViews() {
        requestsAdapter = new RequestsAdapter(getContext(), 0, requestList);
        lvRequests.setAdapter(requestsAdapter);
    }

    @OnItemClick(R.id.lv_requests)
    void openRequest(int position) {
        Intent intent = new Intent(getContext(), RequestActivity.class)
                .putExtra("id", ((RequestModel)requestList.get(position)).RequestID);
        startActivity(intent);

    }
}

