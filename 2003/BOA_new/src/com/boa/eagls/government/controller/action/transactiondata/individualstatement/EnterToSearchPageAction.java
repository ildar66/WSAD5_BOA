package com.boa.eagls.government.controller.action.transactiondata.individualstatement;

import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStatementForm;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStmFormValues;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 14.07.2003
 * Time: 14:55:45
 * To change this template use Options | File Templates.
 */
public class EnterToSearchPageAction extends Action{

    protected EAGLSSession session;

    /**
     * Constructor
     */
    public EnterToSearchPageAction() {
        super();
        session = new EAGLSSession();
    }


    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {
        IndividualStatementForm formBean = (IndividualStatementForm) form;
        formBean.setRag_statementSearch(IndividualStmFormValues.CURRENT_STATEMENT);
        return mapping.findForward(Forwards.SEARCH_INDIVIDUAL_STATMENT_FORWARD);
    }

}
