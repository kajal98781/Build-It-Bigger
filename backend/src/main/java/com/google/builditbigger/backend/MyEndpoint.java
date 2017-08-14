/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/
package com.google.builditbigger.backend;
import com.example.JokeFromJavaLib;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
/**
 * An endpoint class we are exposing
 */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.google.com",
                ownerName = "backend.builditbigger.google.com",
                packagePath = ""
        )
)
public class MyEndpoint {
    /**
     * A simple endpoint method that gives a joke
     */
    @ApiMethod(name = "tellJoke")
    public MyBean tellJoke() {
        MyBean response = new MyBean();
        response.setJoke(JokeFromJavaLib.getJokeFromJava());
        return response;
    }
}
