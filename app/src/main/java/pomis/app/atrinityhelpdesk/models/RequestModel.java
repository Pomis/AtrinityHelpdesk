package pomis.app.atrinityhelpdesk.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by romanismagilov on 01.11.16.
 */

public class RequestModel implements RequestListItem {
    public String RequestID;

    public String RequestNumber;

    public String Name;

    public String CreatedAt;

    public String Priority;

    public String StatusDisplayName;

    public int ObjectCode;

    public static ArrayList<RequestModel> listFromJson(String responceText) {
        ArrayList<RequestModel> arrayList = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(responceText);
            for (int i = 0; i < json.length(); i++) {
                JSONObject object = json.getJSONObject(i);
                RequestModel model = new RequestModel();
                model.Name = object.getString("Name");
                model.RequestID = object.getString("RequestID");
                model.RequestNumber = object.getString("RequestNumber");
                model.CreatedAt = object.getString("CreatedAt");
                model.Priority = object.getString("Priority");
                model.StatusDisplayName = object.getString("StatusDisplayName");
                model.ObjectCode = object.getInt("ObjectCode");
                arrayList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;


    }
}
