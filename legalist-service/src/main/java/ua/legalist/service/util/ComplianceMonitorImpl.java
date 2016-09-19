
package ua.legalist.service.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * Checks methods inputs for compliance with with business logic.
 * TODO: replace stub by real methods
 */

@Service("complianceMonitor")
public class ComplianceMonitorImpl implements ComplianceMonitor {

    @Override
    public boolean isUserPreRegAbuse(HttpServletRequest request) {
        //TODO: throw ComlianceMonitorException if too many requests from one IP 
        return false;
    }

    @Override
    public boolean isEmailRegConfirmatinAbuse(HttpServletRequest request) {
      //TODO: throw ComlianceMonitorException if too many requests from one IP 
      return true;
    }

    @Override
    public boolean isProperEmail(String email) {
        //tamplate check
        return true;
    }

    @Override
    public boolean isProperPassword(String password) {
        //complexity check
        return true;
    }
    
    
    
}
