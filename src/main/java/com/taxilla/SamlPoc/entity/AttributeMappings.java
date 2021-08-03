package com.taxilla.SamlPoc.entity;

public class AttributeMappings {

    /*
    Map user_name to the attribute for user name in the provider assertion or token. The default for SAML is NameID.
     */
    private String user_name;

    /*
    Maps the attribute on the assertion to the email_verified user record at the time of authentication.
     Default is false. Once set to true, record remains true for subsequent authentications.
     */
    private String email_verified;

    /*
    Map external_groups to the attribute for groups in the provider assertion.
     */
    private String[] external_groups;

    /*
    Map phone_number to the attribute for phone number in the provider assertion or token.
     */
    private String phone_number;

    /*
    Map given_name to the attribute for given name in the provider assertion or token.
     */
    private String given_name;

    /*
    Map family_name to the attribute for family name in the provider assertion or token.
     */
    private String family_name;

    /*
    Map email to the attribute for email in the provider assertion or token.
     */
    private String email;


    // Need to discuss about below variable
    /*
    Map external attribute to UAA recognized mappings. Mapping should be of the format user.attribute.<attribute_name>.
     department is used in the documentation as an example attribute.


    private String user.attribute.department;

    */
}
