package com.boa.eagls.government.dto;

/**
 * Used instead of com.nb.gsa.Adapter.DataAccess.DataAccessAdapter. Contains just error messages.
 * User: OlegK
 * Date: 08.07.2003
 * Time: 19:37:02
 */
public class ApplicationSystemErrors {
    public final static String ERROR_SP_NULL_RESULTSET  = "SYS_E0004:DataAccessAdapter::Stored procedure returned a null resultset.";
    public final static String ERROR_SQL_NULL_RESULTSET = "SYS_E0004:DataAccessAdapter::SQL statement returned a null resultset.";
    public final static String ERROR_RESULT_FLAG = "SYS_E0004:DataAccessAdapter::Result flag returned a null value.";
    public final static String ERROR_NULL_VALUE_NOT_SUPPORTED = "SYS_E0002:DataAccessAdapter::Stored procedure returned an unsupported null value.";
    public final static String ERROR_DB_TYPE_MISMATCH = "SYS_E0002:DataAccessAdapter::The DAA received an unrecognized value from the database.";
    public final static String ERROR_HIERARCHY_ACCESS = "SYS_E0003:DataAccessAdapter::Authorize hierarchy returned a null value.";
    public final static String ERROR_SP_COMPILE_ERROR = "SYS_E0001:DataAccessAdapter::Database failed to compile stored procedure";
    public final static String ERROR_SQL_COMPILE_ERROR = "SYS_E0001:DataAccessAdapter::Database failed to compile SQL statement";

}
