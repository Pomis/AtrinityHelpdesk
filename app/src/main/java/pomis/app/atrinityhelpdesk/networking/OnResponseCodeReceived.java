package pomis.app.atrinityhelpdesk.networking;

/**
 * Created by romanismagilov on 02.11.16.
 */

public interface OnResponseCodeReceived {
    void onSuccess(String responceText);
    void onFailure(int responseCode);
}