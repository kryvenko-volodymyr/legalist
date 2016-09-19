package ua.legalist.service.util;

import javax.servlet.http.HttpServletRequest;

public interface ComplianceMonitor {

    public boolean isUserPreRegAbuse(HttpServletRequest request);

    public boolean isEmailRegConfirmatinAbuse(HttpServletRequest request);

    public boolean isProperEmail(String email);

    public boolean isProperPassword(String password);
}
