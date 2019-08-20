
package kr.ac.dgu.base.cmm.log; 


import net.sf.log4jdbc.Slf4jSpyLogDelegator;
import net.sf.log4jdbc.Spy;
import net.sf.log4jdbc.tools.LoggingType;
/**
 * <pre>
 * kr.ac.dgu.base.cmm.log 
 * @Class Name : Log4JdbcCustomFormatter.java
 * @Description :  class
 * @Modification Information
 * </pre>
 * @author : dgu
 * @date : 2019. 9. 18. 오후 5:41:29
 * @version : 1.0
 *  
 * @Copyright ⓒ 2019 Dongguk Univ., All Rights Reserved.
 * 
 * @history : 
 *	-----------------------------------------------------------------------
 *	변경일				작성자						변경내용  
 *	----------- ------------------- ---------------------------------------
 *	2019. 9. 18.		dgu				최초 작성 
 *	-----------------------------------------------------------------------
 *  
 */

public class Log4JdbcCustomFormatter extends Slf4jSpyLogDelegator {

    private LoggingType loggingType = LoggingType.DISABLED;

    private String margin = "";

    private String sqlPrefix = "SQL:";


    public int getMargin() {
        return margin.length();
    }

    public void setMargin(int n) {
        margin = String.format("%1$#" + n + "s", "");
    }


    public Log4JdbcCustomFormatter() {
    }


    
    @Override
    public String sqlOccured(Spy spy, String methodCall, String rawSql) {
        if (loggingType == LoggingType.DISABLED) {
            return "";
        }

        // Remove all existing cr lf, unless MULTI_LINE
        if (loggingType != LoggingType.MULTI_LINE) {
            rawSql = rawSql.replaceAll("\r", "");
            rawSql = rawSql.replaceAll("\n", "");
        }
        final String fromClause = " from ";
        String sql = rawSql;
        if (loggingType == LoggingType.MULTI_LINE) {
            final String whereClause = " where ";
            final String andClause = " and ";
            final String subSelectClauseS = "\\(select";
            final String subSelectClauseR = " (select";
            sql = sql.replaceAll(fromClause, "\n" + margin + fromClause);
            sql = sql.replaceAll(whereClause, "\n" + margin + whereClause);
            sql = sql.replaceAll(andClause, "\n" + margin + andClause);
            sql = sql.replaceAll(subSelectClauseS, "\n" + margin + subSelectClauseR);
        }
        if (loggingType == LoggingType.SINGLE_LINE_TWO_COLUMNS) {
            if (sql.startsWith("select")) {
                String from = sql.substring(sql.indexOf(fromClause) + fromClause.length());
                sql = from + "\t" + sql;
            }
        }
        getSqlOnlyLogger().info(sqlPrefix + System.getProperty("line.separator")  + sql);
        return sql;
    }

    @Override
    public String sqlOccured(Spy spy, String methodCall, String[] sqls) {
        String s = "";
        for (int i = 0; i < sqls.length; i++) {
            s += sqlOccured(spy, methodCall, sqls[i]) + String.format("%n");
        }
        return s;
    }

    public LoggingType getLoggingType() {
        return loggingType;
    }

    public void setLoggingType(LoggingType loggingType) {
        this.loggingType = loggingType;
    }

    public String getSqlPrefix()
    {
        return sqlPrefix;
    }

    public void setSqlPrefix(String sqlPrefix)
    {
        this.sqlPrefix = sqlPrefix;
    }

}
