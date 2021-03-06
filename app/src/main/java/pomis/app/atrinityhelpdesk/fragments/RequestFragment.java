package pomis.app.atrinityhelpdesk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pomis.app.atrinityhelpdesk.R;
import pomis.app.atrinityhelpdesk.models.BodyModel;
import pomis.app.atrinityhelpdesk.networking.APIFacade;
import pomis.app.atrinityhelpdesk.networking.OnResponseCodeReceived;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {


    @BindView(R.id.tv_request_fio)
    TextView tvFIO;

    @BindView(R.id.tv_request_description)
    TextView tvDescription;

    @BindView(R.id.et_request_solution)
    EditText etSolution;

    @BindView(R.id.tv_event_first)
    TextView tvEventFirst;

    @BindView(R.id.tv_event_second)
    TextView tvEventSecond;

    @BindView(R.id.tv_event_third)
    TextView tvEventThird;

    AlertDialog dialog;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_request, container, false);
        ButterKnife.bind(this, v);
        showIndicator();
        loadData();
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void showIndicator() {
        dialog = new AlertDialog.Builder(getContext())
                .setMessage("Загрузка...")
                .setCancelable(false)
                .show();
    }

    private void loadData() {
        String requestID = getActivity().getIntent().getStringExtra("id");
        new APIFacade().getInfo(
                BodyModel.getHardcodedBody("GET_INFO").setField("RequestID", requestID),
                new OnResponseCodeReceived() {
                    @Override
                    public void onSuccess(String responceText) {
                        Log.d("kek", responceText);
                        try {
                            JSONObject json = new JSONObject(responceText).getJSONObject("Request");
                            tvFIO.setText(json.getJSONObject("ContactContactID")
                                    .getJSONObject("UserID")
                                    .getString("FullName")
                            );
                            tvDescription.setText(json.getString("Description"));
                            etSolution.setText(json.getString("SolutionDescription"));
                            tvEventFirst.setText(json.getString("CreatedAt") + "\n" + "Время подачи заявки");
                            tvEventSecond.setText(json.getString("SLARecoveryTime").length() > 10 ?
                                    (json.getString("SLARecoveryTime") + "\n" + "Планируемое время завершения") :
                                    "Планируемое время завершения не указано");
                            tvEventThird.setText(json.getString("ActualRecoveryTime") + "\n" + "Фактическое время завершения");
                            dialog.cancel();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int responseCode) {
                        Toast.makeText(getContext(), "Ошибка получения данных", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
