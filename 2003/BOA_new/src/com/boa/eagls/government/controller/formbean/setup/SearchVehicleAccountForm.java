package com.boa.eagls.government.controller.formbean.setup;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * <p><small> DVI Company, 28.07.2003 14:48:54</small></p>
 * @author GlebL
 */
public class SearchVehicleAccountForm extends ActionForm {
  private String txt_centralAccountId;
  private String txt_equipmentId;

  public void setTxt_centralAccountId(String txt_centralAccountId) {
     this.txt_centralAccountId = txt_centralAccountId;
  }

  public String getTxt_centralAccountId() {
    return txt_centralAccountId;
  }

  public void setTxt_equipmentId(String txt_equipmentId) {
    this.txt_equipmentId = txt_equipmentId;
  }

  public String getTxt_equipmentId() {
    return txt_equipmentId;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

}