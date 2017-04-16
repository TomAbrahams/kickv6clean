//Abrahams, Thomas
//Kick it app.
//With help from many tutorials (Lynda, Stackoverflow), it was a fun experience. Android is alright.
package com.example.thomas.cskickitappv2;

/**
 * Created by Thomas on 3/1/2017.
 */
import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Generic service provider for two-step OAuth10a.
 */
public class TwoStepOAuth extends DefaultApi10a {

    @Override
    public String getAccessTokenEndpoint() {
        return null;
    }

    @Override
    public String getAuthorizationUrl(Token arg0) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }
}