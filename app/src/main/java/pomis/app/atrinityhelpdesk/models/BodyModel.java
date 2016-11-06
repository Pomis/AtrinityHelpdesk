package pomis.app.atrinityhelpdesk.models;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by romanismagilov on 01.11.16.
 */

public class BodyModel {


    String ApiKey;

    String Login;

    String Password;

    String ObjectCode;

    String Action;

    String FieldID;

    String FieldValue;

    static public BodyModel getHardcodedBody(String action) {
        BodyModel bodyModel = new BodyModel();
        bodyModel.ApiKey = "e8e6a311d54985a067ece5a008da280a";
        bodyModel.Login = "d_blinov";
        bodyModel.Password = "Passw0rd";
        bodyModel.ObjectCode = "300";
        bodyModel.Action = action;
        return bodyModel;
    }

    public BodyModel setField(String FieldID, String FieldValue) {
        this.FieldID = FieldID;
        this.FieldValue = FieldValue;
        return this;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("ApiKey", ApiKey);
            object.put("Login", Login);
            object.put("Password", Password);
            object.put("ObjectCode", ObjectCode);
            object.put("Action", Action);
            JSONObject fields = new JSONObject();
//            fields.put("FilterID", "3CD0E650-4B81-E511-A39A-1CC1DEAD694D");
            fields.put(FieldID, FieldValue);
            object.put("Fields", fields);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
