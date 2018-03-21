package hr.ericsson.application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hr.ericsson.application.domain.Application;

public class PrijaveDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	public Application insert(final Application application) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(application);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(INSERT_PRIJAVE, parameters, keyHolder, new String[]{"id"});
		application.setId(keyHolder.getKey().intValue());
		return application;
	}
	
	final static String INSERT_PRIJAVE = "INSERT INTO public.prijave(id, name, email, position, company) VALUES (:id, :name, :email, :position, :company)";
	

}
