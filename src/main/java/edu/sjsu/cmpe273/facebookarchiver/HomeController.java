package edu.sjsu.cmpe273.facebookarchiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserAccounts;
import edu.sjsu.cmpe273.facebookarchiver.entity.UserPhotos;
import edu.sjsu.cmpe273.facebookarchiver.services.UserAccountService;
import edu.sjsu.cmpe273.facebookarchiver.services.UserPhotoService;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by emy on 4/24/15.
 */
@Controller
public class HomeController {


    private static final String STATE = "state";
    private String client_id = "966288596744764";
    private String app_secret = "dea83246d01afcb06464a13347a10064";
    private String url = "http://localhost:8080";
    private ObjectMapper objectMapper;
    private OAuthService oAuthService;
    private FacebookClient facebookClient;

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    UserPhotoService userPhotoService;


    public HomeController() {
         this.oAuthService = buildOAuthService(client_id, app_secret);
    }
    //starts the oauth by passing necessary parameters and initializes oauthservice.
    private OAuthService buildOAuthService(String client_id, String app_secret){
        return new ServiceBuilder()
                .apiKey(client_id)
                .apiSecret(app_secret)
                .callback(url+"/auth/facebook/callback") //redirects the callback and must match the url in facebook settings.
                .provider(FacebookApi.class)
                .scope("email")
                .scope("user_photos")
                .build();
    }
      @RequestMapping(value="/")
      public String HomePage() {
       return "login";
      }

    //link redirects to facebook for access token.
    @RequestMapping(value="/auth/facebook", method=RequestMethod.GET)
    public RedirectView startAuthentication(HttpSession httpSession) throws OAuthException {
        String state = UUID.randomUUID().toString();
        httpSession.setAttribute(STATE, state);
        String authorizationUrl = oAuthService.getAuthorizationUrl(Token.empty())+"&"+STATE+"="+state;
        return new RedirectView(authorizationUrl);
    }
    //method handles the callback from facebook
    @RequestMapping(value="/auth/facebook/callback")
    public String callback(@RequestParam("code")String code, @RequestParam(STATE)String state, HttpSession httpsession) throws IOException
    {
        //checks state parameter.
        String stateFromSession = (String)httpsession.getAttribute(STATE);
        httpsession.removeAttribute(STATE);
        if(!state.equals(stateFromSession)) { //incase of failure redirects user to login
            return "login";
        }
        //Exchanges the access token
        Token accessToken = getAccessToken(code);
        this.facebookClient = new DefaultFacebookClient(accessToken.getToken(), Version.VERSION_2_2);
        userAccountService.create(facebookClient);
        userPhotoService.create(facebookClient);
        return "logged"; //successfully logged in.
    }

    private Token getAccessToken(String code) {
        Verifier verify = new Verifier(code);
        return oAuthService.getAccessToken(Token.empty(), verify);//Token.Empty() method in scribe and handles OAuthservice for both OAuth1 and 2.
    }

    //@RequestMapping(value="/{user-id}/attending", method = RequestMethod.GET)
    //@RequestMapping(value = "/userAccounts", method=RequestMethod.GET)
    //@ResponseStatus(HttpStatus.OK)
   // public @ResponseBody
    //UserAccounts getProfile(/*@PathVariable("user-id")String userId*/) {
      //  User me = facebookClient.fetchObject("me", User.class);
        //return userAccountService.create(me);
        //return;
   // }
    //@RequestMapping(value="/{user-id}/photo", method = RequestMethod.GET)
    @RequestMapping(value="/userPhoto", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    void getPhoto(/*@PathVariable("user-id")String userId*/)
    {
       userPhotoService.create(facebookClient);
        return;
    }

    //List all photos
    @RequestMapping(value="/userAccounts/{id}/userPhotos", method=RequestMethod.GET)
    @ResponseBody
    public ArrayList<UserPhotos> getAllPhotos(@PathVariable("id")String Id){
        return userPhotoService.listAllPhotos(Id);
    }



}
