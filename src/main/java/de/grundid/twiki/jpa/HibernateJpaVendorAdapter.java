package de.grundid.twiki.jpa;

import java.util.Map;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class HibernateJpaVendorAdapter extends org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter {

	@Override
	public Map<String, Object> getJpaPropertyMap() {
		Map<String, Object> map = super.getJpaPropertyMap();
		map.put("hibernate.ejb.naming_strategy", ImprovedNamingStrategy.class.getName());
		return map;
	}
}
