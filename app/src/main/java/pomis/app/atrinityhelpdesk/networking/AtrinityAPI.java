package pomis.app.atrinityhelpdesk.networking;

import java.util.List;

import pomis.app.atrinityhelpdesk.models.BodyModel;
import pomis.app.atrinityhelpdesk.models.RequestModel;

/**
 * Created by romanismagilov on 02.11.16.
 */

public interface AtrinityAPI {
    void getDefaultRequests(OnResponseCodeReceived callback);
    void getInfo(BodyModel body, OnResponseCodeReceived callback);
}
