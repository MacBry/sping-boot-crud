package com.mac.bry.crud.configurations.audit;

import javax.servlet.ServletRequest;

import org.audit4j.core.MetaData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class AuditMetaData implements MetaData {

	@Override
	public String getActor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
		}
		return "anonymous";
	}

	@Override
	public String getOrigin() {
		try {
			return ((ServletRequest) ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest()).getRemoteAddr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "unidentified";
	}

}
