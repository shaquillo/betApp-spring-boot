package com.kindredgroup.unibetlivetest.api;

public interface Urls {

    String BASE_PATH = "/api/v1";

    /** events apis **/
    String EVENTS = "/events";
    String SELECTIONS = EVENTS + "/{id}/selections";

    /** bets api **/
    String BETS = "/bets";
    String ADD_BET = BETS + "/add";


    /** customers apis **/
    String CUSTOMERS = "/customers";
    String CURRENT_CUSTOMER = CUSTOMERS + "/current";

}
