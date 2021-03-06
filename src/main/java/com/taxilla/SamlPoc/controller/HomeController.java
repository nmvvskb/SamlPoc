package com.taxilla.SamlPoc.controller;

import com.taxilla.SamlPoc.componentVo.IdetityProviders;
import com.taxilla.SamlPoc.componentVo.IdpTypeSelection;
import com.taxilla.SamlPoc.entity.GivenIdp;
import com.taxilla.SamlPoc.entity.IdentityProviderDetail;
import com.taxilla.SamlPoc.utils.CustomeException;
import com.taxilla.SamlPoc.utils.IDPFacotry;
import com.taxilla.SamlPoc.services.IIdentityProviderService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("idpList", new IdetityProviders());
        return "index";
    }

    private static String idpType="";

    @PostMapping(value = "/auth")
    public RedirectView handleSamlAuth( Model model,IdetityProviders idpDetails,HttpServletResponse response, HttpServletRequest request) throws IOException, URISyntaxException {
        System.out.println("Inside of HandleSamlAuth");
        RedirectView redirectView = new RedirectView();
        if(idpDetails.getProviderList()==null){
            System.out.println("Did not choose any Identity provider");
            model.addAttribute("idpList", new IdetityProviders());
            redirectView.setUrl("/");
            return redirectView;
        }else {
            System.out.println("Selected IDP Name "+idpDetails.getProviderList().name());
            String selectedIDP = idpDetails.getProviderList().name();
            idpType = selectedIDP;
            try {
                IDPFacotry idpFacotry = new IDPFacotry(selectedIDP);

                redirectView.setUrl("/idpSelect");
                return redirectView;
//                String endPointURL = idpFacotry.getEndoPointURL();
//                System.out.println("Endpoint URL :: "+endPointURL);
//                response.sendRedirect(endPointURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       return null;
    }


    @RequestMapping(value = "/saml/SSO")
    public RedirectView idpMetaData(@RequestParam(value = "SAMLResponse", required = true) String SAMLResponse, HttpServletResponse response, HttpServletRequest request, Model redirectAttributes) throws IOException, URISyntaxException {
        System.out.println("response.getStatus()"+response.getStatus());
        String re=null;
        RedirectView redirectView = new RedirectView();
        if(response.getStatus()==200){
            System.out.println("Inside if condition");
            if (SAMLResponse != null && !SAMLResponse.equalsIgnoreCase("") && SAMLResponse.length()>0) {
                System.out.println("INside of auth");
                redirectView.setContextRelative(true);
                redirectView.addStaticAttribute("SAMLResponse", SAMLResponse);
                redirectView.setUrl("/home");
            } else {
                redirectView.setUrl("/");
            }
        }
        return redirectView;
    }

    @RequestMapping("/logout")
    public RedirectView logout(Model model) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/");
        return redirectView;
    }

    @RequestMapping("/idpSelect")
    public String idpSelect(Model model) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        model.addAttribute("idpSelectList", new IdpTypeSelection());
        redirectView.setUrl("/idpselection");
        return "idpselection";
    }

    @RequestMapping(value = "/home/{SAMLResponse}",method = RequestMethod.GET)
    public String home(Model model,@PathVariable String SAMLResponse) {
        System.out.println(" home in  HomeController");
        String decodedInfo = IIdentityProviderService.SAMLDecoder(SAMLResponse);
        Document doc = IIdentityProviderService.convertStringToXMLDocument( decodedInfo );

        //need to do dynamic approach to fetch detials of response
        //Verify XML document is build correctly
        System.out.println("FRIST Child ::: "+doc.getFirstChild().getNodeName());
        String rootTag = IIdentityProviderService.getRootTag(doc.getFirstChild().getNodeName());

        String loggedInAs = doc.getElementsByTagName(rootTag+":NameID").item(0).getTextContent();
        System.out.println("saml2:NameID ::: "+loggedInAs);

        model.addAttribute("username", loggedInAs);

//        JSONObject xmlJSONObj = XML.toJSONObject(decodedInfo);
//        decodedInfo = xmlJSONObj.toString();

        model.addAttribute("SAMLResponse", decodedInfo);
        return "home";
    }

    @PostMapping(value = "/idpTypeSelection")
    public RedirectView handleSamlTypeSelection( Model model,IdpTypeSelection idpDetails) throws IOException, URISyntaxException {
        System.out.println("Inside of HandleSamlAuth");
        RedirectView redirectView = new RedirectView();
        if(idpDetails.getIdpSelectionList()==null){
            System.out.println("Did not choose any Identity provider Type");
            redirectView.setUrl("/idpSelect");
            return redirectView;
        }else {
            System.out.println("Selected IDP Type Name "+idpDetails.getIdpSelectionList().name());
            String selectedIDPType = idpDetails.getIdpSelectionList().name();
            try {
                System.out.println("I am in ....!"+selectedIDPType);
                IDPFacotry idpFacotry = new IDPFacotry(selectedIDPType);
                if(selectedIDPType.equalsIgnoreCase("saml") || selectedIDPType.equalsIgnoreCase("oauth") || selectedIDPType.equalsIgnoreCase("oidc")){
                    redirectView.addStaticAttribute("selectedIDPType", selectedIDPType);
                    redirectView.setUrl("/provideidp");
                    return redirectView;
                }else{
                    redirectView.setUrl("/idpNotImpl");
                    return redirectView;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Map<String,String> map = new HashMap<>();
                map.put("errorMessage", e.getMessage());
                model.addAllAttributes(map);
                redirectView.setUrl("/provideidp");
                return redirectView;
            }
        }
    }

    @RequestMapping("/idpNotImpl")
    public String idpNotImpl(Model model) {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        model.addAttribute("idpSelectList", new IdpTypeSelection());
        model.addAttribute("idpNotImpl", "Selected Idp Type is Inprogress, Choose another..!");
        redirectView.setUrl("/idpNotImpl");
        return "idpselection";
    }

    @GetMapping("/provideidp") //  model.addAttribute("idpList", new IdetityProviders());
    public String provideidp(Model model,@RequestParam(value = "selectedIDPType", required = true) String selectedIDPType) {
        model.addAttribute("givenIdp", new GivenIdp());
        model.addAttribute("selectedIDPType", selectedIDPType);
        model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
        return "provideidp";
    }


    @GetMapping("/redirectsso") //  model.addAttribute("idpList", new IdetityProviders());
    public String redirectsso(Model model,@RequestParam(value = "redirectUrl", required = true) String redirectUrl) {
        model.addAttribute("redirectUrl", redirectUrl);
        return "redirectsso";
    }

    @PostMapping(value = "/giveIdpType")
    public String submit(@ModelAttribute("givenIdp") GivenIdp idp,
                         BindingResult result, ModelMap model,HttpServletResponse response, HttpServletRequest request) throws IOException, CustomeException {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println("givenIdp dddd: "+idp.getName());
        System.out.println("givenIdp getSelectedIDPType: "+idp.getSelectedIDPType());
        String providedIdp = idp.getName();
        String selectedIDPType = idp.getSelectedIDPType();
        IDPFacotry idpFacotry = new IDPFacotry(selectedIDPType);
        String endPointURL = idpFacotry.getMetaDataInfo(providedIdp);
        System.out.println("Endpoint URL :: "+endPointURL);
        if(endPointURL!=null){
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(endPointURL);
//            response.sendRedirect(endPointURL);
        }else{
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/provideidp");
            model.addAttribute("givenIdp", new GivenIdp());
            model.addAttribute("selectedIDPType", selectedIDPType);
            model.addAttribute("errorMessage", model.getAttribute("errorMessage"));
            return "provideidp";
        }
        return null;
    }
}
