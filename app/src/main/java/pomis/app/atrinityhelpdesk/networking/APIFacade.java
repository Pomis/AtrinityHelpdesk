package pomis.app.atrinityhelpdesk.networking;

import java.io.IOException;
import java.util.List;

import pomis.app.atrinityhelpdesk.models.BodyModel;
import pomis.app.atrinityhelpdesk.models.RequestModel;

/**
 * Created by romanismagilov on 02.11.16.
 */

public class APIFacade implements AtrinityAPI {

    @Override
    public void getDefaultRequests(final OnResponseCodeReceived callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection.withJSON(
                            APIEndpoints.API_SERVICE,
                            "POST",
                            BodyModel
                                    .getHardcodedBody("GET_LIST")
                                    .setField("FilterID", "3CD0E650-4B81-E511-A39A-1CC1DEAD694D")
                                    .toJSON(),
                            callback
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void getInfo(final BodyModel body, final OnResponseCodeReceived callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Connection.withJSON(
                            APIEndpoints.API_SERVICE,
                            "POST",
                            body.toJSON(),
                            callback
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
