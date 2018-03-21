package hr.ericsson.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import hr.ericsson.application.domain.Application;

@Component
public class ApplicationDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	
	public List<Application> getAll() {		
		List<Application> resultList = (List<Application>) jdbcTemplate.query(READ_ALL_QUERY, new RowMapper<Application>() {
			@Override
			public Application mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
				Application result = new Application();
				result.setId(rs.getInt("id"));
				result.setName(rs.getString("name"));
				result.setEmail(rs.getString("email"));
				result.setPosition(rs.getString("position"));
				result.setCompany(rs.getString("company"));
				
				return result;
			}
		});

		return resultList;
	}
	
	
	public void delete(final Integer id) {		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(id);
		namedJdbcTemplate.update(DELETE_QUERY, parameters);
	}
	
	public void update(final Application application) {		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(application);
	}
	
	public Application insert(final Application application) {
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(application);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedJdbcTemplate.update(INSERT_QUERY, parameters, keyHolder, new String[]{"id"});
		application.setId(keyHolder.getKey().intValue());
		return application;
	}

	
	final static String INSERT_QUERY =  "INSERT INTO public.applications(name, email, position, company) VALUES(:name, :email, :position, :company)";
	final static String READ_ALL_QUERY = "SELECT id, name, email, position, company FROM public.applications";
	final static String DELETE_QUERY = "DELETE FROM public.applications where id=:id";
}
