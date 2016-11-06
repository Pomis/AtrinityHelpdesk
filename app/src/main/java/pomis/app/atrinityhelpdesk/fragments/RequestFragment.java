package pomis.app.atrinityhelpdesk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import pomis.app.atrinityhelpdesk.R;
import pomis.app.atrinityhelpdesk.models.BodyModel;
import pomis.app.atrinityhelpdesk.networking.APIFacade;
import pomis.app.atrinityhelpdesk.networking.OnResponseCodeReceived;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {


    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        String requestID = getActivity().getIntent().getStringExtra("id");
        new APIFacade().getInfo(
                BodyModel.getHardcodedBody("GET_INFO").setField("RequestID", requestID),
                new OnResponseCodeReceived() {
                    @Override
                    public void onSuccess(String responceText) {
                        Log.d("kek", responceText);
                    }

                    @Override
                    public void onFailure(int responseCode) {

                    }
                }
        );
    }
}
